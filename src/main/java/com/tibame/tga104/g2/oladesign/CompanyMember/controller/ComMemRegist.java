package com.tibame.tga104.g2.oladesign.CompanyMember.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.tga104.g2.oladesign.CompanyMember.service.ComMemRegistService;
import com.tibame.tga104.g2.oladesign.CompanyMember.service.Company_MemService;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.member.bean.MemberVO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAO;
import com.tibame.tga104.g2.oladesign.member.dao.MemberDAOImpl;

@WebServlet("/CompanyBackEnd/ComMemRegist")
public class ComMemRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComMemRegistService comSvc;
	private Gson gson;
	
	private MemberDAO memberDAO;
	private MemberVO memberVO;
       
	@Override
	public void init() throws ServletException {
		comSvc = new ComMemRegistService();
		gson = new Gson();
		
		memberDAO = new MemberDAOImpl();
		memberVO = new MemberVO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Company_MemVO comMemVO = gson.fromJson(request.getReader(), Company_MemVO.class);
		
		JsonObject respBody = new JsonObject();
		if(comMemVO == null) {
			respBody.addProperty("success", false);
		}else {		
			if(comSvc.comMemRegist(comMemVO).getClass().equals(HashMap.class)) { //如果回傳物件型別為HashMap，就是錯誤訊息
				System.out.println("錯誤訊息處理");
				respBody.addProperty("success", false);
				respBody.addProperty("error", gson.toJson(comSvc.comMemRegist(comMemVO)));
			}else {
				System.out.println("成功註冊");
				respBody.addProperty("success", true);
				respBody.addProperty("comMemVO", gson.toJson(comSvc.comMemRegist(comMemVO)));
				memberVO = memberDAO.findByPrimaryKey(comMemVO.getMemId());
				System.out.println("getRegCom="+ memberVO.getIsRegCom());
				respBody.addProperty("regCom", memberVO.getIsRegCom());
			}	
		}
		response.getWriter().write(respBody.toString());
	}

}
