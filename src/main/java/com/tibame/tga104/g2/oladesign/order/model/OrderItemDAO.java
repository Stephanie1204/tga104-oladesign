
package com.tibame.tga104.g2.oladesign.order.model;

import java.util.List;

public interface OrderItemDAO {

	public abstract List<OrderItemBean> select(int productId);
	
	public abstract List<OrderItemBean> select(String orderId);

	public abstract void insert(OrderItemBean bean);

	public abstract Integer update(OrderItemBean bean);
}
