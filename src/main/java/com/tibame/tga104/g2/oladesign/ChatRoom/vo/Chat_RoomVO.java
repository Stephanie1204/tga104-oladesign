package com.tibame.tga104.g2.oladesign.ChatRoom.vo;

public class Chat_RoomVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String chatRoomId;
	private Integer mem0; //買家
	private Integer mem1; //賣家
	
	
	public String getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public Integer getMem0() {
		return mem0;
	}
	public void setMem0(Integer mem0) {
		this.mem0 = mem0;
	}
	public Integer getMem1() {
		return mem1;
	}
	public void setMem1(Integer mem1) {
		this.mem1 = mem1;
	}
	
}
