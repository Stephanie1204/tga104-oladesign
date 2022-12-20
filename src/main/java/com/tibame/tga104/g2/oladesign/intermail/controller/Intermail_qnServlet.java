package com.tibame.tga104.g2.oladesign.intermail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.intermail.model.Intermail_qnService;
import com.tibame.tga104.g2.oladesign.intermail.model.Intermail_qnVO;
@WebServlet("/intermail_qn/intermail_qn.do")
public class Intermail_qnServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("numQue");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入問題類型編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/intermail_qn/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			String numQue = null;
			try {
				numQue = (str);
			} catch (Exception e) {
				errorMsgs.add("問題類型編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/intermail_qn/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			Intermail_qnService intermail_qnSvc = new Intermail_qnService();
			Intermail_qnVO intermail_qnVO = intermail_qnSvc.getOneIntermail_qn(numQue);
			if (intermail_qnVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/intermail_qn/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("intermail_qnVO", intermail_qnVO); // 資料庫取出的empVO物件,存入req
			String url = "/intermail_qn/listOneIntermail_qn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdmin.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			String numQue = (req.getParameter("numQue"));

			/*************************** 2.開始查詢資料 ****************************************/
			Intermail_qnService intermail_qnSvc = new Intermail_qnService();
			Intermail_qnVO intermail_qnVO = intermail_qnSvc.getOneIntermail_qn(numQue);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("intermail_qnVO", intermail_qnVO); // 資料庫取出的empVO物件,存入req
			String url = "/intermail/update_intermail_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String numQue = req.getParameter("numQue");
			String enameReg = "^[0-9)]{1,10}$";
			if (numQue == null || numQue.trim().length() == 0) {
				errorMsgs.add("問題類型編號: 請勿空白");
			} else if (!numQue.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("問題類型編號: 只能是數字 , 且長度必需在1到10之間");
			}

			String type = req.getParameter("type");
			String enameReg2 = "^[(\\u4e00-\\u9fa5)]{2,10}$";
			if (type == null || type.trim().length() == 0) {
				errorMsgs.add("問題類型選項: 請勿空白");
			} else if (!type.trim().matches(enameReg2)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("問題類型選項: 只能是中文 , 且長度必需在2到10之間");
			}
			Intermail_qnVO intermail_qnVO = new Intermail_qnVO();
			intermail_qnVO.setNumQue(numQue);
			intermail_qnVO.setType(type);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("intermail_qnVO", intermail_qnVO); //
				RequestDispatcher failureView = req.getRequestDispatcher("/intermail_qn/addIntermail_qn.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Intermail_qnService intermail_qnSvc = new Intermail_qnService();
			intermail_qnVO = intermail_qnSvc.addIntermail_qn(numQue, type);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/intermail_qn/listAllIntermail_qn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			String numQue = (req.getParameter("numQue"));

			/*************************** 2.開始刪除資料 ***************************************/
			Intermail_qnService intermail_qnSvc = new Intermail_qnService();
			intermail_qnSvc.deleteIntermail_qn(numQue);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/intermail_qn/listAllIntermail_qn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}

}
