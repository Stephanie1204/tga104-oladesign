package com.tibame.tga104.g2.oladesign.CompanyMember.vo;

public class Company_MemByCheckVO extends Company_MemVO {
	private static final long serialVersionUID = 1L;
	private Boolean isMemberHasCom;
	private String StoreLogoString;
	private String StoreBannerString;

	public String getStoreLogoString() {
		return StoreLogoString;
	}

	public void setStoreLogoString(String storeLogoString) {
		StoreLogoString = storeLogoString;
	}

	public String getStoreBannerString() {
		return StoreBannerString;
	}

	public void setStoreBannerString(String storeBannerString) {
		StoreBannerString = storeBannerString;
	}

	public Boolean getIsMemberHasCom() {
		return isMemberHasCom;
	}

	public void setIsMemberHasCom(Boolean isMemberHasCom) {
		this.isMemberHasCom = isMemberHasCom;
	}
}
