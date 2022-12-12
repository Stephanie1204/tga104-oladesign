package com.tibame.tga104.login_test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ToGetComTaxIdService")
public class ToGetComTaxIdService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		Integer memId = Integer.valueOf(req.getParameter("memId"));
		
		
		HttpSession session = req.getSession();
		ToGetComTaxIdVO toGetComTaxIdVO = new getComTaxIdJNDIDAO().getComTaxId(memId);
		
		session.setAttribute("memId", toGetComTaxIdVO.getMemId());
		session.setAttribute("comTaxId", toGetComTaxIdVO.getComTaxId());
		
//		String url = "/promotion/select_page.jsp";
		String url = "/logInTest.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		
		
		
		
	}
	
	

	

}
