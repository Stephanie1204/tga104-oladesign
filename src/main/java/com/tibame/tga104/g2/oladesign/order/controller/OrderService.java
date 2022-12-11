package com.tibame.tga104.g2.oladesign.order.controller;

import java.util.List;

import model.dao.OrderDAOJdbc;

public class OrderService {
	
	private OrderDAO orderDao = new OrderDAOJdbc();
	
	public static void main(String[] args) {
		//測試
		OrderService orderService = new OrderService();
		List<OrderBean> selects = orderService.select_Mem(null);
		System.out.println("selects="+selects);
	}
	
	public List<OrderBean> select_Mem(OrderBean bean) {
		List<OrderBean> result = null;
		
		return result;
	}
	
	public List<OrderBean> select_Com(OrderBean bean) {
		List<OrderBean> result = null;
		
		return result;
	}
	
	public OrderBean insert(OrderBean bean) {
		OrderBean result = null;
		//insert測試用廠商
		bean.setComTaxId("23045921");
		//
		return result;
	}

	public OrderBean updateOrderStatus(OrderBean bean) {
		OrderBean result = null;
		return result;
	}
	
	public OrderBean updateShippingStatus(OrderBean bean) {
		OrderBean result = null;
		return result;
	}
	
}

