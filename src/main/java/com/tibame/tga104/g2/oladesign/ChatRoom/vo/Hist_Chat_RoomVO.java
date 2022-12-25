package com.tibame.tga104.g2.oladesign.ChatRoom.vo;

public class Hist_Chat_RoomVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// 會員編號
	private Integer memId;

	// 廠商Logo
	private String storeLogo;

	// 廠商名稱
	private String storeName;

	// 訊息
	private String message;

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}