package com.tibame.tga104.g2.oladesign.order.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tibame.tga104.g2.oladesign.intermail.model.IntermailVO;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductBean;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductDAORedis;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductDAO_Cart;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

public class OrderService {

	private OrderDAO orderDao = new OrderDAOJdbc();
	private OrderItemDAO orderItemDao = new OrderItemDAOJdbc();
	private ProductDAO_Cart productDao_Cart = new ProductDAORedis();
	public static AllInOne all = new AllInOne("");

	public static void main(String[] args) {
		// 測試
		OrderService orderService = new OrderService();
		List<OrderBean> selects = orderService.select_Mem(null);
		System.out.println("selects=" + selects);
	}

	public List<OrderBean> select_Mem(String memberId) {
		List<OrderBean> result = null;

		result = orderDao.select_Mem(memberId);

		return result;
	}

	public List<OrderBean> select_Com(String comTaxId) {
		List<OrderBean> result = null;

		result = orderDao.select_Com(comTaxId);

		return result;
	}

	public List<OrderBean> select_Com(String comTaxId, int orderStatus) {
		List<OrderBean> result = null;

		result = orderDao.select_Com(comTaxId, orderStatus);

		return result;
	}
	
	public List<String> selectSaler(String userId) {
		List<String> result = null;

		List<String> temp = productDao_Cart.selectSaler(userId);

		if (temp != null) {
			result = temp;
		}
		return result;
	}

	//
	public static String genAioCheckOutOneTime(OrderBean bean) {
		AioCheckOutOneTime obj = new AioCheckOutOneTime();
		obj.setMerchantTradeNo(bean.getOrderId());
		obj.setMerchantTradeDate(bean.getOrderTime_toSec());
		obj.setTotalAmount(Integer.toString(bean.getAmount()));
		obj.setTradeDesc("test Description");
		obj.setItemName("TestOrder");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setClientBackURL("http://localhost:8080/oladesign/homePage/index.jsp");
		obj.setNeedExtraPaidInfo("N");
		obj.setRedeem("Y");
		String form = all.aioCheckOut(obj, null);
		htmlform = form;
		return form;
	}

	public static String htmlform;

	public String getForm() {
		return htmlform;
	}

	// 到這裡bean只會有receiver, address, pointUse, coupon
	public boolean insert(OrderBean bean) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String sdate = dateFormat.format(date);
		bean.setOrderTime_toSec(sdate);
		if (productDao_Cart.salerExist(bean.getMemId(), bean.getComTaxId())) {
			// 產生訂單編號
			String deleteChar = "[/: ]";
			bean.setOrderId(bean.getMemId().trim() + bean.getOrderTime_toSec().replaceAll(deleteChar, "").trim().substring(4));

			bean.setOrderStatus(1);
			bean.setShippingStatus(1);
			//
			int amountPrice = 0;
			if (bean.getCoupon() != null && bean.getCoupon().length() != 0
					&& bean.getCoupon().equals(getCoupon(bean.getComTaxId()))) {
				amountPrice = getDiscountTotalPrice(bean.getMemId(), bean.getComTaxId(), bean.getCoupon());
			} else {
				amountPrice = productDao_Cart.getTotal(bean.getMemId(), bean.getComTaxId());
			}
			// 總金額 - 使用紅利
			bean.setAmount(amountPrice - bean.getPointUse());
			// 紅利取得
			bean.setPointGet((int) (amountPrice / 100));
			//
			int point = getPoint(bean.getMemId()) + bean.getPointGet() - bean.getPointUse();
			orderDao.upDatePoint(bean.getMemId(), point);
			// 存取訂單
			orderDao.insert(bean);
			// 訂單項目存取
			for (ProductBean item : productDao_Cart.selectCart(bean.getMemId(), bean.getComTaxId())) {
				OrderItemBean orderItem = new OrderItemBean();
				orderItem.setOrderId(bean.getOrderId());
				orderItem.setProductId(item.getProductId());
				orderItem.setProductName(item.getName());
				orderItem.setPrice(item.getPrice());
				orderItem.setQuantity(item.getCartQuantity());
				orderItemDao.insert(orderItem);
			}
			// 刪除購物車內容物
			productDao_Cart.deleteFromCartByComTaxId(bean.getMemId(), bean.getComTaxId());
			// 付款
			genAioCheckOutOneTime(bean);
//			System.out.println(genAioCheckOutOneTime(bean));
			return true;
		}
		return false;
	}

	public int getTotalPrice(String userId, String comTaxId) {
		return productDao_Cart.getTotal(userId, comTaxId);
	}

	public void updateOrderStatus(String orderId, int orderStatus) {
		orderDao.updateOrderStatus(orderId, orderStatus);
	}

	public void updateShippingStatus(String orderId, int shippingStatus) {
		orderDao.updateShippingStatus(orderId, shippingStatus);
	}

	public int getPoint(String memberId) {
		return orderDao.getPoint(memberId);
	}

	public String getCoupon(String comTaxId) {
		return orderDao.getCoupon(comTaxId);
	}

	public int getDiscountTotalPrice(String userId, String comTaxId, String coupon) {
		return productDao_Cart.getTotal(userId, comTaxId, coupon);
	}
	
	//
	public List<OrderItemBean> getOrderItems(String orderId){
		return orderItemDao.select(orderId);
	}
	
	public OrderBean getOrder(String orderId){
		return orderDao.select(orderId);
	}
	
	public List<OrderBean> getAll() {
		return orderDao.getAll();
	}
	public OrderBean getCheckOne(String orderId) {  //查看選擇的訂單編號明細
		return orderDao.getCheckOne(orderId);
	}
	
//	public List<OrderBean> getSearch(String orderId,String comTaxId,String memId, String receiver ,String orderStatus,String shippingStatus) {
//		return orderDao.getSearch(orderId, comTaxId, memId, receiver, orderStatus, shippingStatus);
//	} 
//	public List<OrderBean> getSearch(String orderId,String comTaxId,String memId, String receiver ,Integer orderStatus,Integer shippingStatus) {
	public List<OrderBean> getSearch(String orderId,String comTaxId,String memId, String receiver ,Integer orderStatus,Integer shippingStatus,String startTime,String overTime) {
		return orderDao.getSearch(orderId, comTaxId, memId, receiver, orderStatus, shippingStatus,startTime,overTime);
	} 
	
//	public List<OrderBean> getSearch(String orderId,String comTaxId) {
//		return orderDao.getSearch(orderId, comTaxId);
//	} 
	

	
}
