package com.tibame.tga104.g2.oladesign.product.model.product;

import java.util.List;

import com.tibame.tga104.g2.oladesign.order.model.OrderItemBean;
import com.tibame.tga104.g2.oladesign.order.model.OrderItemDAO;
import com.tibame.tga104.g2.oladesign.order.model.OrderItemDAOJdbc;

public class ProductService {
	// DAO object
	private ProductDAO productDao = new ProductDAOJdbc();
	private ProductDAO_Cart productDao_Cart = new ProductDAORedis();
	private OrderItemDAO orderItemDao = new OrderItemDAOJdbc();

	public static void main(String[] args) {

	}

	public List<ProductBean> selectAll(){
		List<ProductBean> temp = productDao.select();
		return temp;
	}
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;

		if (bean != null && bean.getName() != null && bean.getName().length() != 0 && bean.getPrice() == 0
				&& bean.getTypeCode().length() == 0 && bean.getStyleCode().length() == 0) {
//			System.out.println("pass select by name");
			List<ProductBean> temp = productDao.select(bean.getName());
			if (temp != null) {
				result = temp;
			}
		} else if (bean != null
				&& (bean.getTypeCode().length() != 0 || bean.getStyleCode().length() != 0 || bean.getPrice() != 0)) {
			System.out.println("pass select by condition");
			List<ProductBean> temp = productDao.select(bean.getName(), bean.getTypeCode(), bean.getStyleCode(),
					bean.getPrice());
			if (temp != null) {
				result = temp;
			}
		} else {
			System.out.println("pass select all");
			result = productDao.select();
		}
		return result;
	}

	public List<ProductBean> selectByComTaxId(String comTaxId) {
		List<ProductBean> result = null;

		List<ProductBean> temp = productDao.selectByComTaxId(comTaxId);

		if (temp != null) {
			result = temp;
		}
		return result;
	}

	public ProductBean selectByProdId(int proId) {
		ProductBean result = null;

		ProductBean temp = productDao.selectByProdId(proId);

		if (temp != null) {
			result = temp;
		}
		return result;
	}

	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		// insert測試用廠商
//================================================================================================需要變更處
		//
		if (bean != null && bean.getComTaxId() != null) {
			result = productDao.insert(bean);
		}
		return result;
	}

	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = productDao.update(bean);
		}
		return result;
	}

	public boolean delete(ProductBean bean) {
		boolean result = false;
		if (bean != null) {
			result = productDao.delete(bean.getProductId());
		}
		return result;
	}

	public List<ProductBean> getAll() {
		List<ProductBean> result = null;
		result = productDao.select();
		return result;
	}

	// Cart
	public List<ProductBean> selectCart(String userId, String comTaxId) {
		List<ProductBean> result = null;

		List<ProductBean> temp = productDao_Cart.selectCart(userId, comTaxId);

		if (temp != null) {
			result = temp;
		}
		return result;
	}

	public List<String> selectSaler(String userId) {
		List<String> result = null;

		List<String> temp = productDao_Cart.selectSaler(userId);

		if (temp != null) {
			result = temp;
		}
		return result;
	}

//================================================================================================	
	public void updateFromCart(String userId, String comTaxId, int productId, int quantity) {

		productDao_Cart.updateFromCart(userId, comTaxId, productId, quantity);
	}

	public void insertCart(String userId, String comTaxId, int productId, int quantity) {

		if (userId != null && userId.length() != 0) {
			productDao_Cart.insert(userId, comTaxId, productId, quantity);
		}
	}

	public void deleteFromCart(String userId, String comTaxId, int productId) {

		productDao_Cart.deleteFromCart(userId, comTaxId, productId);
	}

//================================================================================================	
	public List<OrderItemBean> getComment(int productId) {

		return orderItemDao.select(productId);
	}

//================================================================================================
	public List<ProductImageBean> getImages(int productId) {
		return productDao.selectImg(productId);
	}

	public void insertImg(ProductImageBean bean) {
		productDao.insertImg(bean);
	}

	public void deleteImg(int imageId) {
		productDao.deleteImg(imageId);
	}

	public int getImgAmount(int productId) {
		return productDao.getImgAmount(productId);
	}
}
