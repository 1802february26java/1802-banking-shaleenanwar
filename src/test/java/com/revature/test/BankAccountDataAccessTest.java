package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.controller.BankMenu;
import com.revature.controller.LogIn;
import com.revature.exception.InvalidInputException;
import com.revature.exception.InvalidLoginException;
import com.revature.repository.BankAccountRepositoryJdbc;


public class BankAccountDataAccessTest {
	
	
	private static final BankMenu options = new BankMenu();
	private static final LogIn login = new LogIn();
	private static Logger logger = Logger.getLogger(BankAccountDataAccessTest.class);

// Test for existing Accounts with credentials
	
	@Test
	public void account101_login() throws InvalidLoginException{
		Object expected = 101;
		assertEquals(expected, BankAccountRepositoryJdbc.getUser("MJ", "r2d2"));
	}
	
	@Test
	public void account102_login() throws InvalidLoginException{
		Object expected = 102;
		assertEquals(expected, BankAccountRepositoryJdbc.getUser("BK", "4by4"));
	}

	
// Test for correct balance	
	@Test
	public void account101_balance() throws InvalidInputException{
		Object expected = 62.00;
		assertEquals(expected, BankMenu.viewBalance(101));
	}

	@Test
	public void account102_balance() throws InvalidInputException{
		Object expected = 80.50;
		assertEquals(expected, BankMenu.viewBalance(102));
	}
	
}
