package com.revature.repository;

import java.util.Set;

import com.revature.exception.InvalidLoginException;
import com.revature.model.BankAccount;

/**
 * Bank Account entity DAO
 */

public interface BankAccountRepository {
	
	public boolean insert(BankAccount account);

	/**
	 * Select all accounts from the database.
	 */
	
	public Set<BankAccount> selectAll();
	
	public long getUser(String userName, String passWord) throws InvalidLoginException;

}


	

