package com.tibame.tga104.g2.oladesign.promotion.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoService;
import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoVO;


@WebServlet("/promo.do")
public class PromoServlet extends HttpServlet {

	private PromoService promoSvc;
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = (ApplicationContext)
				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		this.promoSvc = context.getBean("promoService", PromoService.class);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println("hello");
		
//=====================查資料======================================================================================

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("promoId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("promoId","請輸入促銷專案編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer promoId = null;
				try {
					promoId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("promoId","促銷專案編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				//PromoService promoSvc = new PromoService();
				PromoVO promoVO = promoSvc.getOnePromo(promoId);
				if (promoVO == null) {
					errorMsgs.put("promoId","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("promoVO", promoVO); // 資料庫取出的empVO物件,存入req
				String url = "/promotion/listOnePromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer promoId = Integer.valueOf(req.getParameter("promoId"));
				
				/***************************2.開始查詢資料****************************************/
				//PromoService promoSvc = new PromoService();
				PromoVO promoVO = promoSvc.getOnePromo(promoId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?promoId="  +promoVO.getPromoId()+
						       "&comTaxId="  +promoVO.getComTaxId()+
						       "&promoName="    +promoVO.getPromoName()+
						       "&startDate="+promoVO.getStartDate()+
						       "&endDate="    +promoVO.getEndDate()+
						       "&coupon=" +promoVO.getCoupon();
				String url = "/promotion/update_promo_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer promoId = Integer.valueOf(req.getParameter("promoId").trim());
				String comTaxId = String.valueOf(req.getParameter("comTaxId").trim());
				
				String promoName = req.getParameter("promoName");
				if (promoName == null || promoName.trim().length() == 0) {
					errorMsgs.put("job","促銷專案名稱請勿空白");
				}
				
				// FIXME dev code
//				LocalDateTime startDate = LocalDateTime.now();
//				java.sql.Date startDate = req.getParameter("startDate").trim();
//				if (startDate == null || startDate.trim().length() == 0) {
//					errorMsgs.put("job","促銷專案開始日期請勿空白");
//				}
				java.sql.Date startDate = null;
				try {
					startDate = java.sql.Date.valueOf(req.getParameter("startDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("startDate","請輸入促銷開始日期");
				}
				
				
				// FIXME dev code
//				LocalDateTime endDate = LocalDateTime.now();
//				java.sql.Date endDate = req.getParameter("endDate").trim();
//				if (endDate == null || endDate.trim().length() == 0) {
//					errorMsgs.put("job","促銷專案結束日期請勿空白");
//				}
				java.sql.Date endDate = null;
				try {
					endDate = java.sql.Date.valueOf(req.getParameter("endDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("endDate","請輸入促銷開始日期");
				}
				
				String coupon = req.getParameter("coupon").trim();
				if (coupon == null || coupon.trim().length() == 0) {
					errorMsgs.put("job","促銷專案折扣碼請勿空白");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotion/update_promo_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//PromoService promoSvc = new PromoService();
//				PromoVO promoVO = promoSvc.update(promoId, comTaxId, promoName, startDate, endDate, coupon);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("promoVO", promoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/promotion/listOnePromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String promoName = req.getParameter("promoName");
				if (promoName == null || promoName.trim().length() == 0) {
					errorMsgs.put("promoName","促銷專案名稱請勿空白");
				}
				
				String comTaxId = req.getParameter("comTaxId");
				if (comTaxId == null || comTaxId.trim().length() == 0) {
					errorMsgs.put("comTaxId","廠商統編請勿空白");
				}
				
				// FIXME dev code
//				LocalDateTime startDate = LocalDateTime.now();
				
				
				java.sql.Date startDate = null;
				try {
					startDate = java.sql.Date.valueOf(req.getParameter("startDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("startDate","請輸入促銷開始日期");
				}
				
				
				// FIXME dev code
//				LocalDateTime endDate = LocalDateTime.now();

				java.sql.Date endDate = null;
				try {
					endDate = java.sql.Date.valueOf(req.getParameter("endDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("endDate","請輸入促銷結束日期");
				}
				
				String coupon = req.getParameter("coupon");
				if (coupon == null || coupon.trim().length() == 0) {
					errorMsgs.put("coupon","折扣碼請勿空白");
				}
				
				PromoVO promoVO = new PromoVO();
				promoVO.setPromoName(promoName);
				promoVO.setComTaxId(comTaxId);
				promoVO.setStartDate(startDate);
				promoVO.setEndDate(endDate);
				promoVO.setCoupon(coupon);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/promotion/addPromo.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
//				promoVO = this.promoSvc.addPromo(promoName, comTaxId, startDate, endDate, coupon);

//				boolean isSuccess = promoSvc.addPromo(promoName, comTaxId, startDate, endDate, coupon);
				
//				if(isSuccess) {
//					// 新增成功的結果
//					return ;
//				} else {
//					// 原頁提醒使用者重新填寫
//					System.out.println("新增失敗");
//				}
//				
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/promotion/listAllPromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer promoId = Integer.valueOf(req.getParameter("promoId"));
				
				/***************************2.開始刪除資料***************************************/
				//PromoService promoSvc = new PromoService();
//				promoSvc.deletePromo(promoId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/promotion/listAllPromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
		
		


//		res.setContentType("text/html;charset=UTF-8");
//
//		List<PromoVO> list = new PromoService().getAll();
////		PrintWriter out = res.getWriter();
////		out.print(list);
////		for (Promotion_VO promotion_VO : list) {
////			System.out.println(promotion_VO);
////		}
//		
//		
//		req.setAttribute("pList", list);
//		
//		
//		String url = "/promotion/promotionList.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url);
//		successView.forward(req, res);
//
//	}

}
