package com.tibame.tga104.g2.oladesign.order.model;

import java.util.List;

import com.tibame.tga104.g2.oladesign.product.model.product.ProductDAORedis;
import com.tibame.tga104.g2.oladesign.product.model.product.ProductDAO_Cart;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

public class OrderService {

	private OrderDAO orderDao = new OrderDAOJdbc();
	private ProductDAO_Cart productDao_Cart = new ProductDAORedis();
	public static AllInOne all = new AllInOne("");

	public static void main(String[] args) {
		// 測試
		OrderService orderService = new OrderService();
		List<OrderBean> selects = orderService.select_Mem(null);
		System.out.println("selects=" + selects);
	}

	public List<OrderBean> select_Mem(OrderBean bean) {
		List<OrderBean> result = null;

		return result;
	}

	public List<OrderBean> select_Com(OrderBean bean) {
		List<OrderBean> result = null;

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
	public static String genAioCheckOutOneTime(OrderBean bean){
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
		if (productDao_Cart.salerExist(bean.getMemId(), bean.getComTaxId())) {
			// 產生訂單編號
//			bean.setOrderId(bean.getComTaxId().trim() + bean.getMemId().trim() + bean.getOrderTime_toSec());
			bean.setOrderId("svoijsobisop12fvdv");
			// 總金額 - 使用紅利
			int amountPrice = productDao_Cart.getTotal(bean.getMemId(), bean.getComTaxId());
			bean.setAmount(amountPrice - bean.getPointUse());
			/*point use jdbc*/
			// 紅利取得
			bean.setPointGet((int) (amountPrice / 100));
			/*point get jdbc*/
			//
			
			//
			orderDao.insert(bean);
			productDao_Cart.deleteFromCartByComTaxId(bean.getMemId(), bean.getComTaxId());
			//付款
			//
			genAioCheckOutOneTime(bean);
//			System.out.println(genAioCheckOutOneTime(bean));
			return true;
		}
		return false;
	}

	public int getTotalPrice(String userId, String comTaxId) {
		return productDao_Cart.getTotal(userId, comTaxId);
	}

	public OrderBean updateOrderStatus(OrderBean bean) {
		OrderBean result = null;
		return result;
	}

	public OrderBean updateShippingStatus(OrderBean bean) {
		OrderBean result = null;
		return result;
	}
	
	public int getPoint(String memberId) {
		return orderDao.getPoint(memberId);
	}
	
}
