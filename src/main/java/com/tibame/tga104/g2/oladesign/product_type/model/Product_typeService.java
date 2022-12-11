package com.tibame.tga104.g2.oladesign.product_type.model;
import java.util.List;


public class Product_typeService {
	private Product_typeDAO_interface dao;

	public Product_typeService() {
		 dao = new Product_typeJDBCDAO();
	}
	public Product_typeVO addProduct_type(String typeCode, String typeName) {

		Product_typeVO product_typeVO = new Product_typeVO();

		product_typeVO.setTypeCode(typeCode);
		product_typeVO.setTypeName(typeName);
		dao.insert(product_typeVO);

		return product_typeVO;
	}
	public void deleteProduct_type(String typeCode) {
		dao.delete(typeCode);
	}

	public Product_typeVO getOneProduct_type(String typeCode) {
		return dao.findByPrimaryKey(typeCode);
	}

	public List<Product_typeVO> getAll() {
		return dao.getAll();
	}
}

