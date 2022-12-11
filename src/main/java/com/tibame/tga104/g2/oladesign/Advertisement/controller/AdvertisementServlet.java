package com.tibame.tga104.g2.oladesign.Advertisement.controller;

import java.io.IOException;
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

import com.tibame.tga104.g2.oladesign.Advertisement.service.AdvertisementService;
import com.tibame.tga104.g2.oladesign.Advertisement.vo.AdvertisementVO;

@WebServlet("/CompanyBackEnd/advertisementdo")
@MultipartConfig
public class AdvertisementServlet extends HttpServlet {
    private static final long serialVersionUID = 2L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doGet(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	String action = req.getParameter("action");
	/*********************************************
	 * 新增
	 *************************************************/
	// 確定新增之前需確定段該日期區間是否會有重複3組
	if ("insert".equals(action)) {
	    List<String> errorMsgs = new LinkedList<String>();
	    req.setAttribute("errorMsgs", errorMsgs);

	    String reservation = req.getParameter("reservation");
	    byte[] adimages_bytes = null;
	    Part adimages = req.getPart("adimages");
	    adimages_bytes = adimages.getInputStream().readAllBytes();
	    adimages_bytes = adimages_bytes.length == 0 ? null : adimages_bytes;

	    AdvertisementVO advertisementVO = new AdvertisementVO();
	    advertisementVO.setComTaxId(req.getParameter("com_taxid"));
	    advertisementVO.setStoreLink(req.getParameter("storelink"));
	    advertisementVO.setStartDate(reservation.split("-")[0].trim());
	    advertisementVO.setEndDate(reservation.split("-")[1].trim());
	    advertisementVO.setAdImages(adimages_bytes);

	    AdvertisementService advertisementSvc = new AdvertisementService();
	    String adId = "";
	    // 如果isinsertable得到true便呼叫addAdvertisement()
	    if (advertisementSvc.getIsInsertAble(advertisementVO)) {
		adId = advertisementSvc.addAdvertisement(advertisementVO);
	    } else {
		errorMsgs.add("廣告時段已額滿，請重新選擇日期");
		if (!errorMsgs.isEmpty()) {
		    req.setAttribute("IsInsertAble", "不可");
		    RequestDispatcher failureView = req
			    .getRequestDispatcher("/CompanyBackEnd/addadvertisement.jsp");
		    failureView.forward(req, res);
		    return; // 程式中斷
		}
	    }
	    AdvertisementVO result = advertisementSvc.getOneAdvertisement(adId);

	    req.setAttribute("advertisementVO", result);
	    String url = "/CompanyBackEnd/listoneadvertisement.jsp";
	    RequestDispatcher successView = req.getRequestDispatcher(url);
	    successView.forward(req, res);
	}
    }
}