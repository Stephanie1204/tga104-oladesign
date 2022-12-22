package com.tibame.tga104.g2.oladesign.login.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tibame.tga104.g2.oladesign.admin.model.AdminService;
import com.tibame.tga104.g2.oladesign.admin.model.AdminVO;
@WebServlet("/login/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2446821516777883745L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("admin".equals(action)) {
			HttpSession session = req.getSession();
			if (session.getAttribute("adminVO") != null) {
				session.removeAttribute("adminVO");
				System.out.println("session刪除成功");
			}
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
//			String account = req.getParameter("account");
			String account = req.getParameter("account");
			System.out.println(account);

			String password = req.getParameter("password");
			System.out.println(password);
			
//			String id = "A001";
			
			
//			AdminService svc = new AdminService();
//			AdminVO adminVO = svc.getAdmin(account, password);
			
			
			AdminService svc = new AdminService();
			AdminVO adminVO = svc.adminLogin(account, password);
//			System.out.println(adminVO);
			
			
			

//			if(null != adminVO && admin_acc.equals(adminVO.getAccount()) && admin_pwd.equals(adminVO.getPassword())) {
			if(null != adminVO && account.equals(adminVO.getAccount()) && password.equals(adminVO.getPassword())) {
				session.setAttribute("adminVO", adminVO);
				session.setAttribute("adminName", adminVO.getAdminName());
				session.setAttribute("adminId", adminVO.getAdminId());
				System.out.println("session="+ session);
				System.out.println("adminId");
				System.out.println("adminName");				
				//登入成功跳轉到管理員首頁
//				String url = "/admin/select_page";
				String url = "/back-end/back-end-index.jsp";
				res.sendRedirect(req.getContextPath() + url);
			}else {
				errorMsgs.add("輸入錯誤");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminLogin.jsp");
				failureView.forward(req, res);
				return;
			}
		}
	}
}
