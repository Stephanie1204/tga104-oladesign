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
		
//=======註冊驗證=======
		String emailToken = request.getParameter("emailToken");
		if(emailToken != null) {
//			錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			Jedis jedis = new Jedis("localhost", 6379);	
			jedis.select(10);
			String memIdJedis = jedis.get(emailToken);
			System.out.println("memIdJedis = " + jedis.get(emailToken));
			System.out.println("驗證碼存活時間:" + jedis.ttl(emailToken)); 
			
			Integer memId = null;
			if(memIdJedis != null) {
				memId = Integer.valueOf(memIdJedis);
			}else{
				if(jedis.ttl(emailToken) == -2) { //-1表示資料永久存活；-2表示該資料不存在
					System.out.println("連結已逾時，或驗證碼已刪除");	
					request.setAttribute("vericodeDel", "true"); //設定驗證碼已刪除標記
					RequestDispatcher errView = request.getRequestDispatcher("/member/login.jsp");
					errView.forward(request, response);
					jedis.close();
					return;
				}else {
					System.out.println("emailToken key不存在");
				}
			}
			System.out.println("取得驗證memId = " + memId); 
			
			MemberService memSvc = new MemberService();		
			
			if(memSvc.getOneMember(memId) != null) {
				Boolean isActive = true;
				memSvc.activeMember(memId, isActive);
				//login.jsp alert 註冊驗證成功
				request.setAttribute("success", "true"); //設定註冊成功標記
				RequestDispatcher successView = request.getRequestDispatcher("/member/login.jsp");
				successView.forward(request, response);
				jedis.del(emailToken);
				jedis.close();	
				System.out.println("驗證成功");
				return;
			}else {
				errorMsgs.add("驗證有誤，請點選重新發送驗證信或重新註冊");
				RequestDispatcher errView = request.getRequestDispatcher("/member/sendMail.jsp");
				errView.forward(request, response);
				jedis.close();
				return;
			}
		}else {
			System.out.println("未收到emailToken");
		}
		
		
//=======密碼重設驗證=======
		String reset = request.getParameter("reset");
		
		if(reset != null) {
//			錯誤訊息
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			Jedis jedis = new Jedis("localhost", 6379);	
			jedis.select(11);
			
			String memIdJedis = jedis.get(reset);
			System.out.println("memIdJedis = " + jedis.get(reset));
			System.out.println("驗證碼存活時間:" + jedis.ttl(reset));
			Integer memId = null;
			if(memIdJedis != null) {
				memId = Integer.valueOf(memIdJedis);
			}else{
				if(jedis.ttl(reset) == -2) { //-1表示資料永久存活；-2表示該資料不存在
					System.out.println("連結已逾時，或驗證碼已刪除");	
					request.setAttribute("vericodeDelReset", "true"); //設定驗證碼已刪除標記
					RequestDispatcher errView = request.getRequestDispatcher("/member/login.jsp");
					errView.forward(request, response);
					jedis.close();
					return;
				}else {
					System.out.println("reset key不存在");
				}
			}
			
			MemberService memSvc = new MemberService();
			
			if(memSvc.getOneMember(memId) != null) {
				request.setAttribute("memId", memId); //將memId傳到recoverpwd.jsp
				RequestDispatcher successView = request.getRequestDispatcher("/member/recoverpwd.jsp");
				successView.forward(request, response);
				jedis.del(reset);
				jedis.close();	
				System.out.println("驗證成功，可以重設密碼");
				return;
			}else {
				errorMsgs.add("驗證有誤，請點選重新發送驗證信或重新註冊");
				RequestDispatcher errView = request.getRequestDispatcher("/member/forgetpwd.jsp");
				errView.forward(request, response);
				jedis.close();
				System.out.println("驗證有誤");	
				return;
			}			
		}else {
			System.out.println("未收到reset");
		}
	}
}
