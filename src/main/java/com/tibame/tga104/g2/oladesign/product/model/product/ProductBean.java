package com.tibame.tga104.g2.oladesign.product.model.product;

import java.io.InputStream;
import java.util.Base64;

public class ProductBean {

	private int productId; // primary key
	private String comTaxId; // foreign key
	private String typeCode; // foreign key
	private String styleCode; // foreign key
	private String name;
	private int price;
	private String intro;
	private int amountSold;
	private int stock;
	private int safeStock;
	private int amountFavor;
	private boolean status;
	//
	private String typeName;
	private String styleName;
	//
	private byte[] productImgByteArray;
	//
	private String productImgBase64;
	private InputStream productImg;
	//
	private int cartQuantity;

	//
	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public byte[] getProductImgByteArray() {
		return productImgByteArray;
	}

	public void setProductImgByteArray(byte[] productImgByteArray) {
		this.productImgByteArray = productImgByteArray;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getComTaxId() {
		return comTaxId;
	}

	public void setComTaxId(String comTaxId) {
		this.comTaxId = comTaxId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getAmountSold() {
		return amountSold;
	}

	public void setAmountSold(int amountSold) {
		this.amountSold = amountSold;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSafeStock() {
		return safeStock;
	}

	public void setSafeStock(int safeStock) {
		this.safeStock = safeStock;
	}

	public int getAmountFavor() {
		return amountFavor;
	}

	public void setAmountFavor(int amountFavor) {
		this.amountFavor = amountFavor;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	//

	@Override
	public String toString() {
		return "ProductBean [productId=" + productId + ", comTaxId=" + comTaxId + ", typeCode=" + typeCode
				+ ", styleCode=" + styleCode + ", name=" + name + ", price=" + price + ", intro=" + intro
				+ ", amountSold=" + amountSold + ", stock=" + stock + ", safeStock=" + safeStock + ", amountFavor="
				+ amountFavor + ", status=" + status + ", typeName=" + typeName + ", styleName=" + styleName
				+  ", productImg=" + productImg + ", cartQuantity=" + cartQuantity + "]";
	}

}
