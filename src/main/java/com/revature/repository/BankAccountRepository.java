package com.revature.repository;

import java.util.Set;

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
	
	
	public BankAccount findByFirstName(String firstName);

	/**
	 * Select all celebrities from the database.
	 */
	
	public Set<BankAccount> selectAll();
}

	

