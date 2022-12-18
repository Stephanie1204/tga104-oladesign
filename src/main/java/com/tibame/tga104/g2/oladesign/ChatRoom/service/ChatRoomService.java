package com.tibame.tga104.g2.oladesign.ChatRoom.service;

import com.tibame.tga104.g2.oladesign.ChatRoom.dao.Chat_RoomDAO_interface;
import com.tibame.tga104.g2.oladesign.ChatRoom.dao.Chat_RoomJDBCDAO;
import com.tibame.tga104.g2.oladesign.CompanyCommon.SeqDAO_interface;
import com.tibame.tga104.g2.oladesign.CompanyCommon.SeqJDBCDAO;

public class ChatRoomService {
	private Chat_RoomDAO_interface dao;
	private SeqDAO_interface seqDao;

	public ChatRoomService() {
		dao = new Chat_RoomJDBCDAO();
		seqDao = new SeqJDBCDAO();
	}

	// 建立買家與賣家的聊天室
	public String addchatroom(Integer memId0, Integer memId1) {
		String chatRoomId = seqDao.getOneSeq("CH");
		dao.insert(chatRoomId, memId0, memId1);

		return chatRoomId;
	}

	// 單一查詢:確認雙方會員是否已有聊天室
	public String chatRoomfindByMemId(Integer memId0, Integer memId1) {
		return dao.chatRoomfindByMemId(memId0, memId1);
	}
}
