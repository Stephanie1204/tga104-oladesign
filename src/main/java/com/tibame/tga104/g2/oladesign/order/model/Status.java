package com.tibame.tga104.g2.oladesign.order.model;

public class Status {

	protected enum orderStatus {
	    CHECKING(1),
	    CREATED(2),
	    CANCELED(3);
	    
	    private int code; 

	    orderStatus(int code) { 
			this.code = code;
		}

		public int getCode() { 
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
	}

	protected enum shippingStatus {
		CHECKING(1),
	    PREPARING(2),
	    CANCELED(3),
	    SENT(4),
	    SHIPPING(5),
	    RECEIVED(6);
	    
	    private int code; 

	    shippingStatus(int code) { 
			this.code = code;
		}

		public int getCode() { 
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
	}
}
