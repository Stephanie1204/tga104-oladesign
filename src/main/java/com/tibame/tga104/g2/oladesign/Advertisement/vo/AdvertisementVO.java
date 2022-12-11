package com.tibame.tga104.g2.oladesign.Advertisement.vo;

import java.util.Base64;

public class AdvertisementVO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String adId;
    private String comTaxId;
    private String startDate;
    private String endDate;
    private byte[] adImages;
    private String storeLink;
    private Boolean adStatus;

    public String getAdId() {
	return adId;
    }

    public void setAdId(String adId) {
	this.adId = adId;
    }

    public String getComTaxId() {
	return comTaxId;
    }

    public void setComTaxId(String comTaxId) {
	this.comTaxId = comTaxId;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public byte[] getAdImages() {
	return adImages;
    }

    public void setAdImages(byte[] adImages) {
	this.adImages = adImages;
    }

    public String getStoreLink() {
	return storeLink;
    }

    public void setStoreLink(String storeLink) {
	this.storeLink = storeLink;
    }

    public Boolean getAdStatus() {
	return adStatus;
    }

    public void setAdStatus(Boolean adStatus) {
	this.adStatus = adStatus;
    }

    public String getAdImagesString() {
	// 前端要base64才能顯示照片,如果adImages型態的照片是null或照片的長度是0則給前端空字串""
	// 如果不是則直接轉base64
	if (this.adImages == null || this.adImages.length == 0) {
	    return "";
	}
	return "data:image/jpg;base64, " + new String(Base64.getEncoder().encode(this.adImages));
    }

}
