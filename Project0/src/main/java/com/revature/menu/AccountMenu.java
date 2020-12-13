package com.revature.menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.launcher.AccountLauncher;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.services.AccountService;

public class AccountMenu{

	//N:NEW, L:LOGIN, E:EMPLOYEE 
	
	static String status = "N";
	static String yn = "";
	static boolean isEmployee;
	static boolean backToMenu;
	static double amount = 0;
	static int id = -1;
	AccountService as;
	Account myAccount;
	Scanner userInput;
	List<Customer> cstList;
	
	public AccountMenu(AccountService as) {
		this.as = as;
//		cstList = new ArrayList<Customer>(this.as.showAllCustomer());
		userInput = new Scanner(System.in);
	}

	public void firstDisplay() {
		boolean chk = false;
		while(!chk) {
			System.out.println( "==========================\n" +
			 	 	 			"    1: CUSTOMER\n" +
			 	 	 			"    2: EMPLOYEE\n" +
			 	 	 			"==========================");
			System.out.print("SELECT A SYSTEM YOU WOULD LIKE TO USE: ");
			int num = 0;
			userInput = new Scanner(System.in);
			if(userInput.hasNextInt()) {
				num = userInput.nextInt();
				if(num == 1) {
					isEmployee = false;
					chk = true;
				}else if(num == 2) {
					isEmployee = true;
					chk = true;
				}else {
					System.out.println("PLEASE TRY AGAIN!!\n");
				}
			}else {
				System.out.println("PLEASE TRY AGAIN!!\n");
			}
		}
		
		
	}
	
	public void display() {
		//For Customer
		if(!isEmployee) {
			if(status.equals("N")) {
				System.out.print(menu());
			}else if(status.equals("L")) {
				System.out.print(menuAfterLogin());
			}else {
				System.out.print(menu());
			}
		//For Employee
		}else {
			if(status.equals("N")) {
				System.out.print(menu());
			}else if(status.equals("L")) {
				System.out.print(menuAfterLogin());
			}else {
				System.out.print(menu());
			}
		}
		
	}
	
	public String menu() {
		String str = "\n==========================\n";
		if(!isEmployee) {
			str +=  "    1: REGISTER\n" +
				    "    2: LOGIN\n" +
					"    3: BACK\n";
		}else {
			str += "    1: LOGIN\n" + 
		           "    2: BACK\n";
		}
		str += "==========================\n";
		return str;
	}
	
	public String menuAfterLogin() {
		
		String str = "\n==========================\n";
		if(!isEmployee) {
			str +=  "    1: BALANCE\n" +
					"    2: DEPOSIT\n" +
					"    3: WIDTHRAWAL\n" +
					"    4: TRANSFER\n" +
					"    5: LOGOUT\n";
		}else {
			str +=  "    1: VIEW ALL ACCOUNTS\n" +
					"    2: APPROVAL ACCOUNT\n" +
					"    3: TRANSACTION LOG\n" +
					"    4: LOGOUT\n";
		}
		str += "==========================\n";
		return str;
	}
	
	public String approvalDisp() {
		String str = "\n==========================\n";
			str += "    1: APPROVE ALL\n" + 
		           "    2: APPROVE ONE\n" + 
		           "    3: APPROVE ONE BY ONE\n" + 
		           "    4: REJECT ALL\n" +
		           "    5: BACK\n";
		str += "==========================\n";
		return str;
	}
	
	public void info(Account acc) {
		System.out.println("\n** ACCOUNT INFORMATION **");
		acc.display();
	}
	
	public boolean amountValidation(Scanner in) {
		if(in.hasNextDouble()) {
			amount = in.nextDouble();
			if( amount <= 0) {
				System.out.println("[WARNING] AMOUNT MUST GREATER THAN 0");
				return false;
			}else {
				return true;
			}
		}else {
			System.out.println("[WARNING] AMOUNT MUST BE NUMBER");
			return false;
		}
	}
	
