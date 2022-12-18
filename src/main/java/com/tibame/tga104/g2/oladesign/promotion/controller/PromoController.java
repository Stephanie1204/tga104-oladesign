package com.tibame.tga104.g2.oladesign.promotion.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoService;
import com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoVO;

@RestController
@CrossOrigin
public class PromoController {

	@Autowired
	PromoService service;
	@GetMapping("/promo") // get all promo by comTaxId
	public List<PromoVO> getAllPromo(HttpSession session) {
		String comTaxId  = (String) session.getAttribute("comTaxId");
		List<PromoVO> vo = service.getAll(comTaxId);
		return vo;
	}
	

	@GetMapping("/promo:promoId") // get promo information
	public PromoVO getVO(@RequestParam("promoId") Integer promoId) {
		PromoVO vo = service.getOnePromo(promoId);
		return vo;
	}

	@PostMapping("/promo") // create new promo (should auto set comTaxId)
	public PromoVO postVO(@RequestBody PromoVO promoVO, HttpSession session) {
		String comTaxId  = (String) session.getAttribute("comTaxId");
		promoVO.setComTaxId(comTaxId);
		return service.addPromo(promoVO);
	}

	@PutMapping("/promo") // update
	public PromoVO putVO(@RequestBody PromoVO promoVO) {
		PromoVO vo = service.update(promoVO);
		return vo;
	}

	@DeleteMapping("/promo")
	public Boolean deleteVO(@RequestParam Integer promoId) {
		return service.deletePromo(promoId);
	}

}
