package com.tibame.tga104.g2.oladesign.order.model;

import java.util.List;


public interface OrderDAO {
	
	public abstract List<OrderBean> select_Mem(int memberId);
	
	public abstract List<OrderBean> select_Com(String comTaxId);
	
	public abstract void insert(OrderBean orderBean);
	
	public abstract OrderBean updateOrderStatus(OrderBean orderBean);
	
	public abstract OrderBean updateShippingStatus(OrderBean orderBean);

	public abstract int getPoint(String memberId);
}
