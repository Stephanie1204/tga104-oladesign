//package com.tibame.tga104.g2.oladesign.promotion.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoService;
//
//@WebServlet("/promoList")
//public class PrmoListServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private PromoService service;
//
//	public void init() throws ServletException {
//		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//		service = applicationContext.getBean(PromoService.class);
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setAttribute("xxx", service.getAll());
//		req.getRequestDispatcher("promotion/promotion/select_page.jsp").forward(req, res);
//	}
//}
