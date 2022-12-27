package com.tibame.tga104.g2.oladesign.ChatRoom.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.tibame.tga104.g2.oladesign.ChatRoom.vo.ChatMessage;

@ServerEndpoint("/controller/FriendWS/{userName}")
public class FriendWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	// 記得對方的session 如何找到對方
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		// 存取對方的ID和Session
		/* save the new user in the map */
		System.out.println(userName);
		sessionsMap.put(userName, userSession);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {

		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String receiver = chatMessage.getReceiver();
		// 對方的連線資訊
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
		}
		userSession.getAsyncRemote().sendText(message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
	}
}
