package com.tibame.tga104.g2.oladesign.member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import com.google.gson.Gson;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.helper.SendMail;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;

@WebServlet("/member/MemberRegist")
public class MemberRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// ========註冊========
		if ("regist".equals(action)) { // memRegist.jsp

			// 錯誤訊息提示
			Map<String, String> errorMsgs = new HashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數
			String account = request.getParameter("account");
			String accountReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,})$";
			if (account == null || account.trim().length() == 0) {
				errorMsgs.put("account", "帳號請勿空白");
			} else if (!account.matches(accountReg)) {
				errorMsgs.put("account", "請填入英文、數字，可包含'_' ',' '-'的信箱網域");
			}

			String memName = request.getParameter("memName");
			String memNameReg = "^[\\u4E00-\\u9FA5]+(·[\\u4E00-\\u9FA5]+)*$"; // 可使用·
			if (memName == null || memName.trim().length() == 0) {
				errorMsgs.put("memName", "姓名請勿空白");
			} else if (!memName.matches(memNameReg)) {
				errorMsgs.put("memName", "請填寫真實中文姓名");
			}

			String password = request.getParameter("password");
			String passwordReg = "^([A-Za-z0-9]){6,15}$";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.put("password", "請設定密碼");
			} else if (!password.matches(passwordReg)) {
				errorMsgs.put("password", "密碼須為6~15位英文或數字");
			}

			String cpassword = request.getParameter("cpassword");
			if (cpassword == null || cpassword.trim().length() == 0) {
				errorMsgs.put("cpassword", "請再次輸入密碼");
			} else if (!cpassword.equals(password)) {
				errorMsgs.put("cpassword", "兩次輸入的密碼不一致!");
			}

			String memPhone = request.getParameter("memPhone");
			String memPhoneReg = "^09[0-9]{8}$";
			if (memPhone == null || memPhone.trim().length() == 0) {
				errorMsgs.put("memPhone", "手機請勿空白");
			} else if (!memPhone.matches(memPhoneReg)) {
				errorMsgs.put("memPhone", "請輸入正確手機格式");
			}

			String sex = request.getParameter("sex");
			if (sex == null) {
				errorMsgs.put("sex", "請選擇性別");
			}

			String city = request.getParameter("city");
			String town = request.getParameter("town");
			String zipcode = request.getParameter("zipcode");

			String memAddress = request.getParameter("memAddress");
			if (memAddress == null || memAddress.trim().length() == 0) {
				errorMsgs.put("memAddress", "地址請勿空白");
			} else if (city == null || city.trim().length() == 0) {
				errorMsgs.put("memAddress", "請選擇地址縣市與鄉鎮市區");
			}

			if (city != null || city.trim().length() > 0) {
				request.setAttribute("city", city);
				request.setAttribute("town", town);
			}

			String agreement = request.getParameter("agreement");
			System.out.println("agreement:" + agreement);
			if (agreement == null) {
				errorMsgs.put("agreement", "請確認同意會員條款與隱私條款");
			} else {
				System.out.println("已同意會員調款及隱私條款");
				request.setAttribute("agreement", "checked"); // 紀錄已勾選
			}

			MemberVO memberVO = new MemberVO();

			memberVO.setAccount(account);
			memberVO.setMemName(memName);
			memberVO.setPassword(password);
			memberVO.setMemPhone(memPhone);
			memberVO.setSex(sex);
			memberVO.setMemAddress(memAddress);

			if (!errorMsgs.isEmpty()) {
				request.setAttribute("memberVO", memberVO); // 紀錄已輸入的資料
				RequestDispatcher errView = request.getRequestDispatcher("/member/memRegist.jsp");
				errView.forward(request, response);
				return;
			}

			// 無錯誤時將密碼經SHA256運算
			MessageDigest md;
			HexBinaryAdapter hba;
			String passwordKey = null;
			try {
				if (password != null) {
					md = MessageDigest.getInstance("SHA-256");
					// digest方法參數為byte[]
					byte[] hash = md.digest(password.getBytes(Charset.forName("UTF-8"))); // 將密碼字串以UTF-8的方式轉換為位元組陣列
					hba = new HexBinaryAdapter();
					passwordKey = hba.marshal(hash); // 將byte[] hash轉換為字串
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			// 存完整地址
			String finalAddress = zipcode + city + town + memAddress;

			// 註冊成功新增會員(待驗證)
			MemberService memSvc = new MemberService();

			memberVO = memSvc.addMember(memName, account, passwordKey, memPhone, finalAddress, sex, null);
			Integer memId = memberVO.getMemId();
			System.out.println("memId=" + memId);

			if (!memSvc.isCheckMail()) { // false從dao -> service -> controller
				errorMsgs.put("account", "帳號已經存在，請勿重複註冊");
				System.out.println("帳號已經存在，請勿重複註冊");
			}

			if (!errorMsgs.isEmpty()) { // 判斷帳號已經存在的錯誤
				memberVO = null;
				request.setAttribute("memberVO", memberVO);
				RequestDispatcher errView = request.getRequestDispatcher("/member/memRegist.jsp");
				errView.forward(request, response);
				return;
			}

			// 發送驗證信
			SendMail sendFirstMail = new SendMail();
			sendFirstMail.sendAuthMail(memId, account, memName);

			// 轉交sendMail.jsp
			String url = "/member/sendMail.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

		}

		// ========重新發送驗證信========
		if ("resend".equals(action)) {
			// 錯誤訊息提示
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			// 接收請求參數
			String inputAccount = request.getParameter("account");
			String inputAccountReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,})$";
			if (inputAccount == null || inputAccount.trim().length() == 0) {
				errorMsgs.add("請輸入Email");
			} else if (!inputAccount.matches(inputAccountReg)) {
				errorMsgs.add("請填入英文、數字，可包含'_' ',' '-'的信箱網域");
			}

			// 先攔截email格式
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher errView = request.getRequestDispatcher("/member/sendMail.jsp");
				errView.forward(request, response);
				return;
			}

			// email格式正確後，查詢輸入的email對應的會員資料
			MemberVO memberVO = new MemberVO();
			MemberService memSvc = new MemberService();
			memberVO = memSvc.getOneMemberByEmail(inputAccount);

			if (memberVO == null) {
				errorMsgs.add("沒有此email的會員資料，請先進行註冊");
			} else if (memberVO.getIsActive()) {
				errorMsgs.add("已成功驗證email，帳號已成功啟用");
			}

			// 再攔截未註冊email或成功驗證email
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher errView = request.getRequestDispatcher("/member/sendMail.jsp");
				errView.forward(request, response);
				return;
			}

			Integer memId = memberVO.getMemId();
			String memName = memberVO.getMemName();
			System.out.println("重新發送驗證信至:" + memId + ":" + memName);

			// 發送驗證信
			SendMail sendFirstMail = new SendMail();
			sendFirstMail.sendAuthMail(memId, inputAccount, memName);
			System.out.println("驗證信已重新發送");

			// 轉交sendMail.jsp
			request.setAttribute("haveSend", "驗證信已重新發送");
			String url = "/member/sendMail.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

	}

}
