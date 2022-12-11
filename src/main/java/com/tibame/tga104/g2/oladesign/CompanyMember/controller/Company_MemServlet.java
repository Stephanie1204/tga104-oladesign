package com.tibame.tga104.g2.oladesign.CompanyMember.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tibame.tga104.g2.oladesign.CompanyCommon.MemberCheckService;
import com.tibame.tga104.g2.oladesign.CompanyMember.service.Company_MemService;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemByCheckVO;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;

@WebServlet("/CompanyBackEnd/company_memberdo")
@MultipartConfig
public class Company_MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String account = (String) session.getAttribute("account");
		String action = req.getParameter("action");
		// 點選菜單攔中的"賣家基本資料"按鈕時判斷該會員是否已開通賣家功能,已開通=>帶入基本資料,未開通=>空白表單
		if ("doGetCompantMembetInfo".equals(action)) {
			String memId = req.getParameter("memId");
			
			MemberCheckService memberService = new MemberCheckService();
			Boolean isMemberHasCom = memberService.doCheckMemberHasCom(memId);
			PrintWriter pw = res.getWriter();
			
			Company_MemVO company_memVO = new Company_MemByCheckVO();
			company_memVO.setComTaxId("comTaxId");
			Company_MemByCheckVO company_membycheckVO = new Company_MemByCheckVO();
			company_membycheckVO = (Company_MemByCheckVO) company_memVO;
			company_membycheckVO.setIsMemberHasCom(isMemberHasCom);			
			
			pw.write("{\"isMemberHasCom\": " + isMemberHasCom);

			if (isMemberHasCom) {
				Company_MemService company_memSvc = new Company_MemService();

//				Company_MemVO company_memVO = new Company_MemVO();
				company_memVO = company_memSvc.doGetCompanyMemByMemId(memId);
				
//				 Gson gson = new Gson();
//			     String jsonString = gson.toJson(company_memVO);
			     
//			     pw.write(jsonString);
				
//				pw.write(", \"comTaxId\": \"" + company_memVO.getComTaxId()
//						+ "\"");
//				pw.write(", \"memId\": \"" + company_memVO.getMemId() + "\"");
//				pw.write(", \"comName\": \"" + company_memVO.getComName()
//						+ "\"");
//				pw.write(", \"comAddress\": \"" + company_memVO.getComAddress()
//						+ "\"");
//				pw.write(", \"comPhone\": \"" + company_memVO.getComPhone()
//						+ "\"");
//				pw.write(", \"comOwner\": \"" + company_memVO.getComOwner()
//						+ "\"");
//				pw.write(", \"ownerPhone\": \"" + company_memVO.getOwnerPhone()
//						+ "\"");
//				pw.write(", \"comBankaccount\": \""
//						+ company_memVO.getComBankaccount() + "\"");
//				pw.write(", \"comRegdate\": \"" + company_memVO.getComRegdate()
//						+ "\"");
			}

			pw.write("}");

			// 寫好RS給AJAX
			pw.flush();
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 公司統編
			String com_taxid = req.getParameter("com_taxid");
			String com_taxidReg = "^[0-9]{8}$";
			if (com_taxid == null || com_taxid.trim().length() == 0) {
				errorMsgs.add("公司統編請勿空白");
			} else if (!com_taxid.trim().matches(com_taxidReg)) {
				errorMsgs.add("公司統編：需為數字,且長度為8碼");
			}
			// 會員編號
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));

			// 公司名稱
			String com_name = req.getParameter("com_name");
			String com_nameReg = "^[(\u4e00-\\u9fa5)(a-zA-Z)]{2,50}$";
			if (com_name == null || com_name.trim().length() == 0) {
				errorMsgs.add("公司名稱請勿空白");
			} else if (!com_name.trim().matches(com_nameReg)) {
				errorMsgs.add("公司名稱：只能是中、英文,且長度需在2-50之間");
			}
			// 公司地址
			String County = req.getParameter("County");
			String Zero = req.getParameter("Zero");
			String com_address = req.getParameter("com_address");
			String iscom_address = County + Zero + com_address;
			// String com_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (com_address == null || com_address.trim().length() == 0) {
				errorMsgs.add("公司地址請勿空白");
			} // else if (!com_address.trim().matches(com_addressReg)) {
				// errorMsgs.add("公司地址的正則表達規則");
				// }
				// 公司電話
			String com_phone = req.getParameter("com_phone");
			String com_phoneReg = "^(\\d{2,3}-?|\\d{2,3})\\d{3,4}-?\\d{4}$";
			if (com_phone == null || com_phone.trim().length() == 0) {
				errorMsgs.add("公司電話請勿空白");
			} else if (!com_phone.trim().matches(com_phoneReg)) {
				errorMsgs.add("公司電話：需有區碼-");
			}
			// 負責人
			String com_owner = req.getParameter("com_owner");
			String com_ownerReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
			if (com_owner == null || com_owner.trim().length() == 0) {
				errorMsgs.add("負責人姓名請勿空白");
			} else if (!com_owner.trim().matches(com_ownerReg)) {
				errorMsgs.add("負責人姓名：只能是中、英文,且長度需在2-10之間");
			}
			// 負責人手機號碼
			String owner_phone = req.getParameter("owner_phone");
			String owner_phoneReg = "^09\\d{8}$";
			if (owner_phone == null || com_owner.trim().length() == 0) {
				errorMsgs.add("負責人手機號碼請勿空白");
			} else if (!owner_phone.trim().matches(owner_phoneReg)) {
				errorMsgs.add("負責人手機號碼：請重新檢查");
			}
			// 銀行帳戶
			String com_bankaccount = req.getParameter("com_bankaccount");
			String com_bankaccountReg = "^\\d{10,16}$";
			if (!"".equals(com_bankaccount.trim())
					&& !com_bankaccount.trim().matches(com_bankaccountReg)) {
				errorMsgs.add("銀行帳戶：長度需在10-16碼之間");
			}
			Company_MemVO company_memVO = new Company_MemVO();
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("company_memVO", company_memVO);
				RequestDispatcher failureView = req.getRequestDispatcher(
						"/CompanyBackEnd/pages/setcompany_member.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			Company_MemService company_memSvc = new Company_MemService();
			company_memSvc.addCompany_Mem(com_taxid, mem_id, com_name,
					iscom_address, com_phone, com_owner, owner_phone,
					com_bankaccount);
			req.setAttribute("company_memVO", company_memVO);
			String url = "/CompanyBackEnd/pages/listonecompany_member.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update_save".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 公司統編
			String com_taxid = req.getParameter("com_taxid");
			// 會員編號
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));
			// 公司名稱
			String com_name = req.getParameter("com_name");
			String com_nameReg = "^[(\u4e00-\\u9fa5)(a-zA-Z)]{2,50}$";
			if (com_name == null || com_name.trim().length() == 0) {
				errorMsgs.add("公司名稱請勿空白");
			} else if (!com_name.trim().matches(com_nameReg)) {
				errorMsgs.add("公司名稱：只能是中、英文,且長度需在2-50之間");
			}
			// 公司地址
			String com_address = req.getParameter("com_address");
			// String com_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (com_address == null || com_address.trim().length() == 0) {
				errorMsgs.add("公司地址請勿空白");
			} // else if (!com_address.trim().matches(com_addressReg)) {
				// errorMsgs.add("公司地址的正則表達規則");
				// }
			// 公司電話
			String com_phone = req.getParameter("com_phone");
			String com_phoneReg = "^(\\d{2,3}-?|\\d{2,3})\\d{3,4}-?\\d{4}$";
			if (com_phone == null || com_phone.trim().length() == 0) {
				errorMsgs.add("公司電話請勿空白");
			} else if (!com_phone.trim().matches(com_phoneReg)) {
				errorMsgs.add("公司電話：需有區碼-");
			}
			// 負責人
			String com_owner = req.getParameter("com_owner");
			String com_ownerReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
			if (com_owner == null || com_owner.trim().length() == 0) {
				errorMsgs.add("負責人姓名請勿空白");
			} else if (!com_owner.trim().matches(com_ownerReg)) {
				errorMsgs.add("負責人姓名：只能是中、英文,且長度需在2-10之間");
			}
			// 負責人手機號碼
			String owner_phone = req.getParameter("owner_phone");
			String owner_phoneReg = "^09\\d{8}$";
			if (owner_phone == null || com_owner.trim().length() == 0) {
				errorMsgs.add("負責人手機號碼請勿空白");
			} else if (!owner_phone.trim().matches(owner_phoneReg)) {
				errorMsgs.add("負責人手機號碼：請重新檢查");
			}
			// 銀行帳戶
			String com_bankaccount = req.getParameter("com_bankaccount");
			String com_bankaccountReg = "^\\d{10,16}$";
			if (!"".equals(com_bankaccount.trim())
					&& !com_bankaccount.trim().matches(com_bankaccountReg)) {
				errorMsgs.add("銀行帳戶：長度需在10-16碼之間");
			}
			Company_MemVO company_memVO = new Company_MemVO();
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("company_memVO", company_memVO);
				RequestDispatcher failureView = req.getRequestDispatcher(
						"/back-end/company_member/pages/setcompany_member.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			Company_MemService company_memSvc = new Company_MemService();
			company_memSvc.updateCompany_Mem(com_taxid, mem_id, com_name,
					com_address, com_phone, com_owner, owner_phone,
					com_bankaccount);
			company_memVO = company_memSvc.getOneCompany_Mem(com_taxid);
			req.setAttribute("company_memVO", company_memVO);
			String url = "/CompanyBackEnd/pages/listonecompany_member.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if ("doSetStoreInfo".equals(action)) {
			String comtaxId = req.getParameter("comtaxId");
			
			PrintWriter pw = res.getWriter();

			
			Company_MemService company_memSvc = new Company_MemService();
			if((company_memSvc.doGetCompanyMemByTaxId(comtaxId))!=null) {
				Company_MemVO company_memVO = new Company_MemVO();
				company_memVO = company_memSvc.doGetCompanyMemByTaxId(comtaxId);
				pw.write("{\"isComHasStore\": " + company_memVO);				
				byte[] store_logo_bytes = null;
				byte[] store_banner_bytes = null;
				// 賣場logo
				Part store_logo = req.getPart("store_logo");
				store_logo_bytes = store_logo.getInputStream().readAllBytes();
				store_logo_bytes = store_logo_bytes.length == 0
						? null
						: store_logo_bytes;
				// 賣場Banner
				Part store_banner = req.getPart("store_banner");
				store_banner_bytes = store_banner.getInputStream().readAllBytes();
				store_banner_bytes = store_banner_bytes.length == 0? null: store_banner_bytes;
				
				pw.write(", \"comTaxId\": \"" + company_memVO.getComTaxId()
				+ "\"");
				pw.write(", \"store_name\": \"" + company_memVO.getStoreName()
						+ "\"");
				pw.write(", \"store_intro\": \"" + company_memVO.getStoreIntro() + "\"");
				pw.write(", \"store_logo\": \"" + company_memVO.getStoreLogoString()
						+ "\"");
				pw.write(", \"store_banner\": \"" + company_memVO.getStoreBannerString()
						+ "\"");
			}

			pw.write("}");

			// 寫好RS給AJAX
			pw.flush();
		}
		
		 
		if ("insertforshop".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/******************* Contoller第一步接收請求參數,輸入格式的錯誤處理 ****************/

			// 賣場名稱
			String store_name = req.getParameter("store_name");
			String store_nameReg = "^[(\u4e00-\\u9fa5)(a-zA-Z)]{2,100}$";
			if (store_name == null || store_name.trim().length() == 0) {
				errorMsgs.add("賣場名稱請勿空白");
			} else if (!store_name.trim().matches(store_nameReg)) {
				errorMsgs.add("賣場名稱：只能是中、英文,且長度需在2-100之間");
			}
			// 賣場簡介
			String store_intro = req.getParameter("store_intro");
			String store_introReg = "^[(\u4e00-\\u9fa5)(a-zA-Z)]{2,2000}$";
			if (!"".equals(store_intro.trim())
					&& !store_name.trim().matches(store_introReg)) {
				errorMsgs.add("賣場簡介：只能是中、英文,且長度需在2-1000之間");
			}

			byte[] store_logo_bytes = null;
			byte[] store_banner_bytes = null;
			// 賣場logo
			Part store_logo = req.getPart("store_logo");
			store_logo_bytes = store_logo.getInputStream().readAllBytes();
			store_logo_bytes = store_logo_bytes.length == 0
					? null
					: store_logo_bytes;
			// 賣場Banner
			Part store_banner = req.getPart("store_banner");
			store_banner_bytes = store_banner.getInputStream().readAllBytes();
			store_banner_bytes = store_banner_bytes.length == 0
					? null
					: store_banner_bytes;

			Company_MemVO company_memVO = new Company_MemVO();
			company_memVO.setStoreName(store_name);
			company_memVO.setStoreIntro(store_intro);
			company_memVO.setStoreLogo(store_logo_bytes);
			company_memVO.setStoreBanner(store_banner_bytes);
			/*************************
			 * 如有錯誤將forward回修改頁面
			 **************************/
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("company_memVO", company_memVO); // 含有輸入格式錯誤的company_memVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher(
						"/back-end/company_member/pages/setcompany_forshop.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/************************
			 * Contoller第二步開始查詢資料
			 ************************/
			Company_MemService company_memSvc = new Company_MemService();
			company_memSvc.addCompany_Mem(store_name, store_intro,
					store_logo_bytes, store_banner_bytes);
			/************************ Contoller第三步開始查詢完成,準備轉交 ****************/
			req.setAttribute("company_memVO", company_memVO);
			String url = "/CompanyBackEnd/pages/listonecompany_forshop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*************************
			 * 處理來自listAll.page的刪除需求
			 ********************/

		}
		

		// if ("updateforcompany".equals(action)) {
		// List<String> errorMsgs = new LinkedList<String>();
		// req.setAttribute("errorMsgs", errorMsgs);
		//
		// /******************* Contoller第一步接收請求參數,輸入格式的錯誤處理 ****************/
		//
		// // 賣場名稱
		// String store_name = req.getParameter("store_name");
		// String store_nameReg = "^[(\u4e00-\\u9fa5)(a-zA-Z)]{2,100}$";
		// if (store_name == null || store_name.trim().length() == 0) {
		// errorMsgs.add("賣場名稱請勿空白");
		// } else if (!store_name.trim().matches(store_nameReg)) {
		// errorMsgs.add("賣場名稱：只能是中、英文,且長度需在2-100之間");
		// }
		// // 賣場簡介
		// String store_intro = req.getParameter("store_intro");
		// String store_introReg = "^[(\u4e00-\\u9fa5)(a-zA-Z)]{2,2000}$";
		// if (!"".equals(com_bankaccount.trim()) &&
		// !store_name.trim().matches(store_nameReg)) {
		// errorMsgs.add("賣場簡介：只能是中、英文,且長度需在2-1000之間");
		// }
		//
		// byte[] store_logo_bytes = null;
		// byte[] store_banner_bytes = null;
		// // 賣場logo
		// Part store_logo = req.getPart("store_logo");
		// store_logo_bytes = store_logo.getInputStream().readAllBytes();
		// store_logo_bytes = store_logo_bytes.length == 0 ? null :
		// store_logo_bytes;
		// //如果沒有圖片回傳null,如果有圖片傳該圖的byte[]
		// // 賣場Banner
		// Part store_banner = req.getPart("store_banner");
		// store_banner_bytes = store_banner.getInputStream().readAllBytes();
		// store_banner_bytes = store_banner_bytes.length == 0 ? null :
		// store_banner_bytes;
		//
		// Company_MemVO company_memVO = new Company_MemVO();
		// company_memVO.setStoreName(store_name);
		// company_memVO.setStoreIntro(store_intro);
		// company_memVO.setStoreLogo(store_logo_bytes);
		// company_memVO.setStoreBanner(store_banner_bytes);
		// /************************* 如有錯誤將forward回修改頁面
		// **************************/
		// if (!errorMsgs.isEmpty()) {
		// req.setAttribute("company_memVO", company_memVO); //
		// 含有輸入格式錯誤的company_memVO物件,也存入req
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/back-end/company_member/updatecompany_member.jsp");
		// failureView.forward(req, res);
		// return; // 程式中斷
		// }
		// /************************ Contoller第二步開始查詢資料
		// ************************/
		// Company_MemService company_memSvc = new Company_MemService();
		// company_memSvc.updateCompany_Mem(store_name, store_intro,
		// store_logo_bytes,
		// store_banner_bytes);
		// /************************ Contoller第三步開始查詢完成,準備轉交 ****************/
		// company_memVO = company_memSvc.getOneCompany_Mem(com_taxid);
		//
		//
		// req.setAttribute("company_memVO", company_memVO);
		// String url =
		// "/back-end/company_member/pages/listOnecompany_member.jsp";
		// RequestDispatcher successView = req.getRequestDispatcher(url);
		// successView.forward(req, res);
		// }

		/************************* 處理select.page的需求 **************************/
