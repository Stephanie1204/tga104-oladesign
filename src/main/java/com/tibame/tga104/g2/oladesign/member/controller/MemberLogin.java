package com.tibame.tga104.g2.oladesign.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;



@WebServlet("/front-end/regist-login/login/MemberLogin")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
//		========登入========
		if("login".equals(action)) { //login.jsp
//			錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
//			接收請求參數
			String inputAccount = request.getParameter("account");
			String inputAccountReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,})$";
			if(inputAccount == null || inputAccount.trim().length() == 0) {
				errorMsgs.add("請輸入註冊的Email");
			}else if(!inputAccount.matches(inputAccountReg)) {
				errorMsgs.add("請填入英文、數字，可包含'_' ',' '-'的信箱網域");
			}
				
			
			String inputPassword = request.getParameter("password");
			String inputPasswordReg = "^([A-Za-z0-9]){6,15}$";
			if(inputPassword == null || inputPassword.trim().length() == 0) {
				errorMsgs.add("請輸入密碼");
			}else if(!inputPassword.matches(inputPasswordReg)){
				errorMsgs.add("密碼須為6~15位英文或數字");
			}
				
			System.out.println("inputAccount= " + inputAccount);
			System.out.println("inputPassword= " + inputPassword);
//			比對資料庫
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.memberLogin(inputAccount, inputPassword);
			
			if(!errorMsgs.isEmpty()) {
				request.setAttribute("memberVO", memberVO);
				String url = "/front-end/regist-login/login/login.jsp";
				RequestDispatcher errView = request.getRequestDispatcher(url);
				errView.forward(request, response);
				return;
			}
			
			if(memberVO == null) {
				errorMsgs.add("帳號或密碼錯誤!");
			}else if(memberVO.isBan()) {
				System.out.println(memberVO.isBan());
				errorMsgs.add("您的帳號未被開通，請聯絡客服");
			}
			
			if(!errorMsgs.isEmpty()) {
				request.setAttribute("memberVO", memberVO);
				String url = "/front-end/regist-login/login/login.jsp";
				RequestDispatcher errView = request.getRequestDispatcher(url);
				errView.forward(request, response);
				return;
			}
			
			
//			帳號密碼有效時
			HttpSession session = request.getSession();
			session.setAttribute("account", memberVO.getAccount()); //在session內設定已經登入過的標識
			session.setAttribute("memName", memberVO.getMemName()); //在header顯示登入者姓名
			
			try{
				String location = (String)session.getAttribute("location");
				if(location != null) { //查看有無來源網頁
					session.removeAttribute("location");
					response.sendRedirect(location);
					return;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			//無來源網頁重導至首頁
			response.sendRedirect(request.getContextPath() + "/front-end/front-index.jsp");;
		}
	}
}
