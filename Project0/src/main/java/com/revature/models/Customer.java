package com.revature.models;

public class Customer extends Account {
	private double balance = 0.0;
	
	Customer(int inId) {
		super(inId);
	}

	public Customer(int inId, String inFName, String inLName, double inBalance) {
		super (inId, inFName, inLName, 'C');
		this.balance = inBalance;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public void display() {
		System.out.println("ACCOUNT [ ID: " + super.getId() + ", First Name: " + super.getfName() + ", Last Name: " + super.getlName() + ", Balance: "+ this.balance +" ]"  ); 		
	}
}
