package com.revature.models;

public class Employee extends Account{

	Employee(int inId, char[] password) {
		super(inId, password);
	}

	public Employee(int inId, char[] password, String inFName, String inLName) {
		super (inId, password, inFName, inLName, 'E');
	}
	
	@Override
	public void display() {
		System.out.println("ACCOUNT INFORMATION [ ID: " + super.getId() + ", First Name: " + super.getfName() + ", Last Name: " + super.getlName() +" ]"  ); 		
	}
}
