package com.messengerchat.model;

import com.messengerchat.services.DateNow;

public class ChatMessage {
	private String date;
	private String usernameFrom;
	private String usernameTo;
	private String text;

	public ChatMessage() {
		this(DateNow.getDateNowFull(), "noID", "noID", "noText");
	}
	
	public ChatMessage(String date, String usernameFrom, String usernameTo, String text) {
		setDate(date);
		setText(text);
		setUsernameFrom(usernameFrom);
		setUsernameTo(usernameTo);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsernameFrom() {
		return usernameFrom;
	}

	public void setUsernameFrom(String usernameFrom) {
		this.usernameFrom = usernameFrom;
	}

	public String getUsernameTo() {
		return usernameTo;
	}

	public void setUsernameTo(String usernameTo) {
		this.usernameTo = usernameTo;
	}

	@Override
	public String toString() {
		String message = "[" + date + "] " + usernameFrom + " : " + text;
		return message;
	}

}
