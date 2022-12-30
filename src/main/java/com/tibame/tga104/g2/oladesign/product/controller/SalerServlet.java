package com.tibame.tga104.g2.oladesign.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductBean;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductImageBean;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductService;

@WebServlet(urlPatterns = { "/pages/saler.controller" })
@MultipartConfig(fileSizeThreshold = 1024 * 10, // 10 KB
		maxFileSize = 1024 * 10340, 
		maxRequestSize = 1024 * 10240 // 1 MB
)
//Servlet 必須繼承 javax.servlet.http.HttpServlet
public class SalerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// create productService object
	private ProductService productService;

	// override init method, called when the servlet is created
	@Override
	public void init() throws ServletException {
		productService = new ProductService();
	}

	// override doGet method
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("pass1");
//接受資料
//if XX.jsp has a "form action = this servelet url"
//getParameter gets the value from name="XXX"
		String tempProductId = request.getParameter("productId");// gets from name=id...etc
		String comTaxId = request.getParameter("comTaxId");
		String tempTypeCode = request.getParameter("typeCode");// not null
		String tempStyleCode = request.getParameter("styleCode");// not null
		String name = request.getParameter("name");// not null
		String tempPrice = request.getParameter("price");// not null
		String intro = request.getParameter("intro");
		String tempStock = request.getParameter("stock");// not null
		String tempSafeStock = request.getParameter("safeStock");// not null
		String tempStatus = request.getParameter("status");// not null
		String prodaction = request.getParameter("prodaction");
		Part filePart = request.getPart("img_file");

		//
		Collection<Part> fileParts = request.getParts();
		String imageId = request.getParameter("imageId");
		System.out.println(intro);
//驗證資料 select不會經過此流程
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		if (prodaction != null) {
			if (prodaction.equals("Insert") || prodaction.equals("Update") || prodaction.equals("Delete")) {
				if (tempTypeCode == null || tempTypeCode.length() == 0) {
					errors.put("typeCode", "Please enter typeCode for " + prodaction);
				}
				if (tempStyleCode == null || tempStyleCode.length() == 0) {
					errors.put("styleCode", "Please enter styleCode for " + prodaction);
				}
				if (name == null || name.length() == 0) {
					errors.put("name", "Please enter name for " + prodaction);
				}
				if (tempPrice == null || tempPrice.length() == 0) {
					errors.put("price", "Please enter price for " + prodaction);
				}
				if (tempStock == null || tempStock.length() == 0) {
					errors.put("stock", "Please enter stock for " + prodaction);
				}
				if (tempSafeStock == null || tempSafeStock.length() == 0) {
					errors.put("safeStock", "Please enter safeStock for " + prodaction);
				}
				if (tempStatus == null || tempStatus.length() == 0) {
					errors.put("status", "Please enter status for " + prodaction);
				}
			}
		}
		System.out.println("pass2");
