/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Robert Haas
 *
 */
public class Util {
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/library?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "JsEhsm#j@S6a";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}
}
