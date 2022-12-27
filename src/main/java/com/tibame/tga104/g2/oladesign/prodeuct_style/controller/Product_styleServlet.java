package com.tibame.tga104.g2.oladesign.prodeuct_style.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.prodeuct_style.model.Product_styleService;
import com.tibame.tga104.g2.oladesign.prodeuct_style.model.Product_styleVO;
@WebServlet("/product_style/product_style.do")
public class Product_styleServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("styleCode");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入地區類別編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product_style/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			String styleCode = null;
			try {
				styleCode = (str);
			} catch (Exception e) {
				errorMsgs.add("區類別編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product_style/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			Product_styleService product_style = new Product_styleService();
			Product_styleVO product_styleVO = product_style.getOneProduct_style(styleCode);
			if (product_styleVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product_style/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("product_styleVO", product_styleVO); // 
			String url = "/product_style/listOneProduct_style.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdmin.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			String styleCode = (req.getParameter("styleCode"));

			/*************************** 2.開始查詢資料 ****************************************/
			Product_styleService product_styleSvc = new Product_styleService();
			Product_styleVO product_styleVO = product_styleSvc.getOneProduct_style(styleCode);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("product_styleVO", product_styleVO); // 資料庫取出的empVO物件,存入req
			String url = "/product_style/update_intermail_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String styleCode = req.getParameter("styleCode");
			String enameReg = "^[(a-zA-Z0-9_)]{4,10}$";
			if (styleCode == null || styleCode.trim().length() == 0) {
				errorMsgs.add("類型編號: 請勿空白");
			} else if (!styleCode.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("類型編號: 只能是英文、數字 , 且長度必需在4到10之間");
			}

			String styleName = req.getParameter("styleName");
			String enameReg2 = "^[(\u4e00-\u9fa5)]{2,10}$";
			if (styleName == null || styleName.trim().length() == 0) {
				errorMsgs.add("地區類別名稱選項: 請勿空白");
			} else if (!styleName.trim().matches(enameReg2)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("地區類別名稱選項: 只能是中文 , 且長度必需在2到10之間");
			}
			Product_styleVO product_styleVO = new Product_styleVO();
			product_styleVO.setStyleCode(styleCode);
			product_styleVO.setStyleName(styleName);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("product_styleVO", product_styleVO); //
				RequestDispatcher failureView = req.getRequestDispatcher("/product_style/addProduct_style.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Product_styleService product_styleSvc = new Product_styleService();
			product_styleVO = product_styleSvc.addProduct_style(styleCode, styleName);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/product_style/listAllProduct_style.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			String styleCode = (req.getParameter("styleCode"));

			/*************************** 2.開始刪除資料 ***************************************/
			Product_styleService poduct_styleSvc = new Product_styleService();
			poduct_styleSvc.deleteProduct_style(styleCode);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/product_style/listAllProduct_style.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}
}
