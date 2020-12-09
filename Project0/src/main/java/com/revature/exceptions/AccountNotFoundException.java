package com.revature.exceptions;

public class AccountNotFoundException extends Exception{

	private static int numberOfOccurances = 0;
	
	public AccountNotFoundException() {
		super("That Account Was Not Found, Please Try Again");
		numberOfOccurances++;
	}
}
