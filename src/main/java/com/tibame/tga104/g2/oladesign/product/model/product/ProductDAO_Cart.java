package com.tibame.tga104.g2.oladesign.product.model.product;

import java.util.List;

public interface ProductDAO_Cart {

	public abstract List<ProductBean> selectCart(String userId, String comTaxId);
	
	public abstract List<String> selectSaler(String userId);
	
	public abstract void insert(String userId, String comTaxId, int productId);
	
	public abstract void deleteFromCart(String userId, String comTaxId, int productId);
	
	public abstract void deleteFromCart(String userI);
	
}
