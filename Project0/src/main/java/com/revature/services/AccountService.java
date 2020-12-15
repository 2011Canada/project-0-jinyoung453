package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public interface AccountService {
	
	// probably should make 'createAccount'
	public Account create(Account ac);
	
	//login
	public Account login(int id,char type);
	
	//findAccount
	public Account findAccount(int id, char type, String approval);
	
	public char chkActivationStatus(int id);
	
	public String chkApprovalStatus(int id);
	
	public String chkAccType(int id);
	
	public void setStatus(Customer cs, char status, String approval);
	//withdrawal
	public void withdrawal(Customer cs, double amt);
	
	//deposit
	public void deposit(Customer cs, double amt);
	
	//showAllCustomerList
	public List<Customer> showAllCustomers();
	
	public List<Customer> showInactiveCustomers();
}
