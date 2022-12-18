package com.tibame.tga104.g2.oladesign.promotion.model.promo;

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

	public PromoVO addPromo(PromoVO promoVO) {
		dao.insert(promoVO);
		return promoVO;
	}

	// 修改的時候要加進促銷編號，因為是要傳回整筆資料，只是鎖起來不能讓他修改而已
	public PromoVO update(PromoVO promoVO) {
		dao.update(promoVO);
		return promoVO;
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

}
