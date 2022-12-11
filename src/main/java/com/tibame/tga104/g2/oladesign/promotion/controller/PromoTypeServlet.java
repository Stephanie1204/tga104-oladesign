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

import com.tibame.tga104.g2.oladesign.promotion.model.promoType.PromoTypeService;
import com.tibame.tga104.g2.oladesign.promotion.model.promoType.PromoTypeVO;

@WebServlet("/PromoTypeServlet")
public class PromoTypeServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			String code = String.valueOf(req.getParameter("CODE"));

			/*************************** 2.開始查詢資料 ****************************************/
			PromoTypeService promoTypeSvc = new PromoTypeService();
			PromoTypeVO promoTypeVO = promoTypeSvc.getOnePromo(code);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?code=" + promoTypeVO.getCode() + "&name=" + promoTypeVO.getName();

			String url = "/promoType/update_emp_input.jsp??????????????" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String code = String.valueOf(req.getParameter("CODE").trim());

			String name = req.getParameter("name");
//				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (name == null || name.trim().length() == 0) {
				errorMsgs.put("name", "促銷類型名稱: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.put("ename","員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp???????????????");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				PromoTypeService promoTypeSvc = new PromoTypeService();
				PromoTypeVO promoTypeVO = promoTypeSvc.updatePromoType(code, name);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("PromoTypeVO", promoTypeVO); // 資料庫update成功後,正確的的PromoTypeVO物件,存入req
				String url = "/emp/listOneEmp.jsp?????????????";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String code = req.getParameter("code");
			String codeReg = "^[(A-Z][0-9]{3}$";
			if (code == null || code.trim().length() == 0) {
				errorMsgs.put("code", "促銷類型編號: 請勿空白");
			} else if (!code.trim().matches(codeReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("code", "促銷類型編號: 為英文大寫開頭+3碼數字");
			}

			String name = req.getParameter("name").trim();
			if (name == null || name.trim().length() == 0) {
				errorMsgs.put("name", "促銷類型名稱請勿空白");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/promoType/addpromoType.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			PromoTypeService promoTypeSvc = new PromoTypeService();
			promoTypeSvc.addPromoType(code, name);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/promoType/listAllpromoType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			String code = String.valueOf(req.getParameter("CODE"));

			/*************************** 2.開始刪除資料 ***************************************/
			PromoTypeService promoTypeSvc = new PromoTypeService();
			promoTypeSvc.deletePromoType(code);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/promoType/listAllPromoType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}
}