//		if ("getOne_For_Display".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			/******************* Contoller第一步接收請求參數,輸入格式的錯誤處理 ****************/
//			// 後端擋type輸入的空字串
//			String str = req.getParameter("com_taxid");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入廠商統一編號");
//			}
//			// type確定有輸入文字執行下一段,如沒有輸入文字直接點送出用forward跳回原本的頁面
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher(
//						"/back-end/company_member/selectcompany_member.jsp");
//				failureView.forward(req, res);
//				return; // 下面的程式不執行
//			}
//			// 判斷輸入格式是否正確,如不正確用forward跳回原本的頁面
//			String comTaxid = str;
//			if (str.length() != 8) {
//				errorMsgs.add("統一編號格式不正確");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher successView = req.getRequestDispatcher(
//						"/back-end/company_member/selectcompany_member.jsp");
//				successView.forward(req, res);
//				return; // 下面的程式不執行
//			}
//			/************************ Contoller第二步開始查詢資料 ****************/
//			Company_MemService company_memSvc = new Company_MemService();
//			Company_MemVO company_memVO = company_memSvc
//					.getOneCompany_Mem(comTaxid);
//			// 如果在company_memVO找不到資料,回傳"查無資料"用forward跳回原本的頁面
//			if (company_memVO == null) {
//				errorMsgs.add("查無資料");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher(
//						"/back-end/company_member/selectcompany_member.jsp");
//				failureView.forward(req, res);
//				return; // 下面的程式不執行
//			}
//			/************************ Contoller第三步開始查詢完成,準備轉交 ****************/
//			req.setAttribute("company_memVO", company_memVO); // 資料庫取出的company_memVO物件,存入req
//			String url = "/back-end/company_member/pages/listonecompany_member.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//
//		}
//		/************************* 處理listAll.page的需求 *************************/
//		if ("getOne_For_Update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			/******************* Contoller第一步接收請求參數,輸入格式的錯誤處理 ****************/
//			String com_taxid = req.getParameter("com_taxid");
//			/************************
//			 * Contoller第二步開始查詢資料
//			 ************************/
//			Company_MemService company_memSvc = new Company_MemService();
//			Company_MemVO company_memVO = company_memSvc
//					.getOneCompany_Mem(com_taxid);
//			/************************ Contoller第三步開始查詢完成,準備轉交 ****************/
//			req.setAttribute("company_memVO", company_memVO);
//			String url = "/back-end/company_member/updatecompany_member.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}
//
//		if ("delete".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			String com_taxid = req.getParameter("com_taxid");
//			Company_MemService company_memSvc = new Company_MemService();
//			company_memSvc.deleteCompany_Mem(com_taxid);
//			String url = "/back-end/company_member/listAllcompany_member.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}
	}

}