package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menu.AccountMenu;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountImplementationPostgreDAO;
import com.revature.services.AccountService;
import com.revature.services.AccountServiceImplementation;


public class AccountLauncher {

	public static Logger accountLogger = LogManager.getLogger("com.revature.account");
	
	public static void main(String[] args) {
		AccountDAO aDao = new AccountImplementationPostgreDAO();
		AccountService aService = new AccountServiceImplementation(aDao);
		AccountMenu aMenu = new AccountMenu(aService);
		
		accountLogger.debug("SERVER HAS STARTED");
		System.out.println("**************************");
		System.out.println("***      JK SYSTEM     ***");
		System.out.println("**************************\n");
		aMenu.firstDisplay();
		while(true) {

			aMenu.display();
			aMenu.manageUserInput();
		}
		
		
	}
	
	
	
}