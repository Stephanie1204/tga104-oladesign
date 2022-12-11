package com.tibame.tga104.g2.oladesign.ChatRoom.vo;

import java.sql.Date;

public class Chat_MessageVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String messageId;
	private String chatRoomId;
	private Boolean isSend;
	private String content;
	private Date sendTime;
	private Boolean isRead;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

	public Boolean getIsSend() {
		return isSend;
	}

	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

}
