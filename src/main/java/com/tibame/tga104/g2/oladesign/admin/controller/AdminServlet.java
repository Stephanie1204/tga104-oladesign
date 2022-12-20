package com.tibame.tga104.g2.oladesign.admin.controller;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga104.g2.oladesign.admin.model.AdminService;
import com.tibame.tga104.g2.oladesign.admin.model.AdminVO;
@WebServlet("/admin/admin.do")
public class AdminServlet extends HttpServlet {

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
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("adminId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入管理員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/admin/select_page.jsp");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/index-admin.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			String adminId = null;
			try {
				adminId = (str);
			} catch (Exception e) {
				errorMsgs.add("管理員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/admin/select_page.jsp");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/index-admin.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.getOneAdmin(adminId);
			if (adminVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/admin/select_page.jsp");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/index-admin.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("adminVO", adminVO); //
			String url = "/admin/listOneAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdmin.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 ****************************************/
			String adminId = (req.getParameter("adminId"));
			/*************************** 2.開始查詢資料 ****************************************/
			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.getOneAdmin(adminId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("adminVO", adminVO); 
			String url = "/admin/update_admin_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
			
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String adminId = req.getParameter("adminId");
//			String enameReg = "^[(a-zA-Z0-9_)]{4,10}$";
//			if (adminId == null || adminId.trim().length() == 0) {
//				errorMsgs.add("管理員編號: 請勿空白");
//			} else if (!adminId.trim().matches(enameReg)) {// 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("管理員編號: 只能是英文字母、數字和_ , 且長度必需在4到10之間");
//			}

			String adminName = req.getParameter("adminName");
			String enameReg2 = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (adminName == null || adminName.trim().length() == 0) {
				errorMsgs.add("管理員名稱: 請勿空白");
			} else if (!adminName.trim().matches(enameReg2)) {// 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String account = req.getParameter("account").trim();
			String enameReg1 = "^[(a-zA-Z0-9_)]{5,30}$";
			String enameReg11 ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			} else if (!account.trim().matches(enameReg11)) {// 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員帳號: 只能是英文字母、數字和_ , 且長度必需在5到30之間");
			}

			String password = req.getParameter("password").trim();
			if (password == null || account.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else if (!password.trim().matches(enameReg1)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("管理員密碼: 只能是英文字母、數字和_ , 且長度必需在6到10之間");
			}

			AdminVO adminVO = new AdminVO();
			adminVO.setAdminId(adminId);
			adminVO.setAdminName(adminName);
			adminVO.setAccount(account);
			adminVO.setPassword(password);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adminVO", adminVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/update_admin_input.jsp");
				failureView.forward(req, res);
				return; 
			}
			/*************************** 2.開始修改資料 *****************************************/
			AdminService adminSvc = new AdminService();
			adminVO = adminSvc.updateAdmin(adminId, adminName, account, password);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("adminVO", adminVO); 
			String url = "/admin/listOneAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String adminId = req.getParameter("adminId");
			String enameReg = "^[(a-zA-Z0-9_)]{4,10}$";
			if (adminId == null || adminId.trim().length() == 0) {
				errorMsgs.add("管理員編號: 請勿空白");
			} else if (!adminId.trim().matches(enameReg)) {
				errorMsgs.add("管理員編號: 只能是英文字母、數字和_ , 且長度必需在4到10之間");
			}
			
			String adminName = req.getParameter("adminName");
			String enameReg2 = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (adminName == null || adminName.trim().length() == 0) {
				errorMsgs.add("管理員名稱: 請勿空白");
			} else if (!adminName.trim().matches(enameReg2)) { 
				errorMsgs.add("管理員名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String account = req.getParameter("account").trim();
			
			String enameReg11 ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			} else if (!account.trim().matches(enameReg11)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("管理員帳號: 只能是英文字母、數字和_@ , 且長度必需在5到30之間");
			}

			String password = req.getParameter("password").trim();
			String enameReg1 = "^[(a-zA-Z0-9_)]{5,30}$";
			if (password == null || account.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else if (!password.trim().matches(enameReg1)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("管理員密碼: 只能是英文字母、數字和_ , 且長度必需在6到10之間");
			}

			AdminVO adminVO = new AdminVO();
			adminVO.setAdminId(adminId);
			adminVO.setAdminName(adminName);
			adminVO.setAccount(account);
			adminVO.setPassword(password);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adminVO", adminVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/addAdmin.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			AdminService adminSvc = new AdminService();
			adminVO = adminSvc.addAdmin(adminId, adminName, account, password);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/admin/listAllAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			String adminId = (req.getParameter("adminId"));

			/*************************** 2.開始刪除資料 ***************************************/
			AdminService adminSvc = new AdminService();
			adminSvc.deleteAdmin(adminId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/admin/listAllAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}
}
