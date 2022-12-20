package com.tibame.tga104.g2.oladesign.product_type.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.product_type.model.Product_typeService;
import com.tibame.tga104.g2.oladesign.product_type.model.Product_typeVO;

@WebServlet("/product_type/product_type.do")
public class Product_typeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
System.out.println("STYLE");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("typeCode");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入類別類型編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product_type/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			String typeCode = null;
			try {
				typeCode = (str);
			} catch (Exception e) {
				errorMsgs.add("類別類型編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product_type/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			Product_typeService product_type = new Product_typeService();
			Product_typeVO product_typeVO = product_type.getOneProduct_type(typeCode);
			if (product_typeVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product_type/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("product_typeVO", product_typeVO); // 
			String url = "/product_type/listOneProduct_type.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdmin.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			String typeCode = (req.getParameter("typeCode"));

			/*************************** 2.開始查詢資料 ****************************************/
			Product_typeService product_typeSvc = new Product_typeService();
			Product_typeVO product_typeVO = product_typeSvc.getOneProduct_type(typeCode);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("product_typeVO", product_typeVO); // 資料庫取出的empVO物件,存入req
			String url = "/product_type/update_intermail_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String typeCode = req.getParameter("typeCode");
			String enameReg = "^[(a-zA-Z0-9_)]{3,10}$";
			if (typeCode == null || typeCode.trim().length() == 0) {
				errorMsgs.add("類型編號: 請勿空白");
			} else if (!typeCode.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("類型編號: 只能是英文、數字 , 且長度必需在3到10之間");
			}

			String typeName = req.getParameter("typeName");
			String enameReg2 = "^[(\u4e00-\u9fa5)]{2,10}$";
			if (typeName == null || typeName.trim().length() == 0) {
				errorMsgs.add("類型名稱選項: 請勿空白");
			} else if (!typeName.trim().matches(enameReg2)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("類別名稱選項: 只能是中文 , 且長度必需在2到10之間");
			}
			Product_typeVO product_typeVO = new Product_typeVO();
			product_typeVO.setTypeCode(typeCode);
			product_typeVO.setTypeName(typeName);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("product_typeVO", product_typeVO); //
				RequestDispatcher failureView = req.getRequestDispatcher("/product_type/addProduct_type.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Product_typeService product_typeSvc = new Product_typeService();
			product_typeVO = product_typeSvc.addProduct_type(typeCode, typeName);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/product_type/listAllProduct_type.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			String typeCode = (req.getParameter("typeCode"));

			/*************************** 2.開始刪除資料 ***************************************/
			Product_typeService poduct_typeSvc = new Product_typeService();
			poduct_typeSvc.deleteProduct_type(typeCode);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/product_type/listAllProduct_type.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}
}
