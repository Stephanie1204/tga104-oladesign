package com.tibame.tga104.g2.oladesign.order.model;

import java.sql.Timestamp;

public class OrderBean {

	private String orderId; // primary key
	private String comTaxId; // foreign key
	private String memId; // foreign key
	private Timestamp orderTime;
	private String address;
	private int amount;
	private int orderStatus;
	private int shippingStatus;
	private String coupon;
	private int pointUse;
	private int pointGet;
	private String receiver;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getComTaxId() {
		return comTaxId;
	}

	public void setComTaxId(String comTaxId) {
		this.comTaxId = comTaxId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(int shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public int getPointUse() {
		return pointUse;
	}

	public void setPointUse(int pointUse) {
		this.pointUse = pointUse;
	}

	public int getPointGet() {
		return pointGet;
	}

	public void setPointGet(int pointGet) {
		this.pointGet = pointGet;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}
