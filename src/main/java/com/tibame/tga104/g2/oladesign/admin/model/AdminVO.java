package com.tibame.tga104.g2.oladesign.admin.model;

import java.sql.Date;

public class AdminVO implements java.io.Serializable {

	private static final long serialVersionUID = -8971401992354576671L;
	private String adminId  ;
	private String adminName ;
	private String account;
	private String password;
	private Date creatDate;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	

}