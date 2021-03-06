package com.messengerchat.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.messengerchat.model.ChatMessage;
import com.messengerchat.model.User;

public class UserService {
	/* add new user to database */
	public boolean addUser(String name, String username, String password) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();

		String query = "INSERT INTO user(username, password, name)" + " value(?, ?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, name);
		preparedStatement.executeUpdate();

		preparedStatement.close();
		connection.close();
		return false;
	}

	/* check user exits database from username and password */
	public boolean checkUser(String username, String password) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();

		String query = "SELECT * from user where username = ? and password = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		ResultSet result = preparedStatement.executeQuery();

		if (result.next())
			return true;
		return false;
	}

	/* get full information with username */
	public User getFullInfo(String username) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();

		String query = "SELECT * from user_notpassword where username = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, username);
		ResultSet result = preparedStatement.executeQuery();
		result.next();

		String usernameString = result.getString("username");
		String nameString = result.getString("name");
		String passwordString = "******"; // security password concealment

		User user = new User(usernameString, passwordString, nameString);
		return user;
	}

	public List<User> getListUsers() throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();

		String query = "SELECT * from user_notpassword";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();

		List<User> listUsers = new ArrayList<User>();
		while (result.next()) {
			String usernameString = result.getString("username");
			String nameString = result.getString("name");
			String passwordString = "******"; // security password concealment

			listUsers.add(new User(usernameString, passwordString, nameString));
		}

		return listUsers;
	}

	public List<ChatMessage> getListMessage(User user) throws ClassNotFoundException, SQLException {
		List<ChatMessage> listMessage = new ArrayList<ChatMessage>();
		Connection connection = MySQLService.getMysqlConnection();

		String username = user.getUsername();
		String query = "SELECT * from chatmessage where usernameTo = " + username;
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		while(result.next()) {
			String c_dateString = result.getString("c_date");
			String usernameFromString = result.getString("usernameFrom");
			String usernameToString = result.getString("usernameTo");
			String c_textString = result.getString("c_text");
			
			listMessage.add(new ChatMessage(c_dateString, usernameFromString, usernameToString, c_textString));
		}

		return listMessage;
	}

}
