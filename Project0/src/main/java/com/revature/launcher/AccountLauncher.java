package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menu.AccountMenu;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountImplementationPostgreDAO;
import com.revature.services.AccountService;
import com.revature.services.AccountServiceImplementation;


public class AccountLauncher {

	public static Logger e720Logger = LogManager.getLogger("com.revature.e720");
	
	public static void main(String[] args) {
		AccountDAO aDao = new AccountImplementationPostgreDAO();
		AccountService aService = new AccountServiceImplementation(aDao);
		AccountMenu aMenu = new AccountMenu(aService);
		
		e720Logger.debug("SERVER HAS STARTED");
		
		aMenu.firstDisplay();
		while(true) {

			aMenu.display();
			aMenu.manageUserInput();
		}
		
		
	}
	
	
	
}