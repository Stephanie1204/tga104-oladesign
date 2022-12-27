package com.tibame.tga104.g2.oladesign.favorite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.tga104.g2.oladesign.favorite.bean.FavorVO;
import com.tibame.tga104.g2.oladesign.favorite.service.FavorService;

/**
 * Servlet implementation class FavorServlet
 */
@WebServlet("/favorite/FavorServlet")
public class FavorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavorService favorSvc;
	private Gson gson;
       
	@Override
	public void init() throws ServletException {
		favorSvc = new FavorService();
		gson = new Gson();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		FavorVO favorVO = gson.fromJson(request.getReader(), FavorVO.class);
		
		JsonObject respBody = new JsonObject();
		if(favorVO == null) {
			respBody.addProperty("successAdd", false);
		}else {
			respBody.addProperty("successAdd", favorSvc.addFavor(favorVO));
		}
		response.getWriter().write(respBody.toString());
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		FavorVO favorVO = gson.fromJson(request.getReader(), FavorVO.class);
		
		JsonObject respBody = new JsonObject();
		if(favorVO == null) {
			respBody.addProperty("successDel", false);
		}else {
			respBody.addProperty("successDel", favorSvc.delFavor(favorVO));
		}
		response.getWriter().write(respBody.toString());
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		FavorVO favorVO = gson.fromJson(request.getReader(), FavorVO.class);
		JsonObject respBody = new JsonObject();
		if(favorVO == null) {
			respBody.addProperty("getFavor", false);
		}else {
			respBody.addProperty("getFavor", gson.toJson(favorSvc.getOneAllFavor(favorVO)));
			System.out.println("getFavor="+ gson.toJson(favorSvc.getOneAllFavor(favorVO)));
		}
		response.getWriter().write(respBody.toString());	
	}

	
}