	public boolean idValidation(Scanner in, char type) {
		if(in.hasNextInt()) {
			backToMenu = false;
			id = in.nextInt();
			//System.out.println("id == " + id);
			if(id == 0) {
				backToMenu = true;
				return true;
			}
			if(type == 'C') {
				if(as.chkApprovalStatus(id).equalsIgnoreCase("Approved")) {
					if(as.chkActivationStatus(id) == 'I') {
						System.out.println("[WARNING] THE ACCOUNT HAS BEEN REJECTED. PLEASE VISIT A BANK");
						id = 0;
						backToMenu = true;
						return true;
					}else {
						return true;
					}
				}else {
					System.out.println("[WARNING] THE ACCOUNT IS NOT ACTIVATED");
					return false;
				}
			}else {
				if(as.findAccount(id, type, "Approved") == null) {
					System.out.println("[WARNING] ACCOUNT DOES NOT EXIST. PLEASE TRY AGAIN!!");
					return false;
				}else {
					return true;	
				}
			}

		}else {
			System.out.println("[WARNING] ACCOUNT DOES NOT EXIST. PLEASE TRY AGAIN!!");
			return false;
		}
	}
	
	
	public String ynValidation(Scanner in) {
		yn = in.next();
		if(yn.equalsIgnoreCase("Y") || yn.equalsIgnoreCase("N") ) {
			return yn;
		}else {
			System.out.println("INVALID INPUT. PLEASE ENTER \"Y\" OR \"N\"");
			return yn="";
		}
	}
	
	public int numValidation(int n) {
		int num = 0;
		boolean chk = false;
		while(!chk) {
		System.out.print("PLEASE ENTER NUMBER: ");
		userInput = new Scanner(System.in);
			if(userInput.hasNextInt()) {
				num = userInput.nextInt();
				if(num > 0 && num <= n) {
					chk = true;
					return num;
				}else {
					System.out.println("PLEASE TRY AGAIN!!\n");
				}
			}else {
				System.out.println("PLEASE TRY AGAIN!!\n");
			}
		}
		
		return num;
	}
	
