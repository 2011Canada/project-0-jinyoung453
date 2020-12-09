package com.revature.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Displayable;
import com.revature.services.AccountService;

public class AccountMenu{

	//N:NEW, L:LOGIN, E:EMPLOYEE 
	static String status = "N";
	static boolean isEmployee;
	AccountService as;
	Account myAccount;
	Customer customer;
	
	Scanner userInput;
	List<Customer> cstList;
	
	public AccountMenu(AccountService as) {
		this.as = as;
		//cstList = new ArrayList<Customer>(this.as.showAllCustomer());
		userInput = new Scanner(System.in);
	}


	public void firstDisplay() {
		System.out.println( "############################\n" +
		 	 	 			"    1: CUSTOMER\n" +
		 	 	 			"    2: EMPLOYEE\n" +
		 	 	 			"############################\n");
		System.out.print("SELECT A SYSTEM YOU WOULD LIKE TO USE: ");
		int num = userInput.nextInt();
		if(num == 1) {
			isEmployee = false;
		}else {
			isEmployee = true;
		}
	}
	
	public void display() {
		//For Customer
		if(!isEmployee) {
			if(status.equals("N")) {
				System.out.print(menu() + "PLEASE ENTER NUMBER: ");
			}else if(status.equals("L")) {
				System.out.print(menuAfterLogin() + "PLEASE ENTER NUMBER: ");
			}else {
				System.out.print(menu() + "PLEASE ENTER NUMBER: ");
			}
		//For Employee
		}else {
			if(status.equals("N")) {
				System.out.print(menu() + "PLEASE ENTER NUMBER: ");
			}else if(status.equals("L")) {
				System.out.print(menuAfterLogin() + "PLEASE ENTER NUMBER: ");
			}else {
				System.out.print(menu() + "PLEASE ENTER NUMBER: ");
			}
		}
		
	}
	
	public String menu() {
		String str = "############################\n";
		if(!isEmployee) {
			str +=  "    1: REGISTER\n" +
				    "    2: LOGIN\n";
		}else {
			str += "    1: LOGIN\n";
		}
		str += "############################\n";
		return str;
	}
	
	public String menuAfterLogin() {
		
		String str = "############################\n";
		if(!isEmployee) {
			str +=  "    1: BALANCE\n" +
					"    2: DEPOSIT\n" +
					"    3: WIDTHRAWAL\n" +
					"    4: TRANSFER\n";
		}else {
			str +=  "    1: VIEW ALL ACCOUNTS\n" +
					"    2: APPROVAL ACCOUNT\n" +
					"    3: TRANSACTION LOG\n";
		}
		str += "############################\n";
		return str;
	}
	
	public void info(Account acc) {
		System.out.println("\n** YOUR ACCOUNT INFORMATION **");
		acc.display();
		System.out.println();
	}
	
	
	public void manageUserInput() {
		
		int num = this.userInput.nextInt();
		
		if(status.equals("N")) {
			if(!isEmployee) {
				if(num > 0 && num < 3) {
					switch(num) {
					case 1:	//Create New Account
						System.out.print("YOUR FIRST NAME: ");
						String fName = this.userInput.next();
						
						System.out.print("YOUR LAST NAME: ");
						String lName = this.userInput.next();
						
						System.out.print("DEPOSIT AMOUNT: ");
						double amount = this.userInput.nextDouble();
						
						Account newAccount = new Customer(++Account.numOfAccount, fName, lName, amount);
						as.create(newAccount);
						
						System.out.println("YOUR NEW ACCOUNT IS CREATED");
						newAccount.display();
						break;
					case 2:	//Login
						System.out.print("PLEASE ENTER YOUR ACCOUNT ID: ");
						int id = this.userInput.nextInt();
						myAccount = as.login(id, 'C');
						myAccount.display();
						status = "L";
						menuAfterLogin();
						break;
					}
				}else {
					System.out.println("PLEASE TRY AGAIN!!");
					return;
				}
			}else {
				System.out.print("PLEASE ENTER YOUR ACCOUNT ID: ");
				int id = this.userInput.nextInt();
				myAccount = as.login(id, 'E');
				myAccount.display();
				status = "L";
				menuAfterLogin();
				return;
			}
			
		}else if(status.equals("L")) {
			if(!isEmployee) {
				Customer cstAcc = (Customer)myAccount;
				if(num > 0 && num < 5) {
					double amount = 0.0;
					switch(num) {
					case 1:
						info(cstAcc);
						break;
					case 2:
						System.out.print("ENTER DEPOSIT AMOUNT: ");
						amount = this.userInput.nextDouble();
						as.deposit(cstAcc, amount);
						info(cstAcc);
						break;
					case 3:
						System.out.print("ENTER WITHDRAWAL AMOUNT: ");
						amount = this.userInput.nextDouble();
						as.withdrawal(cstAcc, amount);
						info(cstAcc);
						break;
					default:
						System.out.println("PLEASE TRY AGAIN!!");
						return;
					}
				}else {
					System.out.println("PLEASE TRY AGAIN!!");
					return;
				}
			}else {
				if(num > 0 && num < 5) {
					switch(num) {
					case 1: //VIEW ALL ACCOUNTS
						cstList = as.showAllCustomer();
						for(Customer cst: cstList) {
							cst.display();
						}
						break;
					case 2: //APPROVAL ACCOUNT
						
						break;
					case 3: //TRANSACTION LOG
						break;
					}
				}else {
					System.out.println("PLEASE TRY AGAIN!!");
					return;
				}
			}
		}
	}
}
