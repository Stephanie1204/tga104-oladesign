package com.tibame.tga104.g2.oladesign.Advertisement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tibame.tga104.g2.oladesign.Advertisement.service.AdvertisementService;
import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementByCheckVO;
import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementVO;

@WebServlet("/back-end/adforadmin.do")
@MultipartConfig
public class AdvertisementForaAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 管理員-顯示全部廣告紀錄
		if ("doGetAllADInfo".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String adminId = req.getParameter("adminId");

			AdvertisementService advertisementSvc = new AdvertisementService();
			List<AdvertisementVO> advertisementVO = advertisementSvc.getAll();
			List<AdvertisementByCheckVO> result = new ArrayList<AdvertisementByCheckVO>();

			for (int i = 0; i < advertisementVO.size(); i++) {
				AdvertisementVO advertisement = advertisementVO.get(i);
				AdvertisementByCheckVO advertisementByCheckVO = new AdvertisementByCheckVO();
				advertisementByCheckVO.setAdId(advertisement.getAdId());
				advertisementByCheckVO.setComTaxId(advertisement.getComTaxId());
				advertisementByCheckVO.setStartDate(advertisement.getStartDate());
				advertisementByCheckVO.setEndDate(advertisement.getEndDate());
				advertisementByCheckVO.setAdStatus(advertisement.getAdStatus());
				advertisementByCheckVO.setAdImageString(advertisement.getAdImagesString());
				result.add(advertisementByCheckVO);

			}
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(result);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}

		// 管理員-審核廣告
		if ("doReviewAD".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String adminId = req.getParameter("adminId");
			String adId = req.getParameter("adId");

			AdvertisementService advertisementSvc = new AdvertisementService();
			advertisementSvc.updateAdStatus(adId);

			AdvertisementVO advertisementVO = new AdvertisementVO();
			advertisementVO = advertisementSvc.getOneAdvertisement(adId);

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(advertisementVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
	}
}
