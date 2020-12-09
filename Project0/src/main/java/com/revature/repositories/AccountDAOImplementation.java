package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;

public class AccountDAOImplementation implements AccountDAO {

	static List<Account> accList = new ArrayList<Account>();
	static List<Customer> cstList = new ArrayList<Customer>();
	static List<Employee> empList = new ArrayList<Employee>();
	
	static {
		Account jain = new Customer(1, "Jain", "Kim", 1000.00);
		Account ryan = new Customer(2, "Ryan", "Hong", 2000.00);
		Account sue = new Customer(3, "Sue", "Shin", 3000.00);
		Account emp1 = new Employee(1, "EMP", "AAA");
		Account emp2 = new Employee(2, "EMP2", "BBB");
		Account emp3 = new Employee(3, "EMP3", "CCC");
		Account emp4 = new Employee(4, "EMP4", "DDD");

		accList.add(jain);
		accList.add(ryan);
		accList.add(sue);
		accList.add(emp1);
		accList.add(emp2);
		accList.add(emp3);
		accList.add(emp4);
	}

	public Account createAccount(Account ac) {
		accList.add(ac);
		System.out.println();
		System.out.println("########## ACCOUNT LIST ##########");
		for (Account a : accList) {
			a.display();
		}
		System.out.println("###################################");

		return null;
	}

	public Account updateAccount(Customer cs, double amount) {
		cs.setBalance(amount);
		return null;
	}

	public List<Account> showAllAccount() {
		return accList;
	}
	
	public List<Customer> showAllCustomer() {
		for(Account ac:accList) {
			if(ac instanceof Customer) {
				cstList.add((Customer)ac);
			}
		}
		return cstList;
	}
	
	public Account findAccountById(int id, char type) throws AccountNotFoundException {
		for (Account ac : accList) {
			if (ac.getId() == id && ac.getType() == type) {
				return ac;
			}
		}
		throw new AccountNotFoundException();
	}
	
	public Customer findCustomerById(int id) throws AccountNotFoundException {
		for (Customer cst : cstList) {
			if (cst.getId() == id) {
				return cst;
			}
		}
		throw new AccountNotFoundException();
	}
	
}
