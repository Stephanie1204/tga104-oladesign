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
import javax.servlet.http.HttpSession;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import com.tibame.tga104.g2.oladesign.CompanyMember.service.Company_MemService;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;


@WebServlet("/member/MemberLogin")
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
			
			MessageDigest md;
			HexBinaryAdapter hba;
			String passwordKey = null;
			try {
				if(inputPassword != null) {
					md = MessageDigest.getInstance("SHA-256");
					//digest方法參數為byte[]
					byte[] hash = md.digest(inputPassword.getBytes(Charset.forName("UTF-8"))); //將密碼字串以UTF-8的方式轉換為位元組陣列
					hba = new HexBinaryAdapter();
					passwordKey = hba.marshal(hash); //將byte[] hash轉換為字串
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
//			比對資料庫
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.memberLogin(inputAccount, passwordKey);
			
			if(!errorMsgs.isEmpty()) {
				String url = "/member/login.jsp";
				RequestDispatcher errView = request.getRequestDispatcher(url);
				errView.forward(request, response);
				return;
			}
			
			if(memberVO == null) {
				errorMsgs.add("帳號或密碼錯誤!");
			}else if(memberVO.getIsBan()) {
				System.out.println("isBan = " + memberVO.getIsBan());
				errorMsgs.add("您的帳號已被封鎖，請聯絡客服");
			}else if(!memberVO.getIsActive()) {
				System.out.println("isActive= " + memberVO.getIsActive());
				errorMsgs.add("您的帳號尚未完成驗證!");
			}
			
			if(!errorMsgs.isEmpty()) {
				String url = "/member/login.jsp";
				RequestDispatcher errView = request.getRequestDispatcher(url);
				errView.forward(request, response);
				return;
			}
			
			
//			帳號密碼有效時
			HttpSession session = request.getSession();
			session.setAttribute("memName", memberVO.getMemName()); //在session內設定已經登入過的標識
			session.setAttribute("memId", memberVO.getMemId());
			session.setAttribute("isCom", memberVO.getIsCom());
			session.setAttribute("memberVO", memberVO);
			
			Company_MemVO comMemVO = null;
			if(memberVO.getIsCom()) { //如果會員有賣家資格，就傳入賣家資料
				Company_MemService comMemSvc = new Company_MemService();
				comMemVO = comMemSvc.doGetCompanyMemByMemId(memberVO.getMemId());
				session.setAttribute("comMemVO", comMemVO);
			}
			
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
			response.sendRedirect(request.getContextPath() + "/homePage/index.jsp");
		}
		
		if("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate(); //讓session失效，並解除已連結的物件
			System.out.println("session清空，將成為登出狀態");
			response.sendRedirect(request.getContextPath() + "/homePage/index.jsp");
		}
	}
}
