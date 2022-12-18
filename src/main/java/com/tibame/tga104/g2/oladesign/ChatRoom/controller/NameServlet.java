package com.tibame.tga104.tga104g2.ChatRoom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/chat.do")
public class NameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String mem_0 = req.getParameter("mem_0");
		String mem_1 = req.getParameter("mem_1");
		
		req.setAttribute("mem_0", mem_0);
		req.setAttribute("mem_1", mem_1);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/chat.jsp");
		dispatcher.forward(req, res);
	}
}
