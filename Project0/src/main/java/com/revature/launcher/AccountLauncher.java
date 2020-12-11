package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menu.AccountMenu;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountDAOImplementation;
import com.revature.services.AccountService;
import com.revature.services.AccountServiceImplementation;


public class AccountLauncher {

	public static Logger e720Logger = LogManager.getLogger("com.revature.e720");
	
	public static void main(String[] args) {
		AccountDAO aDao = new AccountDAOImplementation();
		AccountService aService = new AccountServiceImplementation(aDao);
		AccountMenu aMenu = new AccountMenu(aService);
		
		e720Logger.info("SERVER HAS STARTED");
		
		aMenu.firstDisplay();
		while(true) {

			aMenu.display();
			aMenu.manageUserInput();
		}
		
		
	}
	
	
	
}