	public void manageUserInput() {
		int num = 0;
		//For Customer
		if(!isEmployee) {
			if(status.equals("N")) {
				num = numValidation(3);
			}else if(status.equals("L")) {
				num = numValidation(5);
			}else {
				num = numValidation(3);
			}
		//For Employee
		}else {
			if(status.equals("N")) {
				num = numValidation(2);
			}else if(status.equals("L")) {
				num = numValidation(4);
			}else {
				num = numValidation(2);
			}
		}
		boolean chk = false;
		if(status.equals("N")) {
			if(!isEmployee) {
				switch(num) {
				case 1:	//Create New Account
					chk = false;
					System.out.print("FIRST NAME: ");
					String fName = userInput.next();
					
					System.out.print("LAST NAME: ");
					String lName = userInput.next();
					
					System.out.print("PASSWORD: ");
					String password = userInput.next();
					
					char[] pwArr = password.toCharArray();
					
					while(!chk) {
						System.out.print("DEPOSIT AMOUNT: ");
						if(amountValidation(new Scanner(System.in))) {
							chk = true;
						}else {
							System.out.println();
						}
					}
					cstList = as.showAllCustomers();
					Account newAccount = new Customer(cstList.get(cstList.size()-1).getId()+1, pwArr, fName, lName, amount, 'I', "New");
					as.create(newAccount);
					AccountLauncher.e720Logger.info("NEW ACCOUNT CREATED" + "[ACCOUNT ID:" + newAccount.getId() + ", NAME:" + newAccount.getfName() + " " + newAccount.getlName() + ", DEPOSIT:" + amount);
					
					System.out.println("\nYOUR NEW ACCOUNT IS CREATED");
					newAccount.display();
					newAccount = null;
					amount = 0.0;
					break;
				case 2:	//Login
					chk = false;
					while(!chk) {
						System.out.println("\n(0: GO BACK TO MENU)");
						System.out.print("PLEASE ENTER YOUR ID: ");
						if(idValidation(new Scanner(System.in), 'C')) {
							chk = true;
						}
					}
					if(backToMenu) {
						break;
					}
					chk = false;
					while(!chk) {
						System.out.print("PLEASE ENTER YOUR PASSWORD: ");
						String pw = userInput.next();
						myAccount = as.login(id,'C');
						if(pw.length() == myAccount.getPassword().length) {
							for(int i=0; i<pw.length(); i++) {
								if(pw.charAt(i) != myAccount.getPassword()[i]) {
									System.out.println("[WARNING] INVALID PASSWORD. PLEASE TRY AGAIN!!\n");
									break;
								}
								if(i == pw.length() -1) chk = true;
							}
						}else {
							System.out.println("[WARNING] INVALID PASSWORD. PLEASE TRY AGAIN!!\n");
						}
					}
					if(chk) {
						AccountLauncher.e720Logger.info(myAccount.getfName() + " " + myAccount.getlName() + " HAS LOGGED IN");
						chk = false;
					}
					myAccount.display();
					status = "L";
					break;
				case 3: //back
					status = "N";
					System.out.println();
					firstDisplay();
					break;
				default:
					System.out.println("PLEASE TRY AGAIN!!");
					return;
				}
			}else {
				switch(num) {
				case 1: //login
					chk = false;
					while(!chk) {
						System.out.print("\nPLEASE ENTER YOUR ACCOUNT ID: ");
						if(idValidation(new Scanner(System.in),'E')) {
							chk = true;
						}
					}
					chk = false;
					while(!chk) {
						System.out.print("PLEASE ENTER YOUR PASSWORD: ");
						String pw = userInput.next();
						myAccount = as.login(id,'E');
						if(pw.length() == myAccount.getPassword().length) {
							for(int i=0; i<pw.length(); i++) {
								if(pw.charAt(i) != myAccount.getPassword()[i]) {
									System.out.println("[WARNING] INVALID PASSWORD. PLEASE TRY AGAIN!!\n");
									break;
								}
								if(i == pw.length() -1) chk = true;
							}
						}else {
							System.out.println("[WARNING] INVALID PASSWORD. PLEASE TRY AGAIN!!\n");
						}
					}
					if(chk) {
						AccountLauncher.e720Logger.info(myAccount.getfName() + " " + myAccount.getlName() + " HAS LOGGED IN");
						chk = false;
					}
					myAccount.display();
					status = "L";
					id = -1;
					break;
				case 2: //back
					status = "N";
					System.out.println();
					firstDisplay();
					break;
				
				default:
					System.out.println("PLEASE TRY AGAIN!!");
					return;
				}
			}
		}else if(status.equals("L")) {
			if(!isEmployee) {
				Customer cstAcc = (Customer)myAccount;
				switch(num) {
				case 1:
					//info(cstAcc);
					System.out.println("YOUR BALANCE: " + cstAcc.getBalance());
					break;
				case 2:
					chk = false;
					while(!chk) {
						System.out.print("\nENTER DEPOSIT AMOUNT: ");
						if(amountValidation(new Scanner(System.in))) {
							as.deposit(cstAcc, amount);
							System.out.println(amount + " DOLLARS ARE SUCCESSFULLY DEPOSITED");
							chk = true;
							amount = 0;	
						}
					}
					//info(cstAcc);
					System.out.println("YOUR BALANCE: " + cstAcc.getBalance());
					break;
				case 3:
					chk = false;
					while(!chk) {
						System.out.print("\nENTER WITHDRAWAL AMOUNT: ");
						if(amountValidation(new Scanner(System.in))) {
							if(amount > cstAcc.getBalance()) {
								System.out.println("PLEASE CHECK YOUR BALANCE");
							}else {
								as.withdrawal(cstAcc, amount);
								System.out.println(amount + " DOLLARS ARE SUCCESSFULLY WITHDRAWN");
								chk = true;
								amount = 0;
							}
						}
					}
					//info(cstAcc);
					System.out.println("YOUR BALANCE: " + cstAcc.getBalance());
					break;
				case 4:
					boolean amountChk = false;
					boolean accountChk = false;
					Customer transCust = null;
					while(!accountChk) {
						System.out.println("\n(0: GO BACK TO MENU)");
						System.out.print("ENTER ACCOUNT NUMBER TO TRANSFER: ");
						if(idValidation(new Scanner(System.in),'C')) {
							accountChk = true;
						}
					}
					if(backToMenu) {
						break;
					}
					while(!amountChk) {
						System.out.print("\nENTER AMOUNT TO TRANSFER: ");
						if(amountValidation(new Scanner(System.in))) {
							transCust = (Customer)as.findAccount(id, 'C', "Approved");
							if(amount > cstAcc.getBalance()) {
								System.out.println("PLEASE CHECK YOUR BALANCE");
							}else {
								as.withdrawal(cstAcc, amount);
								as.deposit(transCust, amount);
								System.out.println(amount + " DOLLARS SUCCESSFULLY TRANSFERED");
								amount = 0;		
								id = -1;
								amountChk = true;
							}
						}
					}
					System.out.println("YOUR BALANCE: " + cstAcc.getBalance());
					//info(cstAcc);
					//LINE TO REMOVE
					//info(transCust);
					break;
				case 5:
					chk = false;
					while(!chk) {
						System.out.print("\nWOULD YOU LIKE TO LOGOUT??(Y/N)");
						yn = ynValidation(new Scanner(System.in));
						if(!yn.equals("")) {
							if(yn.equalsIgnoreCase("Y")) {
								status = "N";
								myAccount = null;
								System.out.println();
								firstDisplay();
								break;
							}
							chk = true;
						}
					}
					break;
				default:
					System.out.println("PLEASE TRY AGAIN!!");
					return;
				}
			}else {
				switch(num) {
				case 1: //VIEW ALL ACCOUNTS
					cstList = as.showAllCustomers();
					for(Customer cst: cstList) {
						cst.display();
					}
					break;
				case 2: //APPROVAL ACCOUNT
					List<Customer> inactCstList = as.showInactiveCustomers();
					System.out.println("\n** INACTIVE ACCOUNT LIST **");
					for(Customer cst: inactCstList) {
						cst.display();
					}
					System.out.println(approvalDisp());
					int n = numValidation(5);
					switch(n) {
						case 1:	//APPROVE ALL
							for(Customer cst: inactCstList) {
								//cst.setStatus('A');
								as.setStatus(cst, 'A', "Approved");
								System.out.println("[ID:" + cst.getId() + "] IS ACTIVATED");
								AccountLauncher.e720Logger.info("ACCOUNT ID(" + cst.getId() + ") IS ACTIVATED");
							}
							break;
						case 2: //APPROVE ONE
							chk = false;
							while(!chk) {
								System.out.print("\nPLEASE ENTER THE ACCOUNT ID TO ACTIVATE: ");
								userInput = new Scanner(System.in);
								if(userInput.hasNextInt()) {
									int inputId = userInput.nextInt();
									if(inputId > 0) {
										Customer tempCust = (Customer)as.findAccount(inputId, 'C', "New");
										if(tempCust == null) {
											System.out.println("[WARNING] ACCOUNT DOES NOT EXIST. PLEASE TRY AGAIN!!");
										}else {
											as.setStatus(tempCust, 'A', "Approved");
											//tempCust.setStatus('A');
											System.out.println("[ID:" + tempCust.getId() + "] IS ACTIVATED");
											
											chk = true;	
										}
									}else {
										System.out.println("INVALID INPUT!! PLEASE TRY AGAIN!!");
									}
								}else {
									System.out.println("INVALID INPUT!! PLEASE TRY AGAIN!!");
								}
							}
							break;
						case 3: // APPROVE ONE BY ONE
							for(Customer c : inactCstList) {
								chk = false;
								while(!chk) {
									System.out.println();
									c.display();
									System.out.print("WOULD YOU LIKE TO ACTIVATE THIS ACCOUNT?(Y/N)");
									yn = ynValidation(new Scanner(System.in));
									if(yn.equalsIgnoreCase("Y")) {
										as.setStatus(c, 'A', "Approved");
										System.out.println("[ID:" + c.getId() + "] IS ACTIVATED");
										AccountLauncher.e720Logger.info("ACCOUNT ID(" + c.getId() + ") IS ACTIVATED");
										chk = true;
									}
									else if (yn.equalsIgnoreCase("N")) {
										as.setStatus(c, 'I', "Approved");
										chk = true;
									}
									else continue;
								}
							}
							break;
						case 4: // REJECT ALL
						case 5:
							break;
						}
						
					break;
				case 3: //TRANSACTION LOG
					chk = false;
					try {
						
						BufferedReader reader = new BufferedReader(new FileReader("D:\\Revature\\Project0\\project-0-jinyoung453\\Project0\\logs\\trace.log"));
						String fileContent = "";
						String currentReadingLine = reader.readLine();
						
						while (currentReadingLine != null) {
							fileContent += currentReadingLine + System.lineSeparator();
							System.out.println(currentReadingLine);
							currentReadingLine = reader.readLine();
						}
						while(!chk) {
							System.out.print("WOULD YOU LIKE TO DOWNLOAD?(Y/N)");
							yn = ynValidation(new Scanner(System.in));
							if(!yn.equals("")) {
								if(yn.equalsIgnoreCase("Y")) {
									//BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Revature\\Project0\\project-0-jinyoung453\\Project0\\trace.log"));
									BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Revature\\Project0\\project-0-jinyoung453\\Project0\\logs\\TransactionLog.log", true));
									writer.write(fileContent);
									writer.close();
									System.out.println("\nTRANSACTION LOG IS DOWNLOADED");
									System.out.println("FILE LOCATION: D:\\Revature\\Project0\\project-0-jinyoung453\\Project0\\logs\\TransactionLog.log");
								}
								chk = true;
							}
						}
						reader.close();
					}catch(FileNotFoundException e) {
						e.printStackTrace();
					}catch(IOException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					chk = false;
					while(!chk) {
						System.out.print("\nWOULD YOU LIKE TO LOGOUT??(Y/N)");
						yn = ynValidation(new Scanner(System.in));
						if(!yn.equals("")) {
							if(yn.equalsIgnoreCase("Y")) {
								status = "N";
								myAccount = null;
								System.out.println();
								firstDisplay();
								break;
							}
							chk = true;
						}
					}
					break;
				default:
					System.out.println("PLEASE TRY AGAIN!!");
					return;
				}
			}
		}
	}
}
