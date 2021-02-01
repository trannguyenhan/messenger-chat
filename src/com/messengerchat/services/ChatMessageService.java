package com.messengerchat.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.messengerchat.model.ChatMessage;

public class ChatMessageService {
	/* add new message to database */
	public boolean addMessage(ChatMessage chat) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();

		String query = "INSERT INTO user(c_date, usernameFrom, usernameTo, c_text)" + " value(?, ?, ?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, chat.getDate());
		preparedStatement.setString(2, chat.getUsernameFrom());
		preparedStatement.setString(3, chat.getUsernameTo());
		preparedStatement.setString(4, chat.getText());
		preparedStatement.executeUpdate();

		preparedStatement.close();
		connection.close();
		return false;
	}
	
}
