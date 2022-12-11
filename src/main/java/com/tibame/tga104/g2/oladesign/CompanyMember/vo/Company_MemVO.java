package com.tibame.tga104.g2.oladesign.CompanyMember.vo;

import java.sql.Date;
import java.util.Base64;

public class Company_MemVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String comTaxId;
	private Integer memId;
	private String comName;
	private String comAddress;
	private String comPhone;
	private String comOwner;
	private String ownerPhone;
	private String comBankaccount;
	private String storeName;
	private Date comRegdate;
	private String storeIntro;
	private byte[] storeLogo;
	private byte[] storeBanner;

	public String getComTaxId() {
		return comTaxId;
	}

	public void setComTaxId(String comTaxid) {
		this.comTaxId = comTaxid;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComAddress() {
		return comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	public String getComPhone() {
		return comPhone;
	}

	public void setComPhone(String comPhone) {
		this.comPhone = comPhone;
	}

	public String getComOwner() {
		return comOwner;
	}

	public void setComOwner(String comOwner) {
		this.comOwner = comOwner;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getComBankaccount() {
		return comBankaccount;
	}

	public void setComBankaccount(String comBankaccount) {
		this.comBankaccount = comBankaccount;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getComRegdate() {
		return comRegdate;
	}

	public void setComRegdate(Date comRegdate) {
		this.comRegdate = comRegdate;
	}

	public String getStoreIntro() {
		return storeIntro;
	}

	public void setStoreIntro(String storeIntro) {
		this.storeIntro = storeIntro;
	}

	public byte[] getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(byte[] storeLogo) {
		this.storeLogo = storeLogo;
	}

	public byte[] getStoreBanner() {
		return storeBanner;
	}

	public void setStoreBanner(byte[] storeBanner) {
		this.storeBanner = storeBanner;
	}

	public String getStoreLogoString() {
		if (this.storeLogo == null || this.storeLogo.length == 0) {
			return "";
		}
		return "data:image/jpg;base64, " + new String(Base64.getEncoder().encode(this.storeLogo));
	}

	public String getStoreBannerString() {
		if (this.storeBanner == null || this.storeBanner.length == 0) {
			return "";
		}
		return "data:image/jpg;base64, " + new String(Base64.getEncoder().encode(this.storeBanner));
	}
}
