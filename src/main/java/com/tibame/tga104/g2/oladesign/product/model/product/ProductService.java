package com.tibame.tga104.g2.oladesign.product.model.product;

import java.util.List;

public class ProductService {
	//create productDAO object
	private ProductDAO productDao = new ProductDAOJdbc();
	private ProductDAO_Cart productDao_Cart = new ProductDAORedis();
	
	public static void main(String[] args) {
		ProductService productService = new ProductService();
		List<ProductBean> selects = productService.select(null);
		System.out.println("selects="+selects);
	}
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		
		if(bean!=null && bean.getName()!=null && bean.getName().length() != 0 && bean.getPrice() == 0 && bean.getTypeCode().length() == 0 && bean.getStyleCode().length() == 0) {
			System.out.println("pass select by name");
			List<ProductBean> temp = productDao.select(bean.getName());
			if(temp!=null) {
				result = temp;
			}
		}else if(bean!=null && (bean.getTypeCode().length() != 0 || bean.getStyleCode().length() != 0 || bean.getPrice() != 0)) {
			System.out.println("pass select by condition");
			List<ProductBean> temp = productDao.select(bean.getName(), bean.getTypeCode(), bean.getStyleCode(), bean.getPrice());
			if(temp!=null) {
				result = temp;
			}
		} 
		else {
			System.out.println("pass select all");
			result = productDao.select(); 
		}
		return result;
	}
	
	public List<ProductBean> selectByComTaxId(String comTaxId){
		List<ProductBean> result = null;
		
		List<ProductBean> temp = productDao.selectByComTaxId(comTaxId);
		
		if(temp!=null) {
			result = temp;
		}
		return result;
	}
	
	public ProductBean selectByProdId(int proId){
		ProductBean result = null;
		
		ProductBean temp = productDao.selectByProdId(proId);
		
		if(temp!=null) {
			result = temp;
		}
		return result;
	}
	
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		//insert測試用廠商
		bean.setComTaxId("23045921");
		//
		if(bean!=null && bean.getComTaxId()!=null) {
			result = productDao.insert(bean);
		}
		return result;
	}
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null) {
			result = productDao.update(bean);
		}
		return result;
	}
	public boolean delete(ProductBean bean) {
		boolean result = false;
		if(bean!=null) {
			result = productDao.delete(bean.getProductId());
		}
		return result;
	}
	public List<ProductBean> getAll(){
		List<ProductBean> result = null;
		result = productDao.select(); 
		return result;
	}
	//Cart
	public List<ProductBean> selectCart(String userId, String comTaxId){
		List<ProductBean> result = null;
		
		List<ProductBean> temp = productDao_Cart.selectCart(userId, comTaxId);
		
		if(temp!=null) {
			result = temp;
		}
		return result;
	}
	
	public List<String> selectSaler(String userId){
		List<String> result = null;
		
		List<String> temp = productDao_Cart.selectSaler(userId);
		
		if(temp!=null) {
			result = temp;
		}
		return result;
	}
	
	public void insertCart(String comTaxId, int productId){
		String userId = "220001";		
 
		productDao_Cart.insert(userId, comTaxId, productId); 
	}
	
	public void deleteFromCart(String comTaxId, int productId){
		String userId = "220001";	
		productDao_Cart.deleteFromCart(userId, comTaxId, productId); 
	}
}
