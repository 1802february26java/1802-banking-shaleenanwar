package com.revature.controller;

import java.util.Scanner;

import com.revature.exception.InvalidInputException;

public class BankMenu {
	static Scanner scanner = new Scanner(System.in);

	public static int option() throws InvalidInputException{
	
		System.out.println("Welcome!");
		System.out.println("");
		System.out.println("How can I help you? Type the number of the banking option you'd like.");
		System.out.println("");
		System.out.println("1 = View Balance");
		System.out.println("");
		System.out.println("2 = Withdraw Funds");
		System.out.println("");
		System.out.println("3 = Deposit Funds");

		
		int option = scanner.nextInt();
		if (option > 3 || option < 1){
			throw new InvalidInputException("Please enter either 1, 2, or 3.");
		}
		
		return option;
	}
	
	public static double viewBalance(long accountNumber) throws InvalidInputException{
		double balance;
		try{
			balance = DatabaseAccess.getBalance(accountNumber);
		} catch (InvalidInputException e){
			throw new InvalidInputException("Unable to attain Balance");
		}

		System.out.println("Your Current Balance is: " + balance);
		return balance;
	}

	
	public static void withdrawFunds(long accountNumber)throws InvalidInputException{
		double balance = DatabaseAccess.getBalance(accountNumber);
		System.out.println("Your Current Balance is: " + balance);
		System.out.println("");
		
		System.out.println("How much funds would you like to withdraw?");
		double withdrawl = scanner.nextDouble();

		double newBalance = (balance - withdrawl);
		DatabaseAccess.newBalance(accountNumber,newBalance);
		System.out.println("");
		System.out.println("Your balance after the withdrawl is now: " + newBalance);
		System.out.println("");
	}

	public static void depositFunds(long accountNumber) throws InvalidInputException{
		double balance = DatabaseAccess.getBalance(accountNumber);
		System.out.println("Your Current Balance is: " + balance);
		System.out.println("");
		System.out.println("How much would you like to deposit?");
		double amount = scanner.nextDouble();

		double newBalance = (balance + amount);
		DatabaseAccess.newBalance(accountNumber,newBalance);
		System.out.println("");
		System.out.println("Your balance is now: " + newBalance);
		System.out.println("");
	}

}
