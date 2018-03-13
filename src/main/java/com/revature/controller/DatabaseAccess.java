package com.revature.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.revature.exception.InvalidInputException;

import com.revature.util.ConnectionUtil;

public class DatabaseAccess {
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);

	public static double getBalance(long accountNumber) throws InvalidInputException{
		logger.trace("Getting balance.");
		String customer = Long.toString(accountNumber);
		double balance = 0;
		try(Connection connection = ConnectionUtil.getConnection()){
			logger.trace("Connection is Successful");
			String sql = "SELECT B_BALANCE FROM BANKACCOUNT WHERE B_ACCOUNT_NUMBER = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			int parameterIndex = 0;
			statement.setString(++parameterIndex, customer);
			ResultSet result = statement.executeQuery();

			while(result.next()){
				balance = result.getDouble("B_BALANCE");
			}
			return balance;

		}catch (SQLException e){
			logger.error("Error connection to database getBalance");
		}
		return 0;
	}
	

	public static void newBalance(long accountNumber, double newAmount){
		logger.trace("Changing balance.");
		String customer = Long.toString(accountNumber);
		String balance = Double.toString(newAmount);

		try(Connection connection = ConnectionUtil.getConnection()){
			logger.trace("Connection successful");
			String sql = "UPDATE BANKACCOUNT SET B_BALANCE = ? WHERE B_ACCOUNT_NUMBER = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			int parameterIndex = 0;
			statement.setString(++parameterIndex, balance);
			statement.setString(++parameterIndex, customer);

			statement.executeQuery(); 
		}catch (SQLException e){
			logger.error("Error while updating queries.");
		}
	}


}
