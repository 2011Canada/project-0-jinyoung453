package com.revature.services;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.repositories.AccountDAO;

public class AccountServiceImplementation implements AccountService{

	private AccountDAO ad;
	
	public AccountServiceImplementation(AccountDAO ad) {
		this.ad = ad;
	}

	public Account create(Account ac) {
		return ad.createAccount(ac);
	}

	public Account login(int id, char type) {
		try {
			//ad.findAccountById(id);
			return ad.findAccountById(id, type);
		}catch(AccountNotFoundException e) {
			System.out.println(e);
		}
		
		return null;
	}

	public void withdrawal(Customer cst, double amount) {
		ad.updateAccount(cst, cst.getBalance()- amount);
	}

	public void deposit(Customer cst, double amount) {
		
		ad.updateAccount(cst, cst.getBalance() + amount);
	}
	
	public List<Customer> showAllCustomer() {

		return ad.showAllCustomer();
	}

}
