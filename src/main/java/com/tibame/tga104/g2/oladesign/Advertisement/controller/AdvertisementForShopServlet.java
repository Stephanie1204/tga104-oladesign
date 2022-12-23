package com.tibame.tga104.g2.oladesign.Advertisement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tibame.tga104.g2.oladesign.Advertisement.service.AdvertisementService;
import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementByCheckVO;
import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementVO;

@WebServlet("/homePage/adforshop.do")
@MultipartConfig
public class AdvertisementForShopServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 抓取當日廣告
		if ("doGetTodayAD".equals(action)) {
			AdvertisementService advertisementSvc = new AdvertisementService();
			List<AdvertisementVO> advertisementVO = advertisementSvc.getTodayAD();
			List<AdvertisementByCheckVO> result = new ArrayList<AdvertisementByCheckVO>();

			for (int i = 0; i < advertisementVO.size(); i++) {
				AdvertisementVO advertisement = advertisementVO.get(i);
				AdvertisementByCheckVO advertisementByCheckVO = new AdvertisementByCheckVO();
				advertisementByCheckVO.setComTaxId(advertisement.getComTaxId());
				advertisementByCheckVO.setAdImageString(advertisement.getAdImagesString());
				result.add(advertisementByCheckVO);

			}
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(result);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
		
		// 前往當日廣告的廣告賣家
		if ("doGetTodayADShop".equals(action)) {
			String comTaxId = req.getParameter("comTaxId");
			
			AdvertisementService advertisementSvc = new AdvertisementService();
			List<AdvertisementVO> advertisementVO = advertisementSvc.getTodayAD();
			List<AdvertisementByCheckVO> result = new ArrayList<AdvertisementByCheckVO>();
			
			for (int i = 0; i < advertisementVO.size(); i++) {
				AdvertisementVO advertisement = advertisementVO.get(i);
				AdvertisementByCheckVO advertisementByCheckVO = new AdvertisementByCheckVO();
				advertisementByCheckVO.setComTaxId(advertisement.getComTaxId());
				advertisementByCheckVO.setAdImageString(advertisement.getAdImagesString());
				result.add(advertisementByCheckVO);

			}

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(advertisementVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
	}

}
