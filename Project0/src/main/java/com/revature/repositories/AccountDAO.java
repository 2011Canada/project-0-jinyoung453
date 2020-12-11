package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;

public interface AccountDAO {
	
	public Account createAccount(Account ac);
	public Account updateAccount(Customer ac, double amount);
	public List<Account> showAllAccount();
	public List<Customer> showAllCustomers();
	public List<Customer> showInactiveCustomers();
	//public Account findAccountById(int id, String pw, char type) throws AccountNotFoundException;
	//public Account findAccountById(int id, char type) throws AccountNotFoundException;
	//public Account findAccountById(int id, String pw, char type) ;
	public Account findAccountById(int id, char type);
	public char findCustomerStatusById(int id);
}
