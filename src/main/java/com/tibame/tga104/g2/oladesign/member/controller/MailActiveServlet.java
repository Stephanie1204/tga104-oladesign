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

import com.tibame.tga104.g2.oladesign.member.service.MemberService;

import redis.clients.jedis.Jedis;


@WebServlet("/member/MemberMailServlet")
public class MailActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String emailToken = request.getParameter("emailToken");
		
		Jedis jedis = new Jedis("localhost", 6379);	
		jedis.select(10);
		String memIdJedis = jedis.get(emailToken);
		System.out.println("memIdJedis = " + jedis.get(emailToken));
		Integer memId = Integer.valueOf(memIdJedis);
		
		System.out.println("memId = " + memId); 
		System.out.println("驗證碼存活時間:" + jedis.ttl(emailToken)); 
		
		List<String> errorMsgs = new LinkedList<String>();
		
		MemberService memSvc = new MemberService();		
		
		if(memSvc.getOneMember(memId) != null) {
			Boolean isActive = true;
			memSvc.activeMember(memId, isActive);
			//alert 註冊驗證成功
			request.setAttribute("success", "true");
			RequestDispatcher successView = request.getRequestDispatcher("/member/login.jsp");
			successView.forward(request, response);
			jedis.close();	
			System.out.println("驗證成功");
		}else if(memIdJedis == null) {
			errorMsgs.add("連結已逾時，請點選重新發送驗證信");
			System.out.println("連結已逾時");	
		}else {
			errorMsgs.add("驗證有誤，請點選重新發送驗證信或重新註冊");
			System.out.println("驗證有誤");	
		}
		
		if(!errorMsgs.isEmpty()) {
			RequestDispatcher errView = request.getRequestDispatcher("/member/sendMail.jsp");
			errView.forward(request, response);
			jedis.close();
			return;
		}
		
		
	}
}
