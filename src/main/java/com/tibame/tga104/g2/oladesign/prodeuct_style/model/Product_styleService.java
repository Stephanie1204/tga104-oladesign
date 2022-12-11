package com.tibame.tga104.g2.oladesign.prodeuct_style.model;

import java.util.List;


public class Product_styleService {
	private Product_styleDAO_interface dao;
	
	public Product_styleService() {
		 dao = new Product_styleJDBCDAO();
	}
	public Product_styleVO addProduct_style(String styleCode, String styleName) {

		Product_styleVO product_styleVO = new Product_styleVO();

		product_styleVO.setStyleCode(styleCode);
		product_styleVO.setStyleName(styleName);
		dao.insert(product_styleVO);

		return product_styleVO;
	}
	public void deleteProduct_style(String styleCode) {
		dao.delete(styleCode);
	}

	public Product_styleVO getOneProduct_style(String styleCode) {
		return dao.findByPrimaryKey(styleCode);
	}

	public List<Product_styleVO> getAll() {
		return dao.getAll();
	}
}
