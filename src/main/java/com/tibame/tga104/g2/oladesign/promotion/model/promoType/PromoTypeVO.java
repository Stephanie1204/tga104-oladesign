package com.tibame.tga104.g2.oladesign.promotion.model.promoType;

import java.io.Serializable;

public class PromoTypeVO implements Serializable{
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PromoTypeVO(String code, String name) {
		this.code = code;
		this.name = name;
	} 
	
	public PromoTypeVO() {
		
	} 
	

	
}
