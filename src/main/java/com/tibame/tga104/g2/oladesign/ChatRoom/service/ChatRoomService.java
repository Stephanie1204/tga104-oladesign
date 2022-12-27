package com.tibame.tga104.g2.oladesign.ChatRoom.service;

import java.util.List;

import com.tibame.tga104.g2.oladesign.ChatRoom.dao.Chat_RoomDAO_interface;
import com.tibame.tga104.g2.oladesign.ChatRoom.dao.Chat_RoomJDBCDAO;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Chat_RoomVO;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Hist_Chat_RoomVO;
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

	// 多筆查詢:該會員的全部歷史聊天室
	public List<Chat_RoomVO> chatRoomLogs(Integer memId0, Integer memId1) {
		return dao.chatRoomLogs(memId0, memId1);
	}

	// 查詢歷史聊天室
	public List<Hist_Chat_RoomVO> doGetAllChatroom(Integer mem_0) {
		return dao.doGetAllChatroom(mem_0);
	}
}
