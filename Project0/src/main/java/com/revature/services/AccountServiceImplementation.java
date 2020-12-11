package com.revature.services;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.launcher.AccountLauncher;
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
		return ad.findAccountById(id,type);
	}
	
	public Account findCustomer(int id, char type) {
		return ad.findAccountById(id, type);
	}

	public char chkStatus(int id) {
		return ad.findCustomerStatusById(id);
	}

	public void withdrawal(Customer cst, double amount) {
		ad.updateAccount(cst, cst.getBalance()- amount);
		AccountLauncher.e720Logger.info(cst.getfName() +" " + cst.getlName() + " HAS WITHDRAWN");
	}

	public void deposit(Customer cst, double amount) {
		ad.updateAccount(cst, cst.getBalance() + amount);
		AccountLauncher.e720Logger.info(cst.getfName() +" " + cst.getlName()  + " HAS DEPOSITED");
	}
	
	public List<Customer> showAllCustomers() {

		return ad.showAllCustomers();
	}
	
	public List<Customer> showInactiveCustomers() {
		//System.out.println("ee");
		return ad.showAllInactiveCustomers();
	}

}
