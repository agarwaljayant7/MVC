package com.jayant.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection connection = null;
	static {
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	public static void close() {
		try {
			connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
