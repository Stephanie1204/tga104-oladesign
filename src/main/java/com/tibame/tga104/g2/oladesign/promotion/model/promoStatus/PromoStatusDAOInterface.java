package com.tibame.tga104.g2.oladesign.promotion.model.promoStatus;

import java.util.List;

public interface PromoStatusDAOInterface {

	public void insert(PromoStatusVO promoStatusVO);
	public void update(PromoStatusVO promoStatusVO);
	public void delete(String code);
	public PromoStatusVO findByPrimaryKey(String code);
	public List<PromoStatusVO> getAll();
}
