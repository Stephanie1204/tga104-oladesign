package com.tibame.tga104.g2.oladesign.product.model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tibame.tga104.g2.oladesign.order.model.DiscountCode.DiscountStatus;
import com.tibame.tga104.g2.oladesign.order.model.DiscountItem;
import com.tibame.tga104.g2.oladesign.order.model.OrderDAO;
import com.tibame.tga104.g2.oladesign.order.model.OrderDAOJdbc;

import redis.clients.jedis.Jedis;

public class ProductDAORedis implements ProductDAO_Cart {

	private ProductDAO productDao = new ProductDAOJdbc();
	private OrderDAO orderDao = new OrderDAOJdbc();

	@Override
	public List<ProductBean> selectCart(String userId, String comTaxId) {

		Jedis jedis = new Jedis("localhost", 6379);

		List<ProductBean> result = null;

		result = new ArrayList<ProductBean>();

		for (String prodId : jedis.zrange(userId + ":" + comTaxId, 0, -1)) {
			System.out.println(prodId);
			ProductBean bean = new ProductBean();
			bean = productDao.selectByProdId(Integer.parseInt(prodId));
			bean.setCartQuantity(jedis.zscore(userId + ":" + comTaxId, prodId).intValue());
			result.add(bean);
		}
		jedis.close();
		return result;
	}

	@Override
	public List<String> selectSaler(String userId) {

		Jedis jedis = new Jedis("localhost", 6379);

		List<String> result = null;

		result = new ArrayList<String>();

		for (String comTaxId : jedis.smembers(userId + "_buyFrom")) {
//			System.out.println(comTaxId);
			result.add(comTaxId);
		}

		jedis.close();
		return result;
	}

	@Override
	public boolean salerExist(String userId, String comTaxId) {

		Jedis jedis = new Jedis("localhost", 6379);

		if (jedis.sismember(userId + "_buyFrom", comTaxId)) {
			jedis.close();
			return true;
		}

		jedis.close();
		return false;
	}

	@Override
	public void updateFromCart(String userId, String comTaxId, int productId, int quantity) {
		Jedis jedis = new Jedis("localhost", 6379);

		Map<String, Double> userCart = new HashMap<>();
		userCart.put(Integer.toString(productId), new Double(quantity));

		jedis.zadd(userId + ":" + comTaxId, userCart);
		jedis.sadd(userId + "_buyFrom", comTaxId);

		jedis.close();
	}

	@Override
	public void insert(String userId, String comTaxId, int productId, int quantity) {
		Jedis jedis = new Jedis("localhost", 6379);

		Map<String, Double> userCart = new HashMap<>();
		userCart.put(Integer.toString(productId), new Double(quantity));

		jedis.zadd(userId + ":" + comTaxId, userCart);
		jedis.sadd(userId + "_buyFrom", comTaxId);

		jedis.close();
	}

//刪除購物車單一品項
	@Override
	public void deleteFromCart(String userId, String comTaxId, int productId) {
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println("delete");
		jedis.zrem(userId + ":" + comTaxId, Integer.toString(productId));
		// 購物車內屬於某廠商的商品種類從購物車內移除光時,將user+buyFrom這個key內的此member也移除
		if (jedis.zcard(userId + ":" + comTaxId) <= 0) {
			jedis.srem(userId + "_buyFrom", comTaxId);
		}
		jedis.close();
	}

//刪除購物車內屬於某廠商的所有資料
	@Override
	public void deleteFromCartByComTaxId(String userId, String comTaxId) {
		Jedis jedis = new Jedis("localhost", 6379);

		jedis.del(userId + ":" + comTaxId);
		jedis.srem(userId + "_buyFrom", comTaxId);
		if (jedis.scard(userId + "_buyFrom") <= 0) {
			jedis.del(userId + "_buyFrom");
		}
		jedis.close();
	}

	// 購物車中所有屬於某個廠商統編的商品價格總和
	@Override
	public int getTotal(String userId, String comTaxId) {

		Jedis jedis = new Jedis("localhost", 6379);

		int totalPrice = 0;

		for (String prodId : jedis.zrange(userId + ":" + comTaxId, 0, -1)) {
			System.out.println(prodId);

			int quantity = jedis.zscore(userId + ":" + comTaxId, prodId).intValue();
			totalPrice += productDao.getPrice(Integer.parseInt(prodId)) * quantity;
		}
		jedis.close();

		return totalPrice;
	}

	@Override
	public int getTotal(String userId, String comTaxId, String coupon) {

		Jedis jedis = new Jedis("localhost", 6379);

		int totalPrice = 0;

		for (String prodId : jedis.zrange(userId + ":" + comTaxId, 0, -1)) {
			int quantity = jedis.zscore(userId + ":" + comTaxId, prodId).intValue();
			boolean isDiscountItem = false;

			for (DiscountItem item : orderDao.getDiscountItem(coupon)) {
				System.out.println(prodId + ":" + Integer.toString(item.getProductId()) + ":" + item.getDiscountCode());
				if (prodId.equals(Integer.toString(item.getProductId()))
						&& item.getDiscountCode().equals(DiscountStatus.PRICEDOWN.getCode())) {
					totalPrice += (productDao.getPrice(Integer.parseInt(prodId)) - item.getDiscount()) * quantity ;
					System.out.println(totalPrice);
					isDiscountItem = true;
					break;
				} else if (prodId.equals(Integer.toString(item.getProductId()))
						&& item.getDiscountCode().equals(DiscountStatus.PERCENTOFF.getCode())) {
					totalPrice += (int) (productDao.getPrice(Integer.parseInt(prodId)) * quantity * item.getDiscount()
							/ 100);
					System.out.println(totalPrice);
					isDiscountItem = true;
					break;
				} 
			}
			if(isDiscountItem == false) {
				totalPrice = productDao.getPrice(Integer.parseInt(prodId)) * quantity;
				System.out.println(totalPrice);
			}
		}
		jedis.close();

		return totalPrice;
	}
}
