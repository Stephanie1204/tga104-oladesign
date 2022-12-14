package com.tibame.tga104.g2.oladesign.promotion.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.promotion.model.promoStatus.PromoStatusService;
import com.tibame.tga104.g2.oladesign.promotion.model.promoStatus.PromoStatusVO;

@WebServlet("/PromoStatusServlet")
public class PromoStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			String code = String.valueOf(req.getParameter("CODE"));

			/*************************** 2.開始查詢資料 ****************************************/
			PromoStatusService promoStatusSvc = new PromoStatusService();
//			PromoStatusVO promoStatusVO = promoStatusSvc.getOnePromo(code);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			String param = "?code=" + promoStatusVO.getCode() + "&name=" + promoStatusVO.getName();

//			String url = "/promoStatus/update_emp_input.jsp??????????????" + param;
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//			successView.forward(req, res);
		}

		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			String code = String.valueOf(req.getParameter("CODE"));

			/*************************** 2.開始刪除資料 ***************************************/
			PromoStatusService promoStatusSvc = new PromoStatusService();
			promoStatusSvc.deletePromoStatus(code);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/promoStatus/listAllPromoStatus.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}
	
}
