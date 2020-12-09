package com.revature.models;

public class Employee extends Account{

	Employee(int inId) {
		super(inId);
	}

	public Employee(int inId, String inFName, String inLName) {
		super (inId, inFName, inLName, 'E');
	}
}
