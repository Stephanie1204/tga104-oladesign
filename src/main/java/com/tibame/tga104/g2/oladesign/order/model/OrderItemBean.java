package com.tibame.tga104.g2.oladesign.order.model;

public class OrderItemBean {

	private String orderId;

	private int productId;

	private String productName;

	private int quantity;

	private int price;

	private int commentStar;

	private String comment;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCommentStar() {
		return commentStar;
	}

	public void setCommentStar(int commentStar) {
		this.commentStar = commentStar;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
