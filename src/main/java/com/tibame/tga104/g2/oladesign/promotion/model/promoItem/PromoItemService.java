package com.tibame.tga104.g2.oladesign.promotion.model.promoItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoItemService {
	@Autowired
	private PromoItemDAOInterface dao;
	
//	public PromoItemService() {
//		dao = new PromoItemJNDIDAO();
//	}
	
	public PromoItemVO addPromoItem(PromoItemVO promoItemVO) {
		dao.insert(promoItemVO);
		return promoItemVO;
	}
	
	public PromoItemVO updatePromoItem(PromoItemVO promoItemVO) {
		dao.update(promoItemVO);
		return promoItemVO;
	}
	public Boolean deletePromoItem(Integer promoId, Integer prodId) {
		return dao.delete(promoId,prodId) > 0;
	}
	public List<PromoItemVO> getAllByPromoId(Integer promoId) {
		return  dao.findAllByPromoId(promoId);
	}
	public List<PromoItemVO> getAllByProdId(Integer prodId){
		return dao.findAllByProdId(prodId);
	}
	
	
	
	
}
