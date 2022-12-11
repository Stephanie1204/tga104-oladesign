package com.tibame.tga104.g2.oladesign.promotion.model.promoType;

import java.util.List;

public interface PromoTypeDAOInterface {

	public void insert(PromoTypeVO promoTypeVO);
	public void update(PromoTypeVO promoTypeVO);
	public void delete(String code);
	public PromoTypeVO findByPrimaryKey(String code);
	public List<PromoTypeVO> getAll();
	
}

