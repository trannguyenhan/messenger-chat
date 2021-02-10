package com.messengerchat.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.messengerchat.model.ChatMessage;
import com.messengerchat.services.ChatMessageService;
import com.messengerchat.services.DateNow;


@ServerEndpoint("/chatServerEndpoint/{username}")
public class ChatServerEndpoint {
	static Set<Session> listUser = (Set<Session>) Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void handleOpen(Session userSession, @PathParam("username") String username) {
		listUser.add(userSession);
		userSession.getUserProperties().put("username", username);
		System.out.println("connect with client " + userSession.getId());
	}
	
	@OnMessage
	public void handleMessage(String message, Session userSession) 
			throws ClassNotFoundException, SQLException, IOException {
		JsonElement element = JsonParser.parseString(message);
		JsonObject obj = element.getAsJsonObject();
		
		// get info of messenger : sender, receiver, message, date
		String usernameFrom = obj.get("usernameFrom").toString();
		usernameFrom = usernameFrom.substring(1, usernameFrom.length() - 1);
		
		String usernameTo = obj.get("usernameTo").toString();
		usernameTo = usernameTo.substring(1, usernameTo.length() - 1);
		
		String contentChat = obj.get("message").toString();
		contentChat = contentChat.substring(1, contentChat.length() - 1);
		
		// create object message and save to database
		ChatMessage chatMessage = new ChatMessage(DateNow.getDateNowFull(), usernameFrom, usernameTo, contentChat);
		new ChatMessageService().addMessage(chatMessage);
		
		// send message to receiver 
		Iterator<Session> iterator = listUser.iterator();
		while(iterator.hasNext()) {
			Session tmpUser = iterator.next();
			if(tmpUser.getUserProperties().get("username").equals(usernameTo) || 
					tmpUser.getUserProperties().get("username").equals(usernameFrom)) {
				tmpUser.getBasicRemote().sendText(chatMessage.toString());
			}
		}
	}
	
	@OnClose
	public void handleClose(Session userSession) {
		listUser.remove(userSession);
		System.out.println("disconnect... " + userSession.getId());
	}
	
}
