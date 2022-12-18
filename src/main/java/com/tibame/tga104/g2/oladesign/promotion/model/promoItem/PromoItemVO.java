package com.tibame.tga104.g2.oladesign.promotion.model.promoItem;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class PromoItemVO implements Serializable{
	private String comTaxId;
	private Integer promoId;
	private Integer prodId;
	private String code;
	private String codeName;
	private Integer discount;
	private String prodName;
	private Integer price;
	private Integer stock;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private Date promoStartDate;
	private Date promoEndDate;
	private String promoName;
	private String coupon;
	
	
	@Override
	public String toString() {
		return "PromoItemVO [promoId=" + promoId + ", prodId=" + prodId + ", code=" + code + ", codeName=" + codeName
				+ ", discount=" + discount + ", prodName=" + prodName + ", price=" + price + ", stock=" + stock
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", promoStartDate=" + promoStartDate
				+ ", promoEndDate=" + promoEndDate + ", promoName=" + promoName + ", coupon=" + coupon + "]";
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getPromoName() {
		return promoName;
	}
	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}
	public Integer getPromoId() {
		return promoId;
	}
	public void setPromoId(Integer promoId) {
		this.promoId = promoId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getPromoStartDate() {
		return promoStartDate;
	}
	public void setPromoStartDate(Date promoStartDate) {
		this.promoStartDate = promoStartDate;
	}
	public Date getPromoEndDate() {
		return promoEndDate;
	}
	public void setPromoEndDate(Date promoEndDate) {
		this.promoEndDate = promoEndDate;
	}
	public String getComTaxId() {
		return comTaxId;
	}
	public void setComTaxId(String comTaxId) {
		this.comTaxId = comTaxId;
	}
	
	
			


}
