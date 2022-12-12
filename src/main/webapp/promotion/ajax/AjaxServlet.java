package com.tibame.tga104.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html;charset=UTF-8");
    	
    	resp.addHeader("Access-Control-Allow-Origin","*");
    	String promoId = req.getParameter("promoId");
    	System.out.println("此次請求action為: " + promoId);
    	
    	resp.setContentType("application/json");
    	resp.getWriter().print("{\"promoId\": \" " + promoId + "\"}");
    	
//    	{
//    		"res": "its ajax res"
//    	}
    }

}
