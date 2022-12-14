package com.tibame.tga104.g2.oladesign.promotion.model.promoStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tibame.tga104.g2.oladesign.promotion.model.promoStatus.PromoStatusDAOInterface;
import com.tibame.tga104.g2.oladesign.promotion.model.promoStatus.PromoStatusJNDIDAO;
import com.tibame.tga104.g2.oladesign.promotion.model.promoStatus.PromoStatusVO;

@Service
public class PromoStatusService {
	
	@Autowired
	private PromoStatusDAOInterface dao;

	public PromoStatusVO addPromoStatus(String code, String name) {
		PromoStatusVO promoStatusVO = new PromoStatusVO();
		promoStatusVO.setCode(code);
		promoStatusVO.setName(name);

		dao.insert(promoStatusVO);
		return promoStatusVO;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addPromoStatus(PromoStatusVO promoStatusVO) {
		dao.insert(promoStatusVO);
	}

	public PromoStatusVO updatePromoStatus(String code, String name) {
		PromoStatusVO promoStatusVO = new PromoStatusVO();
		promoStatusVO.setCode(code);
		promoStatusVO.setName(name);

		dao.update(promoStatusVO);
		return promoStatusVO;
	}

	// 預留給 Struts 2 用的
	public void updatePromoStatus(PromoStatusVO promoStatusVO) {
		dao.update(promoStatusVO);
	}

	public void deletePromoStatus(String code) {
		dao.delete(code);
	}

	public PromoStatusVO getOnePromoStatus(String code) {
		return dao.findByPrimaryKey(code);
	}

	public List<PromoStatusVO> getAll() {
		return dao.getAll();
	}

}
