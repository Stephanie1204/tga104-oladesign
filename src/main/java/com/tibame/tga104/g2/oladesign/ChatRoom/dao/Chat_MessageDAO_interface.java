package com.tibame.tga104.g2.oladesign.ChatRoom.dao;

import java.util.List;

import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Chat_MessageVO;



public interface Chat_MessageDAO_interface {
	public void insert(Chat_MessageVO chat_messageVO);
	public void update(Chat_MessageVO chat_messageVO);
	public void delete(String chat_messageno);
	public Chat_MessageVO findByPrimaryKey(String chat_messageno);
	public List<Chat_MessageVO> getAll();
	//public List<Chat_MessageVO> getAll(Map<String, String[]> map); 
}
