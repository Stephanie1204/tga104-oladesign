package com.tibame.tga104.tga104g2.ChatRoom.dao;

public interface Chat_RoomDAO_interface {

	public void insert(String chatRoomId, Integer memId0, Integer memId1);

	public String chatRoomfindByMemId(Integer memId0, Integer memId1);
//	public void update(Chat_RoomVO chat_roomVO);
//
//	public void delete(String chat_roomno);
//
//	public Chat_RoomVO findByPrimaryKey(String chat_roomno);
//
//	public List<Chat_RoomVO> getAll();
	// public List<Chat_RoomVO> getAll(Map<String, String[]> map);

}
