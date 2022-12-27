package com.tibame.tga104.g2.oladesign.ChatRoom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tibame.tga104.g2.oladesign.ChatRoom.service.ChatMessageService;
import com.tibame.tga104.g2.oladesign.ChatRoom.service.ChatRoomService;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.ChatMessage;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Chat_RoomVO;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.Hist_Chat_RoomVO;

@WebServlet("/chatroom/chatstart.do")
public class ChatRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		// 賣家主業點我聊聊跳出對話視窗
		if ("doOpenChatRoom".equals(action)) {
			Chat_RoomVO chat_roomVO = new Chat_RoomVO();
			chat_roomVO.setMem0(chat_roomVO.getMem0());
			chat_roomVO.setMem1(chat_roomVO.getMem1());

			// 準備res
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(chat_roomVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			// 寫好RS給AJAX
			pw.flush();
		}

		// 確認有無chatroomid
		if ("doSetChatRoom".equals(action)) {
			Integer mem_0 = Integer.valueOf(req.getParameter("mem_0"));
			Integer mem_1 = Integer.valueOf(req.getParameter("mem_1"));

			ChatRoomService chat_roomSvc = new ChatRoomService();
			Chat_RoomVO chat_roomVO = new Chat_RoomVO();
			String chatRoomId = chat_roomSvc.chatRoomfindByMemId(mem_0, mem_1);

			if ("".equals(chatRoomId)) {
				chat_roomVO.setChatRoomId(chat_roomSvc.addchatroom(mem_0, mem_1)); // 沒有聊天室的話新增聊天室ID設定聊天室
			} else {
				chat_roomVO.setChatRoomId(chatRoomId); // 有聊天室設定聊天室ID
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(chat_roomVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}

		// 儲存對話內容
		if ("doSetChatMessage".equals(action)) {
			String chatRoomId = req.getParameter("chatRoomId");
			String message = req.getParameter("message");
			String sender = req.getParameter("sender");

			ChatMessage chatmessage = new ChatMessage();
			chatmessage.setChatroomid(chatRoomId);
			chatmessage.setMessage(message);
			chatmessage.setSender(sender);

			ChatMessageService chatmessageSvc = new ChatMessageService();
			String msId = "";
			msId = chatmessageSvc.addchatmessage(chatmessage);

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(msId);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}

		// 查詢歷史對話紀錄
		if ("doGetChatLogs".equals(action)) {
			String chatRoomId = req.getParameter("chatRoomId");

			ChatMessageService chatmessageSvc = new ChatMessageService();
			List<ChatMessage> chatmessage = chatmessageSvc.chatMessageLog(chatRoomId);

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(chatmessage);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}

		// 查詢歷史ChatRoom
		if ("doSetChatRoomLogs".equals(action)) {
			Integer mem_0 = Integer.valueOf(req.getParameter("mem_0"));
			Integer mem_1 = Integer.valueOf(req.getParameter("mem_1"));

			ChatRoomService chatroomSvc = new ChatRoomService();
			List<Chat_RoomVO> chatroom = chatroomSvc.chatRoomLogs(mem_0, mem_1);

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(chatroom);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();

		}

		if ("doGetAllChatroom".equals(action)) {
			Integer mem_0 = Integer.valueOf(req.getParameter("mem_0"));
			ChatRoomService chat_roomSvc = new ChatRoomService();
			List<Hist_Chat_RoomVO> hist_Chat_RoomVO = chat_roomSvc.doGetAllChatroom(mem_0);

			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String jsonString = gson.toJson(hist_Chat_RoomVO);

			PrintWriter pw = res.getWriter();
			pw.write(jsonString);

			pw.flush();
		}
	}
}
