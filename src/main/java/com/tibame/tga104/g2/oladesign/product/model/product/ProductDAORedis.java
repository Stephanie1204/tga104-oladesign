package com.tibame.tga104.g2.oladesign.product.model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;


public class ProductDAORedis implements ProductDAO_Cart {

	private ProductDAO productDao = new ProductDAOJdbc();

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

	public void updateFromCart(String userId, String comTaxId, int productId, int quantity) {
		Jedis jedis = new Jedis("localhost", 6379);
		
		Map<String, Double> userCart = new HashMap<>();
		userCart.put(Integer.toString(productId), new Double(quantity));
		
		jedis.zadd(userId + ":" + comTaxId, userCart);
		jedis.sadd(userId + "_buyFrom", comTaxId);

		jedis.close();
	}
	
	public void insert(String userId, String comTaxId, int productId, int quantity) {
		Jedis jedis = new Jedis("localhost", 6379);
		
		Map<String, Double> userCart = new HashMap<>();
		userCart.put(Integer.toString(productId), new Double(quantity));
		
		jedis.zadd(userId + ":" + comTaxId, userCart);
		jedis.sadd(userId + "_buyFrom", comTaxId);

		jedis.close();
	}

	public void deleteFromCart(String userId, String comTaxId, int productId) {
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println("delete");
		jedis.zrem(userId + ":" + comTaxId, Integer.toString(productId));
		if(jedis.zcard(userId + ":" + comTaxId) <= 0) {
			jedis.srem(userId + "_buyFrom", comTaxId);
		}
		
		jedis.close();
	}

	public void deleteFromCart(String userId) {
		Jedis jedis = new Jedis("localhost", 6379);

		jedis.del(userId);

		jedis.close();
	}
}
