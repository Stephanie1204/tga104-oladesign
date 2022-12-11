package com.tibame.tga104.g2.oladesign.promotion.model.promo;

import java.util.List;

public interface PromoDAOInterface {

	public void insert(PromoVO prmotionVO);
	public void update(PromoVO prmotionVO);
	public void delete(Integer promoId);
	public PromoVO findByPrimaryKey(Integer promoId); //頁面輸入id搜尋促銷活動
	public List<PromoVO> getAll();
}
