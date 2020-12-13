package com.revature.services;

import java.util.List;

import com.revature.launcher.AccountLauncher;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.repositories.AccountDAO;

public class AccountServiceImplementation implements AccountService {

	private AccountDAO ad;

	public AccountServiceImplementation(AccountDAO ad) {
		this.ad = ad;
	}

	public Account create(Account ac) {
		return ad.createAccount(ac);
	}

	public Account login(int id, char type) {
		return ad.findAccountById(id, type, "Approved");
	}

	public Account findAccount(int id, char type, String approval) {
		return ad.findAccountById(id, type, approval);
	}

	
	public char chkActivationStatus(int id) {
		return ad.findCustomerStatusById(id);
	}
	
	public String chkApprovalStatus(int id) {
		return ad.findCustomerApprovalById(id);
	}

	public void setStatus(Customer cs, char status, String approval) {
		ad.updateCustStatus(cs, status, approval);
		AccountLauncher.e720Logger.info("ACCOUNT ID(" + cs.getId() + ") IS ACTIVATED");
	}

	public void withdrawal(Customer cst, double amount) {
			ad.updateAccount(cst, cst.getBalance() - amount);
			cst.setBalance(cst.getBalance() - amount);
			AccountLauncher.e720Logger.info(cst.getfName() + " " + cst.getlName() + " HAS WITHDRAWN: $" + amount);
	}

	public void deposit(Customer cst, double amount) {
		ad.updateAccount(cst, cst.getBalance() + amount);
		cst.setBalance(cst.getBalance() + amount);
		AccountLauncher.e720Logger.info(cst.getfName() + " " + cst.getlName() + " HAS DEPOSITED: $" + amount);
	}

	public List<Customer> showAllCustomers() {

		return ad.showAllCustomers();
	}

	public List<Customer> showInactiveCustomers() {
		// System.out.println("ee");
		return ad.showAllInactiveCustomers();
	}

}
