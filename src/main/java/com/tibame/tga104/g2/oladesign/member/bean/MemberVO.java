package com.tibame.tga104.g2.oladesign.member.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Base64;

public class MemberVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer memId;
	private String memName;
	private String account;
	private String password;
	private String memPhone;
	private String memAddress;
	private Date memRegdate;
	private String sex;
	private Integer point;
	private Boolean isBan;
	private Boolean isCom;
	private Boolean isActive;
	private Boolean isRegCom;
	private byte[] memPhoto;
	private String memPhotoBase64;
	
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", account=" + account + ", password=" + password
				+ ", memPhone=" + memPhone + ", memAddress=" + memAddress + ", memRegdate=" + memRegdate + ", sex="
				+ sex + ", point=" + point + ", isBan=" + isBan + ", isCom=" + isCom + ", isActive=" + isActive
				+ ", isRegCom=" + isRegCom + ", memPhoto=" + Arrays.toString(memPhoto) + "]";
	}
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public Date getMemRegdate() {
		return memRegdate;
	}
	public void setMemRegdate(Date memRegdate) {
		this.memRegdate = memRegdate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Boolean isBan() {
		return isBan;
	}
	
	public void setBan(boolean isBan) {
		this.isBan = isBan;
	}
	public Boolean isCom() {
		return isCom;
	}
	public void setCom(boolean isCom) {
		this.isCom = isCom;
	}
	
	public Boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public Boolean getIsRegCom() {
		return isRegCom;
	}
	public void setIsRegCom(Boolean isRegCom) {
		this.isRegCom = isRegCom;
	}

	public byte[] getMemPhoto() {
		return memPhoto;
	}
	public void setMemPhoto(byte[] memPhoto) {
		this.memPhoto = memPhoto;
	}
	
	
	//將byte[] memPhoto轉為Base64格式
	public String getMemPhotoBase64() {
		return memPhotoBase64;
	}
	public void setMemPhotoBase64(byte[] memPhoto) {
		if(memPhoto == null || memPhoto.length == 0) {
			this.memPhotoBase64 = "";
		}else {
			String base64Str = Base64.getEncoder().encodeToString(memPhoto);
			this.memPhotoBase64 = "data:image/png;base64," + base64Str;
		}

	}
	
	
}
