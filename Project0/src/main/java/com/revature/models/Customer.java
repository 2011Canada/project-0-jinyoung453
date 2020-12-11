package com.revature.models;

public class Customer extends Account {
	private double balance = 0.0;
	private char status = 'I'; //I:Inactive, A:Active
	
	Customer(int inId, char[] password) {
		super(inId, password);
	}

	public Customer(int inId, char[] password ,String inFName, String inLName, double inBalance, char status) {
		super (inId,password, inFName, inLName, 'C');
		this.balance = inBalance;
		this.status = status;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public void display() {
		String stat = "Inactive";
		if(this.status == 'A') stat = "Active"; 
		System.out.println("CUSTOMER [ ID: " + super.getId() + ", First Name: " + super.getfName() + ", Last Name: " + super.getlName() + ", Balance: "+ this.balance +", Status: " + stat +" ]"  ); 		
	}
}
