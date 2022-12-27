package com.tibame.tga104.g2.oladesign.product.model.product;

import java.io.InputStream;
import java.util.Base64;

public class ProductImageBean {
	
	private String imageId;

	private int productId;

	private byte[] productImgByteArray;

	private String productImgBase64;

	private InputStream productImg;
//
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public InputStream getProductImg() {
		return productImg;
	}

	public void setProductImg(InputStream inputStream) {
		this.productImg = inputStream;
	}

	public String getProductImgBase64() {
		return productImgBase64;
	}

	public void setProductImgBase64(byte[] productImg) {
		String base64str = Base64.getEncoder().encodeToString(productImg);
		this.productImgBase64 = "data:image/png;base64," + base64str;
	}

	public byte[] getProductImgByteArray() {
		return productImgByteArray;
	}

	public void setProductImgByteArray(byte[] productImgByteArray) {
		this.productImgByteArray = productImgByteArray;
	}
}
