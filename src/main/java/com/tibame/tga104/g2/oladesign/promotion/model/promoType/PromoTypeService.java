package com.tibame.tga104.g2.oladesign.promotion.model.promoType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoTypeService {

	@Autowired
	private PromoTypeDAOInterface dao;

//	public PromoTypeService() {
////		dao = new PromoTypeJDBCDAO();
//		dao = new PromoTypeJNDIDAO();
//	}

	public PromoTypeVO addPromoType(String code, String name) {
		PromoTypeVO promoTypeVO = new PromoTypeVO();
		promoTypeVO.setCode(code);
		promoTypeVO.setName(name);

		dao.insert(promoTypeVO);
		return promoTypeVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addPromoType(PromoTypeVO promoTypeVO) {
		dao.insert(promoTypeVO);
	}

	public PromoTypeVO updatePromoType(String code, String name) {
		PromoTypeVO promoTypeVO = new PromoTypeVO();
		promoTypeVO.setCode(code);
		promoTypeVO.setName(name);

		dao.update(promoTypeVO);
		return promoTypeVO;
	}

	// 預留給 Struts 2 用的
	public void updatePromoType(PromoTypeVO promoTypeVO) {
		dao.update(promoTypeVO);
	}

	public void deletePromoType(String code) {
		dao.delete(code);
	}

	public PromoTypeVO getOnePromo(String code) {
		return dao.findByPrimaryKey(code);
	}

	public List<PromoTypeVO> getAll() {
		return dao.getAll();
	}

}
