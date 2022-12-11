package com.tibame.tga104.g2.oladesign.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;


@WebServlet("/member/MemberMailServlet")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memUID = request.getParameter("activeCode");
		String id = request.getParameter("id");
		Jedis jedis = new Jedis("localhost", 6379);	
		String memUIDJedis = jedis.get(id);
		
		if(memUIDJedis == null) {
			System.out.println("連結已逾時");
			return;
		}else if(memUID.equals(memUIDJedis)) {
			System.out.println("驗證成功");
		}else {
			System.out.println("驗證有誤");
			return;
		}
		
		jedis.close();
		
		RequestDispatcher successView = request.getRequestDispatcher("/member/login.jsp");
		successView.forward(request, response);
	}
}
