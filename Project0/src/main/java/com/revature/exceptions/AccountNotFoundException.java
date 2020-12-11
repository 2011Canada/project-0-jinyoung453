package com.revature.exceptions;

import com.revature.launcher.AccountLauncher;

public class AccountNotFoundException extends Exception{

	//private static int numberOfOccurances = 0;
	
	public AccountNotFoundException() {
		
		super("That Account Was Not Found, Please Try Again");
		//numberOfOccurances++;
		AccountLauncher.e720Logger.debug("AccountNotFoundException()");
	}
}