//轉換資料 檢查格式
		int productId = 0;
		if (tempProductId != null && tempProductId.length() != 0) {
			try {
				productId = Integer.parseInt(tempProductId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("productId", "productId must be a number");
			}
		}

		String typeCode = "";
		if (tempTypeCode != null && tempTypeCode.length() != 0) {
			try {
				typeCode = tempTypeCode;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		String styleCode = "";
		if (tempStyleCode != null && tempStyleCode.length() != 0) {
			try {
				styleCode = tempStyleCode;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		int price = Integer.MAX_VALUE;
		if (tempPrice != null && tempPrice.length() != 0) {
			try {
				price = Integer.parseInt(tempPrice);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("price", "Price must be a number");
			}
		}

		int stock = 0;
		if (tempStock != null && tempStock.length() != 0) {
			try {
				stock = Integer.parseInt(tempStock);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("stock", "stock must be a number");
			}
		}

		int safeStock = 0;
		if (tempSafeStock != null && tempSafeStock.length() != 0) {
			try {
				safeStock = Integer.parseInt(tempSafeStock);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("safeStock", "safe stock must be a number");
			}
		}

		boolean status = false;
		try {
			status = Boolean.parseBoolean(tempStatus);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errors.put("status", "status erro");
		}

		if (prodaction.equals("Select") && errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/pages/display.jsp").forward(request, response);
			return;
		}
		if (prodaction.equals("Insert") && errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/CompanyBackEnd-product/company-addproduct.jsp").forward(request, response);
			return;
		}

//呼叫Model
		// 測試用廠商統編
		ProductBean bean = new ProductBean();
		bean.setProductId(productId);
		bean.setComTaxId(comTaxId);
		bean.setTypeCode(typeCode);
		bean.setStyleCode(styleCode);
		bean.setName(name);
		bean.setPrice(price);
		bean.setIntro(intro);
		bean.setStock(stock);
		bean.setSafeStock(safeStock);
		bean.setStatus(status);
		// part file is null?
		if(filePart != null) {
			InputStream inputStream = null;
			byte[] buffer = null;
			buffer = filePart.getInputStream().readAllBytes();
			buffer = buffer.length == 0 ? null : buffer;
			bean.setProductImgByteArray(buffer);
			bean.setProductImg(inputStream);
		}
		//
		int currentimgAmount = productService.getImgAmount(productId);
		int count = currentimgAmount;
		int maxAmount = 3;
		List<ProductImageBean> imageList = new ArrayList<ProductImageBean>();
		for (Part imagePart : fileParts) {
			if (count >= maxAmount) {
				break;
			}
			String partName = imagePart.getName(); // form 表單的欄位
			if (partName.equals("img_file1")) {
				ProductImageBean imageBean = new ProductImageBean();
				InputStream inputStream_imgs = null;
				byte[] buffer_imgs = null;
				buffer_imgs = imagePart.getInputStream().readAllBytes();
				buffer_imgs = buffer_imgs.length == 0 ? null : buffer_imgs;
				imageBean.setProductImgByteArray(buffer_imgs);
				imageBean.setProductImg(inputStream_imgs);
				imageBean.setProductId(productId);

				imageList.add(imageBean);
				count++;
			}
		}
		System.out.println("pass3");
		System.out.println(bean);

//根據Model執行結果導向View

		// 如果product.jsp點擊select按鈕,傳送input中的name="prodaction" 其值value="Select"來servlet
		// 對應到這邊
		if (prodaction != null && prodaction.equals("Select")) {
			// 宣告一個list叫做result 並代入從一開始建立的ProductService object 使用其"select method"
			List<ProductBean> result = productService.select(bean);
			// 將得到的list 結果傳送代入下一個要傳遞的參數 並且定義其名稱為"select"(在下一行的display.jsp中的${select}就是這邊來的)
			request.setAttribute("select", result);
			// 傳遞result list到下面指定的jsp檔
			request.getRequestDispatcher("/pages/display.jsp").forward(request, response);
		} else if (prodaction != null && prodaction.equals("Insert")) {
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");
			bean.setComTaxId(companyMem.getComTaxId());
			ProductBean result = productService.insert(bean);
			if (result == null) {
				errors.put("action", "Insert fail");
			} else {
				request.setAttribute("insert", result);
			}
			request.getRequestDispatcher("/CompanyBackEnd-product/company-addproduct.jsp").forward(request, response);

		} else if (prodaction != null && prodaction.equals("SelectById")) {
			List<ProductBean> result = productService.selectByComTaxId(bean.getComTaxId());
			request.setAttribute("selectById", result);
			// 傳遞result list到下面指定的jsp檔
			request.getRequestDispatcher("/CompanyBackEnd-product/company-productlist.jsp").forward(request, response);

		} else if (prodaction != null && prodaction.equals("Update")) {
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");
			if(companyMem.getComTaxId().equals(bean.getComTaxId())) {
				ProductBean result = productService.update(bean);
				if (result == null) {
					errors.put("action", "Update fail");
				} else {
					request.setAttribute("update", result);
				}
				request.getRequestDispatcher("/CompanyBackEnd-product/company-updateproduct.jsp").forward(request,
						response);
			}
		} else if (prodaction != null && prodaction.equals("Delete")) {
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");
			if(companyMem.getComTaxId().equals(bean.getComTaxId())) {
				boolean result = productService.delete(bean);
				if (!result) {
					request.setAttribute("delete", 0);
				} else {
					request.setAttribute("delete", 1);
				}
				request.getRequestDispatcher("/CompanyBackEnd-product/company-productlist.jsp").forward(request, response);

			}
		} else if (prodaction != null && prodaction.equals("addImg")) {
			for (ProductImageBean imgbean : imageList) {
				if (imgbean.getProductId() != 0 && imgbean.getProductImgByteArray() != null) {
					productService.insertImg(imgbean);
				}
			}
			request.getRequestDispatcher("/CompanyBackEnd-product/company-updateproduct.jsp").forward(request,
					response);

		} else if (prodaction != null && prodaction.equals("deleteImg")) {
			productService.deleteImg(Integer.parseInt(imageId));

			request.getRequestDispatcher("/CompanyBackEnd-product/company-updateproduct.jsp").forward(request,
					response);
		} else {
			errors.put("action", "Unknown Action:" + prodaction);
			request.getRequestDispatcher("/CompanyBackEnd-product/company-productlist.jsp").forward(request, response);
		}
		System.out.println("pass4");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
