package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.exception.InvalidLoginException;
import com.revature.model.BankAccount;
import com.revature.util.ConnectionUtil;

public class BankAccountRepositoryJdbc implements BankAccountRepository {


	private static Logger logger = Logger.getLogger(BankAccountRepositoryJdbc.class);
	
	/*
	 * Singleton Logic
	 */
	private static BankAccountRepositoryJdbc repository = new BankAccountRepositoryJdbc();
	
	public BankAccountRepositoryJdbc() {}
	
	public static BankAccountRepository getInstance() {
		//We don't need this part bc we are init inline.
//		if(repository == null) {
//			return new CelebrityRepositoryJdbc();
//		}
		
		return repository;
	}
	
	@Override
	public boolean insert(BankAccount account) {
	
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "INSERT INTO BANKACCOUNT(B_ACCOUNT_NUMBER, B_FIRST_NAME, B_LAST_NAME, B_USER_NAME, B_PASS_WORD, B_BALANCE) VALUES(?,?,?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(++parameterIndex, account.getAccountNumber());
			statement.setString(++parameterIndex, account.getFirstName());
			statement.setString(++parameterIndex, account.getLastName());
			statement.setString(++parameterIndex, account.getUserName());
			statement.setString(++parameterIndex, account.getPassWord());
			statement.setDouble(++parameterIndex, account.getBalance());
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException e){
			logger.error("Exception thrown while inserting new Account", e);
		}
		return false;
	}

		
		public Set<BankAccount> selectAll() {
			logger.trace("Getting all Accounts");
			try(Connection connection = ConnectionUtil.getConnection()){
				String sql = "SELECT * FROM BankAccount";
				PreparedStatement statement = connection.prepareStatement(sql);
				
				
				ResultSet result = statement.executeQuery();
				
				Set<BankAccount> set = new HashSet<>();
				while(result.next()) {
					set.add(new BankAccount(
					result.getLong("B_ACCOUNT_NUMBER"),
					result.getString("B_FIRST_NAME"),
					result.getString("B_LAST_NAME"),
					result.getString("B_USER_NAME"),
					result.getString("B_PASS_WORD"),
					result.getDouble("B_BALANCE")
					));
				}
				return set;
				
			} catch (SQLException e) {
				logger.error("Error while selecting all Accounts.", e);
			}
			return null;
			
		}
		
	
		public static int getUser(String userName, String passWord) throws InvalidLoginException{
			logger.trace("Getting all Accounts");
			int accountNumber = 0;
			try(Connection connection = ConnectionUtil.getConnection()){
				String sql = "SELECT B_ACCOUNT_NUMBER FROM BANKACCOUNT WHERE B_USER_NAME = ? AND B_PASS_WORD = ?";
				

				PreparedStatement statement = connection.prepareStatement(sql);
				int parameterIndex = 0;
				statement.setString(++parameterIndex, userName);
				statement.setString(++parameterIndex, passWord);
				ResultSet result = statement.executeQuery();
				logger.trace("Query executed successfully.");
				while(result.next()){
					accountNumber = result.getInt("B_ACCOUNT_NUMBER");
					logger.trace("AccountNumber inserted correctly");
				}
				return accountNumber;

			}catch (SQLException e){
				throw new InvalidLoginException("Login Failed.");
			}
		}
		
		

		public static void main(String[] args) {
			BankAccountRepository repository = new BankAccountRepositoryJdbc();
			
			//Testing Insert
//			repository.insert(new BankAccount(102, "Beyonce", "Knowles", "BK","4by4", 60.50));
//			repository.insert(new BankAccount(101, "Michael", "Jackson", "MJ","r2d2", 52.00));

			//Testing selectAll
//			logger.info(repository.selectAll());

		}
		
	}

