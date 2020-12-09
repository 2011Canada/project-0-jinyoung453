package com.revature.models;

public class Account implements Displayable{
	public static int numOfAccount =  0;
	private int id=0;
	private String fName, lName;
	private char type;
	
	Account(int inId){
		this.id = inId;
		numOfAccount++;
	}
	
	public Account(int inId, String inFName, String inLName, char type) {
		this(inId);
		this.fName = inFName;
		this.lName = inLName;
		this.type = type;
	}
	
//	@Override
//	public String toString() {
//		return "[ ID: " + id + ", First Name: " + fName + ", Last Name: " + lName + ", Balance: " + balance + " ]";
//	}
	
	public void display() {
		System.out.println("ACCOUNT [ ID: " + id + ", First Name: " + fName + ", Last Name: " + lName + " ]");		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

}
