package com.tibame.tga104.g2.oladesign.productbyshop;

import java.util.List;

public class ProductByShopService {

	private ProductByShopDAO dao;
	public ProductByShopService() {
		dao = new ProductByShopJDBCDAO();
	}
	
	public  List<ProductByShopVO> getProductByComTaxId(String comTaxId){
		return dao.getProductByComTaxId(comTaxId);
	}
}
