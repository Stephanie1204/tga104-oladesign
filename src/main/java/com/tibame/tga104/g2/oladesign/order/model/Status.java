package com.tibame.tga104.g2.oladesign.order.model;

public class Status {

	protected enum orderStatus {
	    CHECKING(0),
	    CREATED(1),
	    CANCELED(2);
	    
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
		CHECKING(0),
	    PREPARING(1),
	    CANCELED(2),
	    SENT(3),
	    SHIPPING(4),
	    RECEIVED(5);
	    
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
