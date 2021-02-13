package com.messengerchat.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.messengerchat.model.ChatMessage;
import com.messengerchat.model.User;

public class ChatMessageService {
	/* add new message to database */
	public boolean addMessage(ChatMessage chat) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();

		String query = "INSERT INTO chatmessage(c_date, usernameFrom, usernameTo, c_text)" + " value(?, ?, ?, ?)";

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
	
	/* get content chat of user1 and user2*/
	public String getContentChat(User user1, User user2) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();

		String query = "SELECT * FROM chatmessage where (usernameFrom = ? and usernameTo = ?) or "
				+ "(usernameFrom = ? and usernameTo = ?) order by id_mes";
		PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, user1.getUsername());
		preparedStatement.setString(2, user2.getUsername());
		preparedStatement.setString(3, user2.getUsername());
		preparedStatement.setString(4, user1.getUsername());
		ResultSet result = preparedStatement.executeQuery();
		
		String chat_content = "";
		while(result.next()) {
			String c_date = result.getString("c_date");
			String usernameFrom = result.getString("usernameFrom");
			String usernameTo = result.getString("usernameTo");
			String c_text = result.getString("c_text");
			
			ChatMessage chat = new ChatMessage(c_date, usernameFrom, usernameTo, c_text);
			chat_content = chat_content + chat.toString() + "\n";
		}
		
		return chat_content;
	}
}
