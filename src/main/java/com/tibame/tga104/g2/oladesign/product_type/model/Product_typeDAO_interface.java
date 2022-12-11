package com.tibame.tga104.g2.oladesign.product_type.model;

import java.util.List;



public interface Product_typeDAO_interface {
	    public void insert(Product_typeVO product_typeVO);
	    public void update(Product_typeVO product_typeVO);
	    public void delete(String typeCode);
	    public Product_typeVO findByPrimaryKey(String typeCode);
	    public List<Product_typeVO> getAll();
	}


