package com.revature.models;

public class Customer extends Account {
	private double balance = 0.0;
	private char status = 'I'; //I:Inactive, A:Active
	private String approval = "New"; //New, Approved
	
	Customer(int inId, char[] password) {
		super(inId, password);
	}

	public Customer(int inId, char[] password ,String inFName, String inLName, double inBalance, char status, String approval) {
		super (inId,password, inFName, inLName, 'C');
		this.balance = inBalance;
		this.status = status;
		this.approval = approval;
	}
	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
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
		System.out.println("CUSTOMER [ ID: " + super.getId() + ", First Name: " + super.getfName() + ", Last Name: " + super.getlName() + ", Balance: "+ this.balance +", Status: " + stat +", Approval: " + approval + " ]"  ); 		
	}
	

	public void display(boolean bool) {
		if(bool) { //Login
			System.out.println("CUSTOMER [ ID: " + super.getId() + ", First Name: " + super.getfName() + ", Last Name: " + super.getlName() + ", Balance: "+ this.balance +" ]"  );	
		}else {
			String stat = "Inactive";
			if(this.status == 'A') stat = "Active"; 
			System.out.println("CUSTOMER [ ID: " + super.getId() + ", First Name: " + super.getfName() + ", Last Name: " + super.getlName() + ", Balance: "+ this.balance +", Status: " + stat +" ]"  );
		}
		 		
	}
}
