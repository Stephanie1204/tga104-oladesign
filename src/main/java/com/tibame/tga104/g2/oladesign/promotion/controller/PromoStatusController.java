package com.tibame.tga104.g2.oladesign.promotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.promotion.model.promoStatus.PromoStatusService;
import com.tibame.tga104.g2.oladesign.promotion.model.promoStatus.PromoStatusVO;

@RestController
public class PromoStatusController {
	@Autowired
	PromoStatusService promoStatusService;
	
	@GetMapping("/promoStatus")
	public PromoStatusVO getVO(@RequestParam("promotStatusCode") String promotStatusCode) {
		PromoStatusVO vo = promoStatusService.getOnePromoStatus(promotStatusCode);
		return vo;
	}
}
