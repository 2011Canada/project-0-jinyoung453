package com.revature.repositories;

import java.util.ArrayList;
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
		Account ac = new Customer(22, new char[]{'1'} ,"NEW", "Kim", 1000.00, 'I');
		
		//	ad.createAccount(ac);
		
		//	ad.updateAccount((Customer)ac, 10);
		
		/*
		List<Account> acList = ad.showAllAccount();
		for(Account a : acList) {
			a.display();
		}
		*/
		
		/*
		List<Customer> cstList = ad.showAllInactiveCustomers();
		for(Customer a : cstList) {
			a.display();
		}
		*/
		
		Account cst = ad.findAccountById(2, 'C');
		cst.display();
	
		//char a = ad.findCustomerStatusById(10);
		//System.out.println("a : " + Character.toString(a));
	
	}

}
