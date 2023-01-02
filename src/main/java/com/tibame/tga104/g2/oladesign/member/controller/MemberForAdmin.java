package com.tibame.tga104.g2.oladesign.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tibame.tga104.g2.oladesign.CompanyMember.service.Company_MemService;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.intermail.model.IntermailService;
import com.tibame.tga104.g2.oladesign.intermail.model.IntermailVO;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.service.MemberIsComService;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;

@WebServlet("/MemberForAdmin")
public class MemberForAdmin extends HttpServlet {
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("doGetAllComInfo".equals(action)) {
			String adminId = req.getParameter("adminId");

			Company_MemService company_memSvc = new Company_MemService();
			List<Company_MemVO> company_memVO = company_memSvc.getAll();

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(company_memVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
		if ("doReviewCOM".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String adminId = req.getParameter("adminId");
			Integer memId = Integer.valueOf(req.getParameter("memId"));

			MemberIsComService memberIsComSVC = new MemberIsComService();
			memberIsComSVC.updateIsCom(memId);

			MemberService memberSVC = new MemberService();
			MemberVO memberVO = memberSVC.getOneMember(memId);

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(memberVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
		
		if ("getOne_For_Display".equals(action)) {
			System.out.println("QQ");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String str = req.getParameter("memId");
			System.out.println(str);
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/memForAdmin/listallmember1.jsp");
				failureView.forward(req, res);
				return; // 下面的程式不執行
			}
			String memId = str;
			if (str.length() != 6) {
				errorMsgs.add("會員編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher successView = req.getRequestDispatcher("/memForAdmin/listallmember1.jsp");
				successView.forward(req, res);
				return;
			}
			MemberService memberService = new MemberService();
			Integer  memId1 = Integer.valueOf(memId);
			MemberVO memberVO = memberService.getOneMember(memId1);
			if (memberVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/memForAdmin/listallmember1.jsp");
				failureView.forward(req, res);
				return;
			}
			req.setAttribute("memberVO", memberVO); // 資料庫取出的company_memVO物件,存入req
			String url = "/memForAdmin/listOneMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("CheckOne".equals(action)) {

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer memId = Integer.valueOf(req.getParameter("memId"));
			System.out.println(memId);
			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberService = new MemberService();
			MemberVO memberVO = memberService.getCheckOne(memId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO);
			String url = "/memForAdmin/listOneMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		
		if ("Ban".equals(action)) {
			MemberVO memberVO = new MemberVO();
			Boolean isBan = false;
			memberVO.setIsBan(isBan);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer memId = Integer.valueOf(req.getParameter("memId"));
			System.out.println(memId);

			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberService = new MemberService();
//			memberVO = memberService.getBan(memId, isBan)
			memberService.getBan(memId, isBan);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO);
			String url = "/memForAdmin/listallmember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		
		if ("UnBan".equals(action)) {
			MemberVO memberVO = new MemberVO();
			Boolean isBan = false;
			memberVO.setIsBan(isBan);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer memId = Integer.valueOf(req.getParameter("memId"));
			System.out.println(memId);

			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberService = new MemberService();
//			memberVO = memberService.getBan(memId, isBan)
			memberService.getUnBan(memId, isBan);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO);
			String url = "/memForAdmin/listallmember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}
		

	}

}