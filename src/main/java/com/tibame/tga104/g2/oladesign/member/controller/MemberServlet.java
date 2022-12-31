package com.tibame.tga104.g2.oladesign.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.service.MemberService;



@WebServlet("/member/MemberServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
//	========查詢一筆
		if("getOne".equals(action)) { //member.jsp
//			錯誤訊息提示
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
//			接收請求參數
			String memStr = request.getParameter("memId");
			if(memStr == null || memStr.trim().length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			
			String memIdReg = "^[0-9]{6}$";
			if(!memStr.matches(memIdReg)) {
				errorMsgs.add("會員ID格式不正確，請輸入6個數字");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/member/member.jsp");
				errView.forward(request, response);
				return;
			}
			
			Integer memId = null;
			
			try {
				memId = Integer.valueOf(request.getParameter("memId"));
			}catch(Exception e){
				errorMsgs.add("會員編號格式不正確，請輸入6個數字");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/member/member.jsp");
				errView.forward(request, response);
				return;
			}
			
//			開始查詢
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.getOneMember(memId);
			if(memberVO == null) {
				errorMsgs.add("沒有該筆會員資料");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher errView = request.getRequestDispatcher("/member/member.jsp");
				errView.forward(request, response);
				return;
			}
			
//			轉交至單一查詢結果畫面
			request.setAttribute("memberVO", memberVO);
			String url = "/back-end/member/getOneMem.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
//		========修改========
		if("updateOne".equals(action)) { //listAllMem.jsp
						
//			接收請求參數
			Integer memId = Integer.valueOf(request.getParameter("memId"));
			
//			開始查詢
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.getOneMember(memId);
			
//			轉交至會員修改畫面
			request.setAttribute("memberVO", memberVO);
			String url = "/back-end/member/updateMem.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
//		========修改後送出========
		if("update".equals(action)) { //updateMem.jsp
			List<String> errorMsgs = new LinkedList<String>();
			
			request.setAttribute("errorMsgs", errorMsgs);
			
//			接收請求參數
			Integer memId = Integer.valueOf(request.getParameter("memId"));
			String memName = request.getParameter("memName");
			String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
			if(memName == null || memName.trim().length() == 0){
				errorMsgs.add("會員姓名:請勿空白");
			}else if(!memName.matches(memNameReg)) {
				errorMsgs.add("會員姓名:僅能輸入2~20個中、英文字");
			}
			
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String memPhone = request.getParameter("memPhone");
			String memPhoneReg = "^09[0-9]{8}$";
			if(memPhone == null || memPhone.trim().length() == 0) {
				errorMsgs.add("手機:請勿空白");
			}else if(!memPhone.matches(memPhoneReg)) {
				errorMsgs.add("手機:請輸入正確電話格式");
			}
			
			String memAddress = request.getParameter("memAddress");
			if(memAddress == null || memAddress.trim().length() == 0) {
				errorMsgs.add("地址:請勿空白");
			}
			
			Date memRegdate = null;
			try {
				memRegdate = Date.valueOf(request.getParameter("memRegdate"));
			}catch(IllegalArgumentException i) {
				i.printStackTrace();
			}
			
			String sex = request.getParameter("sex");
			
			String strPoint = request.getParameter("point");
			String strPointReg = "^[0-9]+$";
			if(strPoint == null || strPoint.trim().length() == 0) {
				errorMsgs.add("紅利點數:請勿空白");
			}else if(!strPoint.matches(strPointReg)) {
				errorMsgs.add("紅利點數:不能為負數或非整數數字");
			}
			
			Integer point = null;
			try {
				point = Integer.valueOf(strPoint);
			}catch(Exception e) {
				errorMsgs.add("紅利點數:格式不正確，請輸入數字");
			}
			
			boolean isBan = Boolean.valueOf(request.getParameter("isBan"));
			
			boolean isCom = Boolean.valueOf(request.getParameter("isCom"));
			
			//照片上傳
			byte[] memPhoto = null;
			Part part = request.getPart("memPhoto");
			System.out.println("part=" + part);
			InputStream in = part.getInputStream();	
			System.out.println("in=" + in);
			System.out.println("in.available()=" +in.available());
			
			if(in.available() != 0) {
				memPhoto = new byte[in.available()];
				in.read(memPhoto);
				in.close();
			}					
			
			MemberVO memberVO = new MemberVO();
			
			memberVO.setMemId(memId);
			memberVO.setMemName(memName);
			memberVO.setAccount(account);
			memberVO.setPassword(password);
			memberVO.setMemPhone(memPhone);
			memberVO.setMemAddress(memAddress);
			memberVO.setMemRegdate(memRegdate);
			memberVO.setSex(sex);
			memberVO.setPoint(point);
			memberVO.setIsBan(isBan);
			memberVO.setIsCom(isCom);
			memberVO.setMemPhoto(memPhoto);
//			memberVO.setMemPhotoBase64(memPhoto); //memPhoto轉為Base64
			
			System.out.println("memPhoto=" + memPhoto);
			if(!errorMsgs.isEmpty()) {
				request.setAttribute("memberVO", memberVO);
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/member/updateMem.jsp");
				errView.forward(request, response);
				return;
			}
			
//			開始修改
			MemberService memSvc = new MemberService();
//			memberVO = memSvc.updateMember(memId, memName, account, password, memPhone, memAddress, memRegdate, sex, point, isBan, isCom, memPhoto);
			memberVO = memSvc.updateMember(memberVO);
			
//			轉交getOneMem.jsp
			request.setAttribute("memberVO", memberVO);
			String url = "/back-end/member/getOneMem.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		
//		========新增========
		if("insert".equals(action)) { //來自listAllMem.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			
			request.setAttribute("errorMsgs", errorMsgs);
			
//			接收請求參數			
			String memName = request.getParameter("memName");
			String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
			if(memName == null || memName.trim().length() == 0){
				errorMsgs.add("會員姓名:請勿空白");
			}else if(!memName.matches(memNameReg)) {
				errorMsgs.add("會員姓名:僅能輸入2~20個中、英文字");
			}
			
			String account = request.getParameter("account");
			String accountReg = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
			if(account == null || account.trim().length() == 0){
				errorMsgs.add("會員帳號:請勿空白");
			}else if(!account.matches(accountReg)) {
				errorMsgs.add("帳號:請輸入email");
			}
			
			String password = request.getParameter("password");
			if(password == null || password.trim().length() == 0){
				errorMsgs.add("密碼:請勿空白");
			}
			
			String memPhone = request.getParameter("memPhone");
			String memPhoneReg = "^09[0-9]{8}$";
			if(memPhone == null || memPhone.trim().length() == 0) {
				errorMsgs.add("手機:請勿空白");
			}else if(!memPhone.matches(memPhoneReg)) {
				errorMsgs.add("手機:請輸入正確電話格式");
			}
			
			String memAddress = request.getParameter("memAddress");
			if(memAddress == null || memAddress.trim().length() == 0) {
				errorMsgs.add("地址:請勿空白");
			}
			
			String sex = request.getParameter("sex");
			if(sex == null) {
				errorMsgs.add("請選擇性別");
			}
			
//			照片上傳
			String realPath = getServletContext().getRealPath("/img_upload"); //上傳目的目錄
			System.out.println("realPath="+realPath);
			File saveDir = new File(realPath); //創建目錄
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			
			Part part = request.getPart("memPhoto");
			InputStream in = part.getInputStream();
			byte[] memPhoto = null;
			if(in.available() != 0) {
				memPhoto = new byte[in.available()];
				in.read(memPhoto);
				in.close();
			}
			
			MemberVO memberVO = new MemberVO();
			
			memberVO.setMemName(memName);
			memberVO.setAccount(account);
			memberVO.setPassword(password);
			memberVO.setMemPhone(memPhone);
			memberVO.setMemAddress(memAddress);
			memberVO.setSex(sex);
			memberVO.setMemPhoto(memPhoto);
			
			if(memPhoto != null) {
//				memberVO.setMemPhotoBase64(memPhoto); //memPhoto轉為Base64
			}
			
			if(!errorMsgs.isEmpty()) {
				request.setAttribute("memberVO", memberVO);
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/member/addMem.jsp");
				errView.forward(request, response);
				return;
			}
			
//			開始新增
			MemberService memSvc = new MemberService();
			memberVO = memSvc.addMember(memName, account, password, memPhone, memAddress, sex, memPhoto);
			
//			轉交listAllMem.jsp   
			String url = "/back-end/member/listAllMem.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
		}
		
//		========刪除========
		if("delete".equals(action)) { //來自listAllMem.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			
			request.setAttribute("errorMsgs", errorMsgs);
			
//			接收請求參數
			Integer memId = Integer.valueOf(request.getParameter("memId"));
			
//			開始刪除
			MemberService memSvc = new MemberService();
			memSvc.delMember(memId);
			
//			轉交listAllMem.jsp
			String url = "/back-end/member/listAllMem.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
	}

}
