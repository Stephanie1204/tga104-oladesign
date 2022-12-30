package com.tibame.tga104.g2.oladesign.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductBean;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductService;

//servlet3.0以後，我們可以不用再web.xml裏面配置servlet，只需要加上@WebServlet註解就可以修改該servlet的屬性
@WebServlet(urlPatterns = { "/pages/product.controller" })

//Servlet 必須繼承 javax.servlet.http.HttpServlet
public class ProductServlet extends HttpServlet {
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
		String comTaxId = request.getParameter("comTaxId");// gets from name=id...etc
		String tempTypeCode = request.getParameter("typeCode");// not null
		String tempStyleCode = request.getParameter("styleCode");// not null
		String tempName = request.getParameter("name");// not null
		String tempPrice = request.getParameter("price");// not null
		String intro = request.getParameter("intro");
		String tempStock = request.getParameter("stock");// not null
		String tempSafeStock = request.getParameter("safeStock");// not null
		String tempStatus = request.getParameter("status");// not null
		String prodaction = request.getParameter("prodaction");

//For Cart
		String tempQuantity = request.getParameter("quantity");

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
				if (tempName == null || tempName.length() == 0) {
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
				System.out.println(typeCode);
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

		String name = "";
		if (tempName != null && tempName.length() != 0) {
			try {
				name = tempName;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		int price = 0;
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
//For cart		
		int quantity = 0;
		if (tempQuantity != null && tempQuantity.length() != 0) {
			try {
				quantity = Integer.parseInt(tempQuantity);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

//
		if (prodaction.equals("Select") && errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/homePage/index.jsp").forward(request, response);
			return;
		}
		if (prodaction.equals("Insert") && errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/CompanyBackEnd-product/company-updateproduct.jsp").forward(request,
					response);
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
			request.getRequestDispatcher("/homePage/searchResults.jsp").forward(request, response);
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
			request.getRequestDispatcher("/CompanyBackEnd-product/company-updateproduct.jsp").forward(request,
					response);

		} else if (prodaction != null && prodaction.equals("SelectById")) {
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");

			bean.setComTaxId(companyMem.getComTaxId());
			List<ProductBean> result = productService.selectByComTaxId(bean.getComTaxId());
			request.setAttribute("selectById", result);
			// 傳遞result list到下面指定的jsp檔
			request.getRequestDispatcher("/pages/salerProducts.jsp").forward(request, response);

		} else if (prodaction != null && prodaction.equals("Update")) {
//要驗證商品屬於該統編			
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");

			if (companyMem.getComTaxId().equals(comTaxId)) {
				ProductBean result = productService.update(bean);
				if (result == null) {
					errors.put("action", "Update fail");
				} else {
					request.setAttribute("update", result);
				}
			}

			request.getRequestDispatcher("/pages/productUpdate.jsp").forward(request, response);

		} else if (prodaction != null && prodaction.equals("Delete")) {
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");
			bean.setComTaxId(companyMem.getComTaxId());
			if (companyMem.getComTaxId().equals(comTaxId)) {
				boolean result = productService.delete(bean);
				if (!result) {
					request.setAttribute("delete", 0);
				} else {
					request.setAttribute("delete", 1);
				}
			}

			request.getRequestDispatcher("/pages/salerProducts.jsp").forward(request, response);

		} // Cart
		else if (prodaction != null && prodaction.equals("UpdateCart")) {
			HttpSession session = request.getSession();
			Object objname = session.getAttribute("memId");
			String userId = "";
			if (objname != null) {
				userId = objname.toString();
			}
			productService.updateFromCart(userId, comTaxId, productId, quantity);

			request.getRequestDispatcher("/homePage/shopping_cart.jsp").forward(request, response);

		} else if (prodaction != null && prodaction.equals("AddCart")) {
			HttpSession session = request.getSession();
			Object objname = session.getAttribute("memId");
			String userId = "";
			if (objname != null) {
				userId = objname.toString();
			}

			if (quantity <= 0) {
				quantity = 1;
				productService.insertCart(userId, comTaxId, productId, quantity);

				request.getRequestDispatcher("/homePage/productPage.jsp").forward(request, response);
			} else if (quantity > 9) {
				quantity = 9;
				productService.insertCart(userId, comTaxId, productId, quantity);

				request.getRequestDispatcher("/homePage/productPage.jsp").forward(request, response);
			} else {
				productService.insertCart(userId, comTaxId, productId, quantity);

				request.getRequestDispatcher("/homePage/productPage.jsp").forward(request, response);
			}
		} else if (prodaction != null && prodaction.equals("AddCartByPage")) {
			HttpSession session = request.getSession();
			Object objname = session.getAttribute("memId");
			String userId = "";
			if (objname != null) {
				userId = objname.toString();
			}
			productService.insertCart(userId, comTaxId, productId, quantity);

			List<ProductBean> result = productService.select(bean);

			request.setAttribute("select", result);

			request.getRequestDispatcher("/homePage/searchResults.jsp").forward(request, response);
		} else if (prodaction != null && prodaction.equals("DeleteFromCart")) {
			HttpSession session = request.getSession();
			Object objname = session.getAttribute("memId");
			String userId = "";
			if (objname != null) {
				userId = objname.toString();
			}
			productService.deleteFromCart(userId, comTaxId, productId);

			request.getRequestDispatcher("/homePage/shopping_cart.jsp").forward(request, response);

		} else {
			errors.put("action", "Unknown Action:" + prodaction);
			request.getRequestDispatcher("/homePage/index.jsp").forward(request, response);
		}

		System.out.println("pass4");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
