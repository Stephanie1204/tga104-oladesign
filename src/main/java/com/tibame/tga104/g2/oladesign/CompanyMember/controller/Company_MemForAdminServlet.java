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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tibame.tga104.g2.oladesign.CompanyMember.service.Company_MemService;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.helper.SendMail;
import com.tibame.tga104.g2.oladesign.member.service.MemberIsComService;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;

@WebServlet("/back-end/comforadmin.do")
@MultipartConfig
public class Company_MemForAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
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

			SendMail sendFirstMail = new SendMail();
			sendFirstMail.sendComMemMail(memberVO.getAccount(),memberVO.getMemName());
			
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(memberVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String str = req.getParameter("comtaxId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入廠商統一編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/listallcompanymember.jsp");
				failureView.forward(req, res);
				return; // 下面的程式不執行
			}
			String comtaxId = str;
			if (str.length() != 8) {
				errorMsgs.add("統一編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/listallcompanymember.jsp");
				successView.forward(req, res);
				return;
			}
			Company_MemService company_memSvc = new Company_MemService();
			Company_MemVO company_memVO = company_memSvc.getOneCompany_Mem(comtaxId);
			if (company_memVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/listallcompanymember.jsp");
				failureView.forward(req, res);
				return;
			}
			req.setAttribute("company_memVO", company_memVO); // 資料庫取出的company_memVO物件,存入req
			String url = "/back-end/listonecompanymem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

}
