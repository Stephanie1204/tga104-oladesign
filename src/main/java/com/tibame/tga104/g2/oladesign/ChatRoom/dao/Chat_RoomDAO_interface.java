package com.tibame.tga104.g2.oladesign.ChatRoom.dao;

import java.util.List;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Chat_RoomVO;

public interface Chat_RoomDAO_interface {

	public void insert(String chatRoomId, Integer memId0, Integer memId1);

	public String chatRoomfindByMemId(Integer memId0, Integer memId1);
	
	public List<Chat_RoomVO> chatRoomLogs(Integer memId0, Integer memId1);
//	public void update(Chat_RoomVO chat_roomVO);
//
//	public void delete(String chat_roomno);
//
//	public Chat_RoomVO findByPrimaryKey(String chat_roomno);
//
//	public List<Chat_RoomVO> getAll();
	// public List<Chat_RoomVO> getAll(Map<String, String[]> map);

}
