package com.revature.repository;

import com.revature.model.BankAccount;

/**
 * Bank Account entity DAO
 */

public interface BankAccountRepository {
	
	public boolean insert(BankAccount user);
	
	/**
	 * Inserts a new user in the database using a CallableStatement
	 * (Stored Procedure)
	 */
	
	
}
