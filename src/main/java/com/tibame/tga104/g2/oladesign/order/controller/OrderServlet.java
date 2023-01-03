package com.tibame.tga104.g2.oladesign.order.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tibame.tga104.g2.oladesign.CompanyMember.vo.Company_MemVO;
import com.tibame.tga104.g2.oladesign.order.model.OrderBean;
import com.tibame.tga104.g2.oladesign.order.model.OrderService;

//servlet3.0以後，我們可以不用再web.xml裏面配置servlet，只需要加上@WebServlet註解就可以修改該servlet的屬性
@WebServlet(urlPatterns = { "/pages/order.controller" })

//Servlet 必須繼承 javax.servlet.http.HttpServlet
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

    
	// create productService object
	private OrderService orderService;

	// override init method, called when the servlet is created
	@Override
	public void init() throws ServletException {
		orderService = new OrderService();
	}

	// override doGet method
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("pass1");
//接受資料
//if XX.jsp has a "form action = this servelet url"
//getParameter gets the value from name="XXX"
		String receiver = request.getParameter("receiver");// gets from name=id...etc
		String address = request.getParameter("address");// gets from name=id...etc
		String address_zone = request.getParameter("address_zone");// not null
		String coupon = request.getParameter("coupon");
		String tempPoint_use = request.getParameter("point_use");// not null
		String comTaxId = request.getParameter("comTaxId");
		String prodaction = request.getParameter("prodaction");
		String tempMemId = request.getParameter("memberId");
		String tempOrderId = request.getParameter("orderId");
		String shipStatus = request.getParameter("shipStatus");
		String orderStatus = request.getParameter("orderStatus");

//驗證資料 select不會經過此流程
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		if (prodaction != null && prodaction.equals("PlaceOrder")) {
			if (receiver == null || receiver.length() == 0) {
				errors.put("receiver", "不得為空白");
			}
			if (address == null || address.length() == 0) {
				errors.put("address", "請填寫收件地址");
			}
			if (address_zone == null || address_zone.length() == 0) {
				errors.put("address_zone", "請選擇郵遞區號");
			}
		}
		System.out.println("pass2");
//轉換資料 檢查格式
		int point_use = 0;
		if (tempPoint_use != null && tempPoint_use.length() != 0) {
			try {
				point_use = Integer.parseInt(tempPoint_use);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("point_use", "point_use must be a number");
			}
			if (point_use > orderService.getPoint(tempMemId)) {// 暫定使用者ID
				errors.put("pointError", "紅利點數不足");
			}
		}
		//
		if (prodaction.equals("Coupon") && (!coupon.equals(orderService.getCoupon(comTaxId)) || coupon.length() == 0)) {// 暫定使用者ID
			errors.put("couponError", "無此序號");
			request.getRequestDispatcher("/homePage/checkOut.jsp").forward(request, response);
			return;
		} else if (prodaction.equals("Coupon") && coupon.equals(orderService.getCoupon(comTaxId))) {
			errors.put("couponError", "使用成功");

			request.setAttribute("AfterDiscount", orderService.getDiscountTotalPrice(tempMemId, comTaxId, coupon));
			request.getRequestDispatcher("/homePage/checkOut.jsp").forward(request, response);
			return;
		}
		// 驗證序號用 因為prodaction為null故要先在這邊判斷
		if (prodaction.equals("PlaceOrder") && errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/homePage/checkOut.jsp").forward(request, response);
			return;
		}

//呼叫Model

		OrderBean bean = new OrderBean();
		bean.setReceiver(receiver);
		bean.setAddress(address_zone + " " + address);
		bean.setCoupon(coupon);
		bean.setPointUse(point_use);
		
		// insert測試用
		bean.setMemId(tempMemId);
		bean.setComTaxId(comTaxId);
//
		System.out.println("pass3");
		System.out.println(bean);

//根據Model執行結果導向View

		// 如果jsp點擊select按鈕,傳送input中的name="prodaction" 其值value="Select"來servlet
		// 對應到這邊
		if (prodaction != null && prodaction.equals("PlaceOrder")) {
			if (orderService.insert(bean)) {
				// System.out.print(OrderService.genAioCheckOutOneTime(bean));
				request.setAttribute("directHTML", OrderService.genAioCheckOutOneTime(bean));

				request.getRequestDispatcher("/homePage/directPage.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/homePage/error.jsp").forward(request, response);

			}
		} else if (prodaction != null && prodaction.equals("SelectOrderItems_Com")) {
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");
//			System.out.println(session.getAttribute("memId"));
//			System.out.println(companyMem.getComTaxId());
			//
			if (companyMem.getComTaxId().equals(orderService.getOrder(tempOrderId).getComTaxId())) {
				// 商家搜尋--驗證成功
				request.setAttribute("order", orderService.getOrder(tempOrderId));
				request.setAttribute("orderItems", orderService.getOrderItems(tempOrderId));

				request.getRequestDispatcher("/CompanyBackEnd-order/company-order.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/CompanyBackEnd-order/company-orderlist.jsp").forward(request, response);
			}
		} else if (prodaction != null && prodaction.equals("SelectOrderItems_Mem")) {
			// 買家搜尋--驗證成功
			HttpSession session = request.getSession();
			if (session.getAttribute("memId").equals(orderService.getOrder(tempOrderId).getMemId())) {

				request.setAttribute("order", orderService.getOrder(tempOrderId));
				request.setAttribute("orderItems", orderService.getOrderItems(tempOrderId));
//===================================================================================需更改網址
				request.getRequestDispatcher("買家訂單項目前端").forward(request, response);
			} else {
				request.getRequestDispatcher("買家訂單列表前端").forward(request, response);
			}
		} else if (prodaction != null && prodaction.equals("UpdateShipStatus")) {
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");

			if (companyMem.getComTaxId().equals(orderService.getOrder(tempOrderId).getComTaxId())) {
				orderService.updateShippingStatus(tempOrderId, Integer.parseInt(shipStatus));

				request.getRequestDispatcher("/CompanyBackEnd-order/company-orderlist.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/CompanyBackEnd-order/company-orderlist.jsp").forward(request, response);
			}
		} else if (prodaction != null && prodaction.equals("UpdateOrderStatus")) {
			HttpSession session = request.getSession();
			Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");

			if (companyMem.getComTaxId().equals(orderService.getOrder(tempOrderId).getComTaxId())) {
				orderService.updateOrderStatus(tempOrderId, Integer.parseInt(orderStatus));

				request.getRequestDispatcher("/CompanyBackEnd-order/company-orderlist.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/CompanyBackEnd-order/company-orderlist.jsp").forward(request, response);
			}
		} else {
			errors.put("action", "Unknown Action:" + prodaction);
			request.getRequestDispatcher("/homePage/error.jsp").forward(request, response);
		}
		System.out.println("pass4");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
