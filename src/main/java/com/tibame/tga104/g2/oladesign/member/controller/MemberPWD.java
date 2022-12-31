package com.tibame.tga104.g2.oladesign.member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.helper.SendMail;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;

@WebServlet("/member/MemberPWD")
public class MemberPWD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// ========忘記密碼========
		if ("forgetpwd".equals(action)) {
			// 錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			// 接收參數
			String account = request.getParameter("account");
			String accountReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,})$";
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("請輸入註冊的Email");
			} else if (!account.matches(accountReg)) {
				errorMsgs.add("請填入英文、數字，可包含'_' ',' '-'的信箱網域");
			}

			if (!errorMsgs.isEmpty()) {
				String url = "/member/forgetpwd.jsp";
				RequestDispatcher errView = request.getRequestDispatcher(url);
				errView.forward(request, response);
				return;
			}

			// 比對資料庫
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.getOneMemberByEmail(account);

			if (memberVO == null) {
				errorMsgs.add("此Email並未註冊");
			} else if (!memberVO.getIsActive()) {
				errorMsgs.add("此Email帳戶尚未開通");
			} else if (memberVO.getIsBan()) {
				errorMsgs.add("此Email帳戶已被封鎖");
			}

			if (!errorMsgs.isEmpty()) {
				String url = "/member/forgetpwd.jsp";
				RequestDispatcher errView = request.getRequestDispatcher(url);
				errView.forward(request, response);
				return;
			}

			Integer memId = memberVO.getMemId();
			String memName = memberVO.getMemName();

			// 發送密碼重設驗證信
			SendMail sendResetMail = new SendMail();
			try {
				sendResetMail.sendPWDAuthMail(memId, account, memName);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("請開啟redis");
			}

			// 轉交forgetpwd.jsp
			request.setAttribute("haveSend", "重設密碼驗證信已發送");
			String url = "/member/forgetpwd.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

		// ========重設密碼========
		if ("recoverpwd".equals(action)) {
			// 錯誤處理
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			// 接收參數
			String newPassword = request.getParameter("newpassword");
			String newPasswordReg = "^([A-Za-z0-9]){6,15}$";
			if (newPassword == null || newPassword.trim().length() == 0) {
				errorMsgs.add("請設定新密碼");
			} else if (!newPassword.matches(newPasswordReg)) {
				errorMsgs.add("密碼須為6~15位英文或數字");
			}

			String newcPassword = request.getParameter("newcpassword");
			if (newcPassword == null || newcPassword.trim().length() == 0) {
				errorMsgs.add("請再次輸入新密碼");
			} else if (!newcPassword.equals(newPassword)) {
				errorMsgs.add("兩次輸入的密碼不一致!");
			}

			if (!errorMsgs.isEmpty()) {
				String url = "/member/recoverpwd.jsp";
				RequestDispatcher errView = request.getRequestDispatcher(url);
				errView.forward(request, response);
				return;
			}

			// 無錯誤時將密碼經SHA256運算
			MessageDigest md;
			HexBinaryAdapter hba;
			String newPasswordKey = null;
			try {
				if (newPassword != null) {
					md = MessageDigest.getInstance("SHA-256");
					// digest方法參數為byte[]
					byte[] hash = md.digest(newPassword.getBytes(Charset.forName("UTF-8"))); // 將密碼字串以UTF-8的方式轉換為位元組陣列
					hba = new HexBinaryAdapter();
					newPasswordKey = hba.marshal(hash); // 將byte[] hash轉換為字串
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			Integer memId = null;
			try {
				memId = Integer.valueOf(request.getParameter("memId"));
				System.out.println("recover memId=" + memId);
			} catch (Exception e) {
				e.printStackTrace();
			}

			MemberService memSvc = new MemberService();
			memSvc.resetMemberPWD(newPasswordKey, memId);

			// 轉交login.jsp
			request.setAttribute("pwdrecover", "true");
			String url = "/member/login.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
	}

}
