package com.tibame.tga104.g2.oladesign.product.model.product;

import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.Jedis;


public class ProductDAORedis implements ProductDAO_Cart {

	private ProductDAO productDao = new ProductDAOJdbc();

	public List<ProductBean> selectCart(String userId, String comTaxId) {

		Jedis jedis = new Jedis("localhost", 6379);

		List<ProductBean> result = null;

		result = new ArrayList<ProductBean>();

		for (String prodId : jedis.smembers(userId + ":" + comTaxId)) {
//			System.out.println(prodId);
			result.add(productDao.selectByProdId(Integer.parseInt(prodId)));
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

	public void insert(String userId, String comTaxId, int productId) {
		Jedis jedis = new Jedis("localhost", 6379);

		jedis.sadd(userId + ":" + comTaxId, Integer.toString(productId));
		jedis.sadd(userId + "_buyFrom", comTaxId);

		jedis.close();
	}

	public void deleteFromCart(String userId, String comTaxId, int productId) {
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println("delete");
		jedis.srem(userId + ":" + comTaxId, Integer.toString(productId));
		if(jedis.scard(userId + ":" + comTaxId) <= 0) {
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
