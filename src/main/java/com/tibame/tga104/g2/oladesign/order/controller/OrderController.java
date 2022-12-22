package com.tibame.tga104.g2.oladesign.order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.order.model.OrderBean;
import com.tibame.tga104.g2.oladesign.order.model.OrderService;

@RestController
@CrossOrigin
public class OrderController {

	OrderService service = new OrderService();
	
	@GetMapping("/order")
	public List<OrderBean> getAllByMemId(Integer memId) {
		return service.select_Mem("220003");
	}
	
//	@GetMapping("/order")
//	public List<OrderBean> getAllByMemId(@RequestParam("memId") String memId) {
//		return orderSVC.select_Mem(memId);
//	}
}
