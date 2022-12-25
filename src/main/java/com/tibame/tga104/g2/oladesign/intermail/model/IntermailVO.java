package com.tibame.tga104.g2.oladesign.intermail.model;

import java.sql.Timestamp;

public class IntermailVO implements java.io.Serializable{
	private Integer interMailId;
	private Integer memId;
	private String adminId;
	private String numQue;
	private String conTent;
	private Timestamp sentTime;
	private Boolean isSend;
	private Boolean isReply;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	
	public Integer getInterMailId() {
		return interMailId;
	}
	public void setInterMailId(Integer interMailId) {
		this.interMailId = interMailId;
	}

	public Boolean getIsReply() {
		return isReply;
	}
	public void setIsReply(Boolean isReply) {
		this.isReply = isReply;
	}

	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getNumQue() {
		return numQue;
	}
	public void setNumQue(String numQue) {
		this.numQue = numQue;
	}
	public String getConTent() {
		return conTent;
	}
	public void setConTent(String conTent) {
		this.conTent = conTent;
	}
	public Timestamp getSentTime() {
		return sentTime;
	}
	public void setSentTime(Timestamp sentTime) {
		this.sentTime = sentTime;
	}
	public Boolean getIsSend() {
		return isSend;
	}
	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}
		
	}
	
