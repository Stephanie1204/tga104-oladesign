package com.tibame.tga104.g2.oladesign.favorite.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

public class FavorVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer memId;
	private Integer prodId; // primary key
	private String comTaxId; // foreign key
	private String typeCode; // foreign key
	private String styleCode; // foreign key
	private String name;
	private Integer price;
	private Integer imgNum;
	private byte[] img;
	private String imgBase64;
	
	
	@Override
	public String toString() {
		return "FavorVO [memId=" + memId + ", prodId=" + prodId + ", comTaxId=" + comTaxId + ", typeCode="
				+ typeCode + ", styleCode=" + styleCode + ", name=" + name + ", price=" + price + ", imgNum=" + imgNum
				+ ", img=" + Arrays.toString(img) + "]";
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getProductId() {
		return prodId;
	}
	public void setProductId(Integer prodId) {
		this.prodId = prodId;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getImgNum() {
		return imgNum;
	}
	public void setImgNum(Integer imgNum) {
		this.imgNum = imgNum;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
	//將byte[] img轉為Base64格式
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(byte[] img) {
		if(img == null || img.length == 0) {
			this.imgBase64 = "";
		}else {
			String base64Str = Base64.getEncoder().encodeToString(img);
			this.imgBase64 = "data:image/png;base64," + base64Str;
		}
	}
}
