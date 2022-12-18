package com.tibame.tga104.tga104g2.ChatRoom.service;

import java.util.List;

import com.tibame.tga104.tga104g2.ChatRoom.dao.Chat_MessageDAO_interface;
import com.tibame.tga104.tga104g2.ChatRoom.dao.Chat_MessageJDBCDAO;
import com.tibame.tga104.tga104g2.ChatRoom.model.ChatMessage;
import com.tibame.tga104.tga104g2.CompanyCommon.SeqDAO_interface;
import com.tibame.tga104.tga104g2.CompanyCommon.SeqJDBCDAO;

public class ChatMessageService {

	private Chat_MessageDAO_interface dao;
	private SeqDAO_interface seqDao; 
	
	public ChatMessageService() {
		dao = new Chat_MessageJDBCDAO();
		seqDao = new SeqJDBCDAO();		
	}
	
	// 建立聊天訊息
	public String addchatmessage(ChatMessage chatmessage) {
		String messageId = seqDao.getOneSeq("MS");
		chatmessage.setMessageid(messageId);

		dao.insert(chatmessage);

		return messageId;
	}
	
	// 確認雙方是否已有聊天紀錄
    public List<ChatMessage> chatMessageLog(String chatRroomId) {
	return dao.chatMessageLog(chatRroomId);
    }
	
	
}
