package com.revature.models;

public class Account{
	public static int numOfAccount =  0;
	private int id=0;
	private char[] password;
	private String fName, lName;
	private char type;
	
	public Account(int inId, char[] password){
		this.id = inId;
		this.password = password;
		numOfAccount++;
	}
	
	public Account(int inId, char[] password, String inFName, String inLName, char type) {
		this(inId, password);
		this.fName = inFName;
		this.lName = inLName;
		this.type = type;
	}
	
//	@Override
//	public String toString() {
//		return "[ ID: " + id + ", First Name: " + fName + ", Last Name: " + lName + ", Balance: " + balance + " ]";
//	}
	
	public void display() {
		System.out.println("ACCOUNT INFORMATION [ID:" + id + ", First Name:" + fName + ", Last Name:" + lName + "]");		
	}
	
	public void display(boolean bool) {
		System.out.println("ACCOUNT INFORMATION [ID:" + id + ", First Name:" + fName + ", Last Name:" + lName + "]");		
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

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
	
	

}
