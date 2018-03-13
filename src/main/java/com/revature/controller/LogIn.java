package com.revature.controller;


import java.util.Scanner;

import com.revature.exception.InvalidLoginException;
import com.revature.repository.BankAccountRepositoryJdbc;


public class LogIn {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static int login() throws InvalidLoginException{
		String userName = "";
		String passWord = "";
		String[] userCredentials = {userName, passWord};
		int accountNumber = 0;

		BankAccountRepositoryJdbc repository = new BankAccountRepositoryJdbc();


		
		System.out.println("Enter your username.");
		userCredentials[0] = scanner.nextLine();
		System.out.println("Enter your password.");
		userCredentials[1] = scanner.nextLine();
		
		try {
			accountNumber = repository.getUser(userCredentials[0], userCredentials[1]);
			if (accountNumber == 0){
				throw new InvalidLoginException("Login Failed");
			}
		} catch (InvalidLoginException e) {
			throw new InvalidLoginException("Login Failed.");
		}
		
		return accountNumber;
	}
	
}
