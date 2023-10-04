package com.laptrinhjavaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	static final String DB_URL = "jdbc:mysql://localhost:3306/springbootweb12023";
	static final String USER = "root";
	static final String PASS = "";
	
	public static Connection getCon() {
		Connection conn = null;		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
				
	}
}
