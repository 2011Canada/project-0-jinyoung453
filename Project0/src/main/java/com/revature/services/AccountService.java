package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public interface AccountService {

	
	// probably should make 'createAccount'
	public Account create(Account ac);
	
	//login
	public Account login(int id, char type);
	
	//view balance
	
	//withdrawal
	public void withdrawal(Customer cs, double amt);
	
	//deposit
	public void deposit(Customer cs, double amt);
	
	//showAllCustomerList
	public List<Customer> showAllCustomer();
}