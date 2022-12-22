package com.tibame.tga104.g2.oladesign.order.model;

import java.util.List;


public interface OrderDAO {
	
	public abstract List<OrderBean> select_Mem(String memberId);
	
	public abstract List<OrderBean> select_Com(String comTaxId);
	
	public abstract void insert(OrderBean orderBean);
	
	public abstract OrderBean updateOrderStatus(OrderBean orderBean);
	
	public abstract OrderBean updateShippingStatus(OrderBean orderBean);

	public abstract int getPoint(String memberId);
	
	public abstract List<DiscountItem> getDiscountItem(String coupon);
	
	public abstract String getCoupon(String comTaxId);
	
	public abstract void upDatePoint(String memberId, int point);
}
