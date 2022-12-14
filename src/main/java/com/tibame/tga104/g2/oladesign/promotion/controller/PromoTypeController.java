package com.tibame.tga104.g2.oladesign.promotion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.promotion.model.promoType.PromoTypeService;
import com.tibame.tga104.g2.oladesign.promotion.model.promoType.PromoTypeVO;

@RestController
public class PromoTypeController {

	@Autowired
	PromoTypeService service;
	
	@GetMapping("/promoType")
	public PromoTypeVO getVO (@RequestParam("code") String code) {
		PromoTypeVO vo = service.getOnePromo(code);
		return vo;
	}
	
	
}
