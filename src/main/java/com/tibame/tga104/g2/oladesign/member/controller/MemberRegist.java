package com.tibame.tga104.g2.oladesign.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.service.MemberMailService;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;

import redis.clients.jedis.Jedis;


@WebServlet("/member/MemberRegist")
public class MemberRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Integer count = 0;
//	private MemberService memSvc;
//	private MemberDAO_interface dao;
//	private Gson gson;
//	
//	public void init() {
//		try {
//			dao = new MemberJNDIDAO();
//		}catch(NamingException e) {
//			e.printStackTrace();
//		}
//		gson = new Gson();
//	}
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		MemberVO memberVO = gson.fromJson(request.getReader(), MemberVO.class);
//		
//		JsonObject respBody = new JsonObject();
//		if(memberVO == null) {
//			respBody.addProperty("successful", false);
//		}else {
//			respBody.addProperty("successful", dao.insert(memberVO));
//		}
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
//		========註冊========
		if("regist".equals(action)) { //memRegist.jsp
			
//			錯誤訊息提示
			Map<String, String> errorMsgs = new HashMap<String, String>(); 
			request.setAttribute("errorMsgs", errorMsgs);
			
//				接收請求參數
				String account = request.getParameter("account");
				String accountReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,})$";
				if(account == null || account.trim().length() == 0) {
					errorMsgs.put("account", "帳號請勿空白");
				}else if(!account.matches(accountReg)) {
					errorMsgs.put("account", "請填入英文、數字，可包含'_' ',' '-'的信箱網域");
				}
				
				String memName = request.getParameter("memName");
				String memNameReg = "^[\\u4E00-\\u9FA5]+(·[\\u4E00-\\u9FA5]+)*$"; //可使用·
				if(memName == null || memName.trim().length() == 0) {
					errorMsgs.put("memName", "姓名請勿空白");
				}else if(!memName.matches(memNameReg)) {
					errorMsgs.put("memName", "請填寫真實中文姓名");
				}
				
				String password = request.getParameter("password");
				String passwordReg = "^([A-Za-z0-9]){6,15}$";
				if(password == null || password.trim().length() == 0) {
					errorMsgs.put("password", "請設定密碼");
				}else if(!password.matches(passwordReg)){
					errorMsgs.put("password", "密碼須為6~15位英文或數字");
				}
				
				String cpassword = request.getParameter("cpassword");
				if(cpassword == null || cpassword.trim().length() == 0) {
					errorMsgs.put("cpassword", "請再次輸入密碼");
				}else if(!cpassword.equals(password)) {
					errorMsgs.put("cpassword", "兩次輸入的密碼不一致!");
				}
				
				String memPhone = request.getParameter("memPhone");
				String memPhoneReg = "^09[0-9]{8}$";
				if(memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.put("memPhone", "手機請勿空白");
				}else if(!memPhone.matches(memPhoneReg)) {
					errorMsgs.put("memPhone", "請輸入正確手機格式");
				}
				
				String sex = request.getParameter("sex");
				if(sex == null) {
					errorMsgs.put("sex", "請選擇性別");
				}
				
				String memAddress = request.getParameter("memAddress");
				if(memAddress == null || memAddress.trim().length() == 0) {
					errorMsgs.put("memAddress", "地址請勿空白");
				}
				
				String agreement = request.getParameter("agreement");
				if(agreement == null) {
					errorMsgs.put("agreement", "請確認同意會員條款與隱私條款");
				}
								
				MemberVO memberVO = new MemberVO();
				
				memberVO.setAccount(account);
				memberVO.setMemName(memName);
				memberVO.setPassword(password);
				memberVO.setMemPhone(memPhone);
				memberVO.setSex(sex);
				memberVO.setMemAddress(memAddress);	
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("memberVO", memberVO);
					RequestDispatcher errView = request.getRequestDispatcher("/member/memRegist.jsp");
					errView.forward(request, response);
					return;
				}
				
//				註冊成功新增會員
				MemberService memSvc = new MemberService();
				memberVO = memSvc.addMember(memName, account, password, memPhone, memAddress, sex, null);
				if(!memSvc.isCheckMail()) {
					errorMsgs.put("account", "帳號已經存在，請勿重複註冊");
				}
				
				if(!errorMsgs.isEmpty()) { //判斷帳號已經存在的錯誤
					request.setAttribute("memberVO", memberVO);
					RequestDispatcher errView = request.getRequestDispatcher("/member/memRegist.jsp");
					errView.forward(request, response);
					return;
				}
//				轉交sendMail.jsp
				String url = "/member/sendMail.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
//				驗證碼產生
				Jedis jedis = new Jedis("localhost", 6379);	
				jedis.select(10);
				String uId = UUID.randomUUID().toString();
				String memUID = uId.replace("-", "");
				System.out.println("memUID = " + memUID);
				
				count = count + 1;
				String id = count.toString();
				System.out.println("id = " + id);
				jedis.set(id, memUID);
				jedis.expire(id, 2*60*60);
				
//				寄送驗證信
				String recipient = account;
				String subject = "請完成OLA Design帳號驗證";
				String reci_name = memName;
				String active = "<a href='http://localhost:8081/TGA104G2/front-end/regist-login/registration/MemberMailServlet?method=active&activeCode=" + memUID + "&id=" + id + "'>點此驗證</a>";
				String messageText = "Hello! " + reci_name + " 請點選以下連結啟用帳號: " + active;

				MemberMailService mailService = new MemberMailService();
				mailService.sendMail(recipient, subject, messageText);
				System.out.println("驗證信已發送");
				
				jedis.close();
				
//				String id = "3";
//				String memUID = "g4ea8ee27f0647c98c6ce92dbc18bf9e";
//				jedis.set(id, memUID);
//				
//				String recipient = "tga104g2@gmail.com";
//				String subject = "請完成OLA Design帳號驗證";
//				String reci_name = "測試用";
//				String active = "<a href='http://localhost:8081/TGA104G2/front-end/regist-login/registration/MemberMailServlet?method=active&activeCode=" + memUID + "&id=" + id + "'>點此驗證</a>";
//				String messageText = "Hello! " + reci_name + " 請點選以下連結啟用帳號: " + active;
//
//				MemberMailService mailService = new MemberMailService();
//				mailService.sendMail(recipient, subject, messageText);
//				System.out.println("驗證信已發送");
//				jedis.close();
			
		}
		
		
	}

}
