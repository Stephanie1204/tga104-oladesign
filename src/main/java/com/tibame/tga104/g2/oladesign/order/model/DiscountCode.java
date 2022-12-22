package com.tibame.tga104.g2.oladesign.order.model;

public class DiscountCode {

	public enum DiscountStatus {
	    PRICEDOWN("P001"),//單品降價
	    PERCENTOFF("P002");//單品打折
	    
	    private String code; 

	    DiscountStatus(String code) { 
			this.code = code;
		}

		public String getCode() { 
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
}
