package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 * Utility class to obtain a connection object.
 *
 */
public class ConnectionUtil {
	
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	
	public static Connection getConnection() throws SQLException {
		
		String url = "jdbc:oracle:thin:@myassignments.cmk37qx2dyp6.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username = "bankAccount";
		String password = "p4ssw0rd";
		
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * Test that connection is working
	 */
	
	public static void main(String[] args) {
		/**
		 * Try with resources will close resources automatically
		 */
		
		
		try(Connection connection = ConnectionUtil.getConnection()){
			logger.info("Connection successful");
		} catch (SQLException e) {
			logger.error("Couldn't connect to the database", e);
		}
	}
	
}