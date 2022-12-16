package com.tibame.tga104.g2.oladesign.intermail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.intermail.model.IntermailService;
import com.tibame.tga104.g2.oladesign.intermail.model.IntermailVO;


public class IntermailServlet extends HttpServlet {
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
			String str = req.getParameter("interMailId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入站內信編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/intermail/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			String interMailId = null;
			try {
				interMailId = (str);
			} catch (Exception e) {
				errorMsgs.add("站內信編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/intermail/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			IntermailService intermailSvc = new IntermailService();
			IntermailVO intermailVO = intermailSvc.getOneIntermail(interMailId);
			if (intermailVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/intermail/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("intermailVO", intermailVO); 
			String url = "/intermail/listOneIntermail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdmin.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("pass2");

			/*************************** 1.接收請求參數 ****************************************/
			String interMailId = (req.getParameter("interMailId"));

			/*************************** 2.開始查詢資料 ****************************************/
			IntermailService intermailSvc = new IntermailService();
			IntermailVO intermailVO = intermailSvc.getOneIntermail(interMailId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("intermailVO", intermailVO); // 資料庫取出的empVO物件,存入req
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

String interMailId = req.getParameter("interMailId");
			String enameReg = "^[(a-zA-Z0-9_)]{1,10}$";
			if (interMailId == null || interMailId.trim().length() == 0) {
				errorMsgs.add("站內信編號: 請勿空白");
			} else if (!interMailId.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("站內信編號: 只能是數字 , 且長度必需為4之間");
			}
			System.out.println("會員編號上");
			//測試用
//			Integer tempMemId = null;
//			Integer  memId =  0;
//			if(req.getParameter("memId") == null || req.getParameter("memId").trim().length() == 0) {
//				errorMsgs.add("會員編號: 請勿空白");
//				memId = tempMemId;
			
//			}
		
//			String memID1 = req.getParameter("memId");
//			if( memID1 == null || memID1.trim().length() == 0) {
//				errorMsgs.add("會員編號: 請勿空白");
//			}else {
//				 memId = Integer.parseInt(memID1);
//			}
			Integer  memId =  0;
			String tempmemId = req.getParameter("memId");
			if(tempmemId== null || tempmemId.trim().length() == 0) {
				errorMsgs.add("會員編號: 請勿空白");
				}else {
					memId = Integer.parseInt(tempmemId);
				}
			
			
//Integer memId = req.getParameter(memId);
//			String enameReg1 = "^[0-9)]{1,10}$";
//			if (textmemId == null || textmemId.trim().length() == 0) {
//				errorMsgs.add("會員編號: 請勿空白");
//			} else if (!textmemId.trim().matches(enameReg1)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("會員編號: 只能是數字 , 且長度必需為6");
//			}
//			Integer memId = new Integer(textmemId);
//			try {
//				memId = new Integer(textmemId);
//			}catch(Exception e) {
//				errorMsgs.add("會員編號: 只能是數字11 , 且長度必需為6");
//			}
			
			
String adminId = req.getParameter("adminId");
			String enameReg2 = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{4}$";
			if (adminId == null || adminId.trim().length() == 0) {
				errorMsgs.add("管理員編號: 請勿空白");
			} else if (!adminId.trim().matches(enameReg2)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員編號: 只能為A001字 , 且長度必需為4");
			}
String numQue = req.getParameter("numQue");
			String enameReg4 = "^[0-9)]{1}$";
			if (numQue == null || numQue.trim().length() == 0) {
				errorMsgs.add("問題類型編號: 請勿空白");
			} else if (!numQue.trim().matches(enameReg4)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("問題類型編號: 只能是數字 , 且長度必須為1");
			}
String conTent = req.getParameter("conTent");
			String enameReg3 = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{0,1000}$";
			if (conTent == null || conTent.trim().length() == 0) {
				errorMsgs.add("內容: 請勿空白");
			} else if (!conTent.trim().matches(enameReg3)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("內容: 長度必需在1到1000之間");
			}
			
			
//			String issendtext =req.getParameter("isSend");
////Integer memId = req.getParameter(memId);
//			if (issendtext == null || issendtext.trim().length() == 0) {
//				errorMsgs.add("會員編號 : 請勿空白");
//			} else if (!textmemId.trim().matches(enameReg1)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("會員編號: 只能是數字 , 且長度必需為6");
//			}
//			Integer memId = Integer.parseInt(textmemId);
//			try {
//				memId = new Integer(textmemId);
//			}catch(Exception e) {
//				errorMsgs.add("會員編號: 只能是數字11 , 且長度必需為6");
//			}


			
			IntermailVO intermailVO = new IntermailVO();
			intermailVO.setInterMailId(interMailId);
			intermailVO.setMemId(memId);
			intermailVO.setAdminId(adminId);
			intermailVO.setNumQue(numQue);
			intermailVO.setConTent(conTent);
//			intermailVO.setIsSend(isSend);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("intermailVO", intermailVO); //
				RequestDispatcher failureView = req.getRequestDispatcher("/intermail/addIntermail.jsp");
				failureView.forward(req, res);

				return;
				
			}

			/*************************** 2.開始新增資料 ***************************************/
			IntermailService intermailSvc = new IntermailService();
			System.out.println("開始新增資料");
			intermailVO = intermailSvc.addIntermail(interMailId, memId, adminId,numQue,conTent);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/intermail/listAllIntermail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			String interMailId = (req.getParameter("interMailId"));

			/*************************** 2.開始刪除資料 ***************************************/
			IntermailService intermailSvc = new IntermailService();
			intermailSvc.deleteIntermail(interMailId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/intermail/listAllIntermail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}
}
