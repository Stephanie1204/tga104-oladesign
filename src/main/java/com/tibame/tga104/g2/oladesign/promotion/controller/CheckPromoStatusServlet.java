package com.tibame.tga104.g2.oladesign.promotion.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@Deprecated
//@WebServlet("/CheckPromoStatus")
public class CheckPromoStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer = new Timer();

	@Override
	public void init() throws ServletException {
//		super.init();
//		timer.scheduleAtFixedRate(new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println("hello" + new Date());
//			}
//		}, new Date(), 1000 * 5);
	}

	@Override
	public void destroy() {
		super.destroy();
		timer.cancel();
	}

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().print("gogo");
//	}

}
