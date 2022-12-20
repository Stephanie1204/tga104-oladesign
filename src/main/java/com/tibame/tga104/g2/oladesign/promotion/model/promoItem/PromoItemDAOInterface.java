package com.tibame.tga104.g2.oladesign.promotion.model.promoItem;

import java.util.List;

public interface PromoItemDAOInterface {

	public void insert(PromoItemVO promoItemVO);
	public void update(PromoItemVO promoItemVO);
	public int delete(Integer promoId ,Integer prodId);
	public List<PromoItemVO> findAllByPromoId(Integer promoId);  //查這個活動內的所有商品
	public List<PromoItemVO> findAllByProdId(Integer prodId); //查這支商品做過的所有活動
//	public List<PromoItemVO> getAll(); 應該沒這功能
	
}
