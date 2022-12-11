package com.tibame.tga104.g2.oladesign.promotion.model.promoItem;

import java.util.List;

public class PromoItemService {
	private PromoItemDAOInterface dao;
	
	public PromoItemService() {
		dao = new PromoItemJNDIDAO();
	}
	
	public PromoItemVO addPromoItem(Integer promoId, Integer prodId, String code,Integer discount) {
		PromoItemVO promoItemVO = new PromoItemVO();
		promoItemVO.setPromoId(promoId);
		promoItemVO.setProdId(prodId);
		promoItemVO.setCode(code);
		promoItemVO.setDiscount(discount);
		dao.insert(promoItemVO);
		
		return promoItemVO;
	}
	
	public PromoItemVO updatePromoItem(Integer promoId, Integer prodId, String code,Integer discount) {
		PromoItemVO promoItemVO = new PromoItemVO();
		promoItemVO.setPromoId(promoId);
		promoItemVO.setProdId(prodId);
		promoItemVO.setCode(code);
		promoItemVO.setDiscount(discount);
		dao.update(promoItemVO);
		
		return promoItemVO;
	}
	
	public void deletePromoItem(Integer promoId, Integer prodId) {
		dao.delete(promoId ,prodId);
	}
	public List<PromoItemVO> getAllByPromoId(Integer promoId) {
		return  dao.findAllByPromoId(promoId);
	}
	public List<PromoItemVO> getAllByProdId(Integer prodId){
		return dao.findAllByProdId(prodId);
	}
	
	
	
	
}
