package com.tibame.tga104.g2.oladesign.promotion.model.promo;

import java.sql.Date;
import java.time.LocalDateTime;
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

	public PromoVO addPromo(String promoName,String comTaxId, Date startDate,Date endDate,String coupon) {
		
		PromoVO promoVO = new PromoVO();
		promoVO.setPromoName(promoName);
		promoVO.setComTaxId(comTaxId);
		promoVO.setStartDate(startDate);
		promoVO.setEndDate(endDate);
		promoVO.setCoupon(coupon);
		dao.insert(promoVO);
		
		return promoVO;
	}
	
	//修改的時候要加進促銷編號，因為是要傳回整筆資料，只是鎖起來不能讓他修改而已
	public PromoVO update(Integer promoId, String comTaxId, String promoName,Date startDate,Date endDate,String coupon) {
		PromoVO promoVO = new PromoVO();
		
		promoVO.setPromoId(promoId);
		promoVO.setComTaxId(comTaxId);
		promoVO.setPromoName(promoName);
		promoVO.setStartDate(startDate);
		promoVO.setEndDate(endDate);
		promoVO.setCoupon(coupon);
		
		return promoVO;
	}
	
	public void deletePromo(Integer promoId) {
		dao.delete(promoId);
	}
	
	public PromoVO getOnePromo(Integer promoId) {
		return dao.findByPrimaryKey(promoId);
	}
	
	public List<PromoVO> getAll() {
		return dao.getAll();
	}

}
