package com.tibame.tga104.g2.oladesign.product.model.product;

import java.util.List;

public interface ProductDAO {

	public abstract List<ProductBean> select(String name);

	public abstract List<ProductBean> select(String name, String typeCode, String styleCode, int price);
	
	public abstract List<ProductBean> select();
	
	public abstract List<ProductBean> selectByComTaxId(String comTaxId);
	
	public abstract ProductBean selectByProdId(int proId);

	public abstract ProductBean insert(ProductBean bean);

	public abstract ProductBean update(ProductBean bean);

	public abstract boolean delete(int productId);
	
	public abstract int getPrice(int productId);

	public abstract List<ProductImageBean> selectImg(int productId);

	public abstract void insertImg(ProductImageBean bean);
	
	public abstract void deleteImg(int imageId);
	
	public abstract int getImgAmount(int productId);
}