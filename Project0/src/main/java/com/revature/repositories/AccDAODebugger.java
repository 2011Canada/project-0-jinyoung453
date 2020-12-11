package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public class AccDAODebugger {

	public static void main(String[] args) {

		//AccountDAO ad = new AccountDAOImplementation();
		
		/*List<Account> accList = ad.showAllAccount();
		
		for(Account ac : accList) {
			ac.display();
		}
		*/
		
		AccountDAO ad = new AccountImplementationPostgreDAO();
		Account ac = new Customer(1, new char[]{'1'} ,"INSERT", "Kim", 1000.00, 'I');
		ad.createAccount(ac);
	}

}
