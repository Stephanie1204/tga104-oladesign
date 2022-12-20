package com.tibame.tga104.g2.oladesign.login.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logOutServlet")
public class logOutServlet extends HttpServlet {

	private static final long serialVersionUID = -2446821516777883745L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("admin".equals(action)) {
			HttpSession session = req.getSession();
				session.removeAttribute("administratorVO");
				session.invalidate();
				System.out.println("session刪除成功");
				
				System.out.println("sassion=" + session);
				System.out.println("登出成功");
				res.sendRedirect(req.getContextPath() + "/back-end/adminLogin.jsp");
			}
	}
}