package com.messengerchat.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {
	public boolean addUser(String name, String username, String password) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();
		
		String query = "INSERT INTO user(username, password, name)"
						+ " value(?, ?, ?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, name);
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
		return false;
	}
	
	public boolean checkUser(String username, String password) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLService.getMysqlConnection();
		
		String query = "SELECT * from user where username = ? and password = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		ResultSet result =  preparedStatement.executeQuery();
		
		if(result.next()) return true;
		return false;
	}

}
