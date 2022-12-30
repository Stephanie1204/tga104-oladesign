package com.tibame.tga104.g2.oladesign.order.controller;

import java.io.IOException;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.intermail.model.IntermailService;
import com.tibame.tga104.g2.oladesign.intermail.model.IntermailVO;
import com.tibame.tga104.g2.oladesign.order.model.*;


@WebServlet("/order/order.do")
public class Order_ForAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("CheckOne".equals(action)) {

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String orderId = req.getParameter("orderId");
			/*************************** 2.開始查詢資料 *****************************************/
			OrderService orderSvc = new OrderService();
			OrderBean orderBean = orderSvc.getCheckOne(orderId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderBean", orderBean);
			String url = "/order/checkOneOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		
		if ("Select".equals(action)) {
//			OrderBean orderBean = new OrderBean();
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer orderStatus= null;
			Integer shippingStatus = null;
//			Date startTime = null ;
//			Date overTime = null ;
			String orderId = req.getParameter("orderId");
			String comTaxId = req.getParameter("comTaxId");
			String memId = req.getParameter("memId");
			String receiver = req.getParameter("receiver");
//			String orderStatus = req.getParameter("orderStatus");
//			String shippingStatus = req.getParameter("shippingStatus");
			if(req.getParameter("orderStatus")!=null && req.getParameter("orderStatus") != "") {
				 orderStatus = Integer.valueOf(req.getParameter("orderStatus"));
			}
				
//			Integer orderStatus = Integer.valueOf(req.getParameter("orderStatus"));
			if(req.getParameter("shippingStatus")!=null && req.getParameter("shippingStatus") != "") {
				shippingStatus = Integer.valueOf(req.getParameter("shippingStatus"));
			}
			
//			Integer shippingStatus = Integer.valueOf(req.getParameter("shippingStatus"));

//			Timestamp orderTime = req.getParameter("orderTime");
			
			
			String startTime = req.getParameter("startTime");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//			try {
//				Date date = sdf.parse(startTime);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			String  overTime = req.getParameter("overTime");
//			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
//			try {
//				Date date = sdf1.parse(startTime);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			

			/*************************** 2.開始查詢資料 *****************************************/
			OrderService orderSvc = new OrderService();
//			List <OrderBean> list =orderSvc.getSearch(orderId, comTaxId, memId, receiver, orderStatus, shippingStatus);
			List <OrderBean> list =orderSvc.getSearch(orderId, comTaxId, memId, receiver, orderStatus, shippingStatus,startTime,overTime);
//			orderSvc.getSearch(orderId, comTaxId, memId, receiver, orderStatus, shippingStatus);orderTime
			
				

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			if(list != null) {
				req.setAttribute("list", list);
				String url = "/order/listSearchOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				successView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
				
//		if ("getOne_For_Display".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String memberId = req.getParameter("memberId");
//			/*************************** 2.開始查詢資料 *****************************************/
//			OrderService orderSvc = new OrderService();
//			List<OrderBean> orderBean = orderSvc.select_Mem(memberId);
//			if (orderBean == null) {
//				errorMsgs.add("查無資料");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/order/listAllOrder.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("orderBean", orderBean); 
//			String url = "/order/checkOneOrder.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); //
//			successView.forward(req, res);
//		}
	}
 
}
