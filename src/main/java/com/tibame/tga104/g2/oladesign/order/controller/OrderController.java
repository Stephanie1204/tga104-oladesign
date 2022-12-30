package com.tibame.tga104.g2.oladesign.order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.order.model.OrderBean;
import com.tibame.tga104.g2.oladesign.order.model.OrderItemBean;
import com.tibame.tga104.g2.oladesign.order.model.OrderService;

@RestController
@CrossOrigin
public class OrderController {

	OrderService service = new OrderService();
	
//	@GetMapping("/")
//	public List<OrderBean> getAllByMemId(Integer memId) {
//		return service.select_Mem("memId");
//	}
	
	@GetMapping("/order")
	public List<OrderBean> getAllByMemId(@RequestParam("memId") String memId) {
		return service.select_Mem(memId);
	}
	
	@GetMapping("/order/:id")
	public OrderBean getOneOrder(@RequestParam("orderId")String orderId) {
		return service.getOrder(orderId);
	}
	
	@GetMapping("/orderItem")
	public List<OrderItemBean> getOrderItem(@RequestParam("orderId") String orderId) {
		return service.getOrderItems(orderId);
		
	}
}
