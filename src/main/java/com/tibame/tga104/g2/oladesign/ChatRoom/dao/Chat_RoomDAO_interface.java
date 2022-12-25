package com.tibame.tga104.g2.oladesign.ChatRoom.dao;

import java.util.List;

import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Chat_RoomVO;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Hist_Chat_RoomVO;

public interface Chat_RoomDAO_interface {

	public void insert(String chatRoomId, Integer memId0, Integer memId1);

	public String chatRoomfindByMemId(Integer memId0, Integer memId1);

	public List<Chat_RoomVO> chatRoomLogs(Integer memId0, Integer memId1);

	public List<Hist_Chat_RoomVO> doGetAllChatroom(Integer mem_0);
}