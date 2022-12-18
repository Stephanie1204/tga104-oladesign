package com.tibame.tga104.g2.oladesign.promotion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoVO;
import com.tibame.tga104.g2.oladesign.promotion.model.promoItem.PromoItemService;
import com.tibame.tga104.g2.oladesign.promotion.model.promoItem.PromoItemVO;

//@CrossOrigin
@RestController
public class PromoItemController {
	
	@Autowired
	PromoItemService service;

	@GetMapping("/promoItem")
	public List<PromoItemVO> getVO(@RequestParam("promoId")Integer promoId){
			List<PromoItemVO> vo = service.getAllByPromoId(promoId);
		return vo;
	}
	
	@PostMapping("/promoItem")   //add new promoItem in one promo
	public PromoItemVO addPromoItem (@RequestBody PromoItemVO promoItemVO){
		PromoItemVO vo = service.addPromoItem(promoItemVO);
		return vo;
	}
	
	@PutMapping("/promoItem")
	public PromoItemVO putVO(@RequestBody PromoItemVO promoItemVO) {
		PromoItemVO vo = service.updatePromoItem(promoItemVO);
		return vo;
	}
	
	@DeleteMapping("/promoItem")
	public Boolean deleteVO(
			@RequestParam("promoId")Integer promoId,@RequestParam("prodId")Integer prodId) {
		return service.deletePromoItem(promoId, prodId) ;
		
	}
}
