package com.tibame.tga104.g2.oladesign.promotion.model.promo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PromoVO implements Serializable{
	private Integer promoId;
	private String comTaxId;
	private String promoName;
	private Date startDate;
	private Date endDate;
	private String coupon;
	
	public Integer getPromoId() {
		return promoId;
	}
	public void setPromoId(Integer promoId) {
		this.promoId = promoId;
	}
	public String getComTaxId() {
		return comTaxId;
	}
	public void setComTaxId(String comTaxId) {
		this.comTaxId = comTaxId;
	}
	public String getPromoName() {
		return promoName;
	}
	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	@Override
	public String toString() {
		return "Promotion_VO [promoId=" + promoId + ", comTaxId=" + comTaxId + ", promoName=" + promoName
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", coupon=" + coupon + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
