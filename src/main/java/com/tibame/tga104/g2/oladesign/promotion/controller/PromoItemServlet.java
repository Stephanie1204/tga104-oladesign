package com.tibame.tga104.g2.oladesign.promotion.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoService;
import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoVO;
import com.tibame.tga104.g2.oladesign.promotion.model.promoItem.PromoItemService;
import com.tibame.tga104.g2.oladesign.promotion.model.promoItem.PromoItemVO;

@WebServlet("/promoItems.do")
public class PromoItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//=====================Search Data======================================================================================
		
		if ("getOneForDisplay_byPromoID".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("promoId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入促銷編號");
				}
				// Send the use back to the form, if there were errors
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
					errorMsgs.add("促銷編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/promotion/select_page.jsp"); 
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				
				List<PromoItemVO> allItemList = new PromoItemService().getAllByPromoId(promoId);
				PromoVO promoVO =  new PromoService().getOnePromo(promoId);
				
				if(allItemList == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("allItemList", allItemList);// 資料庫取出的empVO物件,存入req
				req.setAttribute("promoVO", promoVO);
				String url = "/promotionItems/listAllPromoItemsByPromoId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}

// ======================查詢促銷明細==========================================================================================
		
		if ("getOneForDisplay_byProdID".equals(action)) {

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
					
				/***************************1.接收請求參數****************************************/
				String str = req.getParameter("prodId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("prodId","請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer prodId = null;
				try {
					prodId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("prodId","商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
						
				/***************************2.開始查詢資料****************************************/
				List<PromoItemVO> allPromoItemList = new PromoItemService().getAllByProdId(prodId);
				if(allPromoItemList == null) {
					errorMsgs.put("prodId","查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;
				}	
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("allPromoItemList", allPromoItemList); 
				String url = "/promotionItems/listAllPromoItemsByProdId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
// ======================新增資料=========================================================================================
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String promoId = req.getParameter("promoId");			
				if (promoId == null || promoId.trim().length() == 0) {
					errorMsgs.add("請重新登入????");
				}
				Integer promoId1 = Integer.valueOf(promoId);
			
				String prodId = req.getParameter("prodId");			
				if (prodId == null || prodId.trim().length() == 0) {
					errorMsgs.add("商品請勿空白");
				}
				Integer prodId1 = Integer.valueOf(prodId);
				
				String code = req.getParameter("code").trim();
				if (code == null || code.trim().length() == 0) {
					errorMsgs.add("折扣類型請勿空白");
				}
				
				String discount = req.getParameter("discount").trim();
				if (discount == null || discount.trim().length() == 0) {
					errorMsgs.add("折扣程度請勿空白");
				}
				Integer discount1 = Integer.valueOf(discount);

				PromoItemVO promoItemVO = new PromoItemVO();
				promoItemVO.setPromoId(promoId1);
				promoItemVO.setProdId(prodId1);
				promoItemVO.setCode(code);
				promoItemVO.setDiscount(discount1);
				
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promoItemVO", promoItemVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/promotionItems/listAllPromoItemsByPromoId.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
//				PromoItemVO promoItemSvc = new PromoItemService().addPromoItem(promoId1, prodId1, code, discount1);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/promotionItems/listAllPromoItemsByPromoId.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
//// ======================修改資料==========================================================================================
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.接收請求參數****************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.開始查詢資料****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/emp/update_emp_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//		}
		
//// ======================修改資料=========================================================================================
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//Integer empno = Integer.valueOf(req.getParameter("empno").trim());
//				
//String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}	
//				
//				java.sql.Date hiredate = null;
//				try {
//hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//
//Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEmpno(empno);
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//		}
//

// ======================刪除資料=========================================================================================
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer promoId = Integer.valueOf(req.getParameter("promoId"));
				Integer prodId = Integer.valueOf(req.getParameter("prodId"));
				
				/***************************2.開始刪除資料***************************************/
				new PromoItemService().deletePromoItem(promoId, prodId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/promotionItems/listAllPromoItemsByPromoId.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				req.setParameter("promoId", promoId);
//				successView.forward(req, res);
				
				String url = "/promotion/promoItem.do?action=getOneForDisplay_byPromoID&promoId=" + promoId;
				req.getRequestDispatcher(url).forward(req, res);
		}
		
	}
}
