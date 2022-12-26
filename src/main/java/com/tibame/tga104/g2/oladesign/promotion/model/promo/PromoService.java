package com.tibame.tga104.g2.oladesign.promotion.model.promo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoService {

	@Autowired
	private PromoDAOInterface dao;

//	//建立一個新的service物件同時，就會新增new出一個dao物件
//	public PromoService() { 
//		dao = new PromoJNDIDAO();
//	}
	
	public Boolean checkCoupon (String coupon){
		return dao.checkCoupon(coupon);
	}
	
	public PromoVO addPromo(PromoVO promoVO) {
		long startDate = promoVO.getStartDate().getTime();
		long now = new Date().getTime();
		if(startDate-now <= 0) {
			return promoVO;
		}

		dao.insert(promoVO);
		return promoVO;
	}

//	public PromoVO update(PromoVO promoVO) {
//		if(!promoVO.getPromoStatus().equals("PS001")) {
//			return promoVO;
//		}
//		dao.update(promoVO);
//		return promoVO;
//	}
	
	public Boolean update(PromoVO promoVO) {
		if(!promoVO.getPromoStatus().equals("PS001")) {
			return false;
		}
		dao.update(promoVO);
		return true;
	}

	public Boolean deletePromo(Integer promoId) {
		return dao.delete("PS003",promoId)>0;
	}

	public PromoVO getOnePromo(Integer promoId) {
		return dao.findByPrimaryKey(promoId);
	}

	public List<PromoVO> getAll(String comTaxId) {
		return dao.getAll(comTaxId);
	}

	// for checking the date to update promo status
	public List<PromoVO> getAllPromo(){
		return dao.getAllPromo();
	}
	
	public PromoVO getCurrentPromo(String comTaxId) {
		return dao.getCurrentPromo(comTaxId);
	}
}
