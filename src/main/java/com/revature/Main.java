package com.revature;

import com.revature.controller.LogIn;
import com.revature.exception.InvalidLoginException;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	public static void main(String[] args) throws InvalidLoginException {

		long account = LogIn.login();
	}
}
