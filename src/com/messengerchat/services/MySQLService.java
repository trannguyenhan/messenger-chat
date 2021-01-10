package com.messengerchat.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLService {
	public static Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
        String userName = "root";
        String password = "mysql1234";
        String connectionUrl = "jdbc:mysql://localhost:3306/messengerchat?"
        		+ "autoReconnect=true&"
        		+ "useSSL=false&useUnicode=true&"
        		+ "useJDBCCompliantTimezoneShift=true&"
        		+ "useLegacyDatetimeCode=false&"
        		+ "serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
        return conn;
    }
    
}
