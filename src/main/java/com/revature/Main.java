package com.revature;



import com.revature.controller.BankMenu;
import com.revature.controller.LogIn;
import com.revature.exception.InvalidInputException;
import com.revature.exception.InvalidLoginException;



/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	public static void main(String[] args) throws InvalidLoginException, InvalidInputException {


		int accountNumber;

		try {
			accountNumber = LogIn.login();
		} catch (InvalidLoginException e) {
			throw new InvalidLoginException("Unable to login.");
		}

	
		int b = BankMenu.option();

		
		switch (b){
		case 1:
			BankMenu.viewBalance(accountNumber);
			break;
		case 2:
			BankMenu.withdrawFunds(accountNumber);
			break;
		case 3:
			BankMenu.depositFunds(accountNumber);
			break;

		}
		System.exit(0);
	}
}
