package com.tibame.tga104.g2.oladesign.order.model;

import java.util.List;


public interface OrderDAO {
	
	public abstract List<OrderBean> select_Mem(String memberId);
	
	public abstract List<OrderBean> select_Com(String comTaxId);
	
	public abstract List<OrderBean> select_Com(String comTaxId, int orderStatus);
	
	public abstract OrderBean select(String orderId);
	
	public abstract void insert(OrderBean orderBean);
	
	public abstract void updateOrderStatus(String orderId, int orderStatus);
	
	public abstract void updateShippingStatus(String orderId, int shippingStatus);

	public abstract int getPoint(String memberId);
	
	public abstract List<DiscountItem> getDiscountItem(String coupon);
	
	public abstract String getCoupon(String comTaxId);
	
	public abstract void upDatePoint(String memberId, int point);
}
