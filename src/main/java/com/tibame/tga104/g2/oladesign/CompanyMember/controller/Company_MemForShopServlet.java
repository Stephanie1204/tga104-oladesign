package com.tibame.tga104.g2.oladesign.CompanyMember.controller;

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
import com.tibame.tga104.g2.oladesign.CompanyMember.service.Company_MemService;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemByCheckVO;
import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.productbyshop.ProductByShopService;
import com.tibame.tga104.g2.oladesign.productbyshop.ProductByShopVO;

@WebServlet("/shophome/shopinfo.do")
@MultipartConfig
public class Company_MemForShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		if("doGetShopInfo".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String comTaxId = req.getParameter("comTaxId");
		
			Company_MemVO company_MemVO = new Company_MemVO();
			Company_MemByCheckVO result = new Company_MemByCheckVO();

			Company_MemService company_memSvc = new Company_MemService();
			company_MemVO = company_memSvc.getOneCompany_Mem(comTaxId);

			result.setStoreName(company_MemVO.getStoreName());
			result.setStoreIntro(company_MemVO.getStoreIntro());
			result.setStoreLogoString(company_MemVO.getStoreLogoString());
			result.setStoreBannerString(company_MemVO.getStoreBannerString());

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(result);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
		
		
		if ("doGetShopList".equals(action)) {
			Company_MemService company_memSvc = new Company_MemService();
			List<Company_MemVO> company_memVO = company_memSvc.getAll();
			List<Company_MemByCheckVO> result = new ArrayList<Company_MemByCheckVO>();
			
			for(int i = 0; i < company_memVO.size(); i++) {
				Company_MemVO company_Mem = company_memVO.get(i);
				Company_MemByCheckVO company_MemByCheckVO = new Company_MemByCheckVO();
				company_MemByCheckVO.setStoreName(company_Mem.getStoreName());
				company_MemByCheckVO.setStoreLogoString(company_Mem.getStoreLogoString());
				company_MemByCheckVO.setComTaxId(company_Mem.getComTaxId());
				result.add(company_MemByCheckVO);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(result);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
		
		if ("doGetProducts".equals(action)) {
			String comTaxId = req.getParameter("comTaxId");
			ProductByShopService productbyshopSvc = new ProductByShopService();
			List<ProductByShopVO> productbyshopVO = productbyshopSvc.getProductByComTaxId(comTaxId);

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(productbyshopVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
	
	}
}
