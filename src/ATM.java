/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.util.Scanner;
import java.io.*;

public class ATM {
	
	//private BankAccount act;
	//public ATM(BankAccount account) {
	//	this.act = account;
	//}
	private Database actdb;
	private BankAccount act;
	private BankAccount transAct;
	
	Scanner in = new Scanner(System.in);
	
	public ATM() throws FileNotFoundException, IOException {
		actdb = new Database("accounts-db.txt");
		in = new Scanner(System.in);
	}

	public void run() throws FileNotFoundException, IOException {		
		int mainMenuSelect = 0, subMenuSelect = 0;
		boolean quit = false, logout = false;
		int quitCheck = 0, logoutCheck = 0;
		long inputActNum = 0; String inputPin = "";
		boolean auth = false;
		String newAct = ""; long newActNum;
		double depositAmt = 0, withdrawAmt = 0, transferAmt = 0;
		long transActNum;
		double transBalance;
				
		
		//////////////////////////////////MAIN MENU//////////////////////////////////////
		
		do {
			quit = false;
			System.out.println("\nPlease select an option from the menu below.\n\t1:\tOpenAccount\n\t2:\tLogin\n\t3:\tQuit");
			mainMenuSelect = in.nextInt();
			
			//////////////////////////////////OPEN ACCOUNT//////////////////////////////////////
			
			if(mainMenuSelect == 1) {
				newActNum = actdb.getMaxAccountNumber()+1;
				newAct += newActNum;
				System.out.println("What PIN would you like to use:");
				in.nextLine();
				newAct += in.nextLine();
				newAct += String.format("%1$-15s", "0.00");
				System.out.println(newAct);
				System.out.println("Enter your last name:");
				newAct += String.format("%1$-20s", in.nextLine());
				System.out.println(newAct);
				System.out.println("Enter your first name:");
				newAct += String.format("%1$-15s", in.nextLine());
				System.out.println(newAct);
				System.out.println("Enter your date of birth (YYYYMMDD):");
				newAct += String.format("%1$-8s", in.nextLine());
				System.out.println(newAct);
				System.out.println("Enter your phone number (##########):");
				newAct += String.format("%1$-10s", in.nextLine());
				System.out.println("Enter your street address:");
				newAct += String.format("%1$-30s", in.nextLine());
				System.out.println("Enter your city:");
				newAct += String.format("%1$-30s", in.nextLine());
				System.out.println("Enter your state:");
				newAct += String.format("%1$-2s", in.nextLine());
				System.out.println("Enter your postal code:");
				newAct += String.format("%1$-5s", in.nextLine());
				newAct += "Y";
				System.out.println(newAct);
				act = new BankAccount(newAct);
				actdb.writeAct(act, null);
				System.out.println(actdb.readAct(newActNum));
				System.out.println("\nYour account has been successfully created.");
				System.out.println("\nYour new account information:\n"+newAct);
			}
			
			//////////////////////////////////LOG IN//////////////////////////////////////
			
			if(mainMenuSelect == 2) {
				System.out.println("\nEnter your account number: ");
				inputActNum = in.nextLong();
				System.out.println("Enter your pin: ");
				in.nextLine();
				inputPin = in.nextLine();
				/////verify account number and pin exist & match//////
				if(actdb.readAct(inputActNum) != null && (Long.toString(inputActNum).length() == 9)) {
					act = new BankAccount(actdb.readAct(inputActNum));
					if(inputPin.equals(act.getUser().getPin())) {
						auth = true;
					}
				}
				//////////////////////////////////AUTHENTICATION//////////////////////////////////////
				
				if(auth == true) {
					System.out.print("\nHello "+act.getUser().getFirst()+" "+act.getUser().getLast()+"!\n");
			
					//////////////////////////////////SUB MENU//////////////////////////////////////
					
					do{
						logout = false;
						System.out.println("\nPlease select an option from the menu below.\n");
						System.out.println(
							"\t1:\tDeposit funds\n" + 
							"\t2:\tWithdraw funds\n" + 
							"\t3:\tTransfer funds\n" + 
							"\t4:\tView balance\n" + 
							"\t5:\tView personal information\n" + 
							"\t6:\tUpdate personal infromation\n" + 
							"\t7:\tClose account\n" + 
							"\t8:\tLogout");
						subMenuSelect = in.nextInt();
						
						//////////////////////////////////DEPOSIT//////////////////////////////////////
						
						if(subMenuSelect == 1) {
							System.out.println("\nHow much would you like to deposit?");
							depositAmt = in.nextDouble();
							if(act.deposit(depositAmt)) {
								System.out.printf("\nYou deposited $%.2f",depositAmt);
								System.out.printf("\nYour balance is now $%.2f",act.getBalance());
								System.out.println();
							}
							else {
								System.out.println("Your input is invalid. Please try again.");
							}
						}
						
						//////////////////////////////////WITHDRAW//////////////////////////////////////
						
						else if(subMenuSelect == 2) {
							System.out.println("\nHow much would you like to withdraw?");
							withdrawAmt = in.nextDouble();
							if(act.withdraw(withdrawAmt)) {
								System.out.printf("\nYou withdrew $%.2f",withdrawAmt);
								System.out.printf("\nYour balance is now $%.2f",act.getBalance());
								System.out.println();
							}
							else {
								System.out.println("\nYour input is invalid. Please try again.");
							}
						}
						
						//////////////////////////////////TRANSFER//////////////////////////////////////
						
						else if(subMenuSelect == 3) {
							System.out.println("\nWhat is the account number that you would like to transfer to?");
							transActNum = in.nextLong();
							transAct = new BankAccount(actdb.readAct(transActNum));
							System.out.println("\nHow much would you like to transfer to "+transAct.getUser().getFirst()+" "+transAct.getUser().getLast()+
									" ("+transActNum+")?");
							transferAmt = in.nextLong();
							if(act.transfer(transferAmt, transAct)) {
								System.out.printf("\nYou transfered $%.2f",transferAmt);
								System.out.println(" to "+transAct.getUser().getFirst()+" "+transAct.getUser().getLast()+
										" ("+transActNum+").");
								System.out.printf("Your balance is now $%.2f",act.getBalance());
								System.out.println();
							}
							else {
								System.out.println("\nYour input is invalid. Please try again.");
							}
						}
						
						//////////////////////////////////VIEW BALANCE//////////////////////////////////////
						
						else if(subMenuSelect == 4) {
							System.out.println("Your balance is $"+act.getBalance()+"\n");
						}
						
						//////////////////////////////////VIEW PERSONAL INFORMATION//////////////////////////////////////
						
						else if(subMenuSelect == 5) {
							System.out.println("Your personal information:\n"+act.getUser()+"\n");
						}
						
						//////////////////////////////////UPDATE PERSONAL INFORMATION//////////////////////////////////////
						
						else if(subMenuSelect == 6) {
							System.out.println("In pogress");
						}
						
						//////////////////////////////////CLOSE ACCOUNT//////////////////////////////////////
						
						else if(subMenuSelect == 7) {
							System.out.println("In pogress");
						}
						
						//////////////////////////////////LOGOUT//////////////////////////////////////
						
						else if(subMenuSelect == 8) {
							System.out.println("Are you sure you would like to logout?\n\t1:\tY\n\t2:\tN");
							logoutCheck = in.nextInt();
							if(logoutCheck == 1) {
								logout = true;
								///////reset account info for another log in///////
							}
						}
						
					//////////////////////////////////QUIT//////////////////////////////////////
						
					}while(logout == false);
				}
				
				//////////////////////////////////INCORRECT LOGIN//////////////////////////////////////
				
				else {
					System.out.print("\nYour account number or pin is incorrect. Try again.\n");
				}
				
				
			}
			
			//////////////////////////////////QUIT//////////////////////////////////////
			
			if(mainMenuSelect == 3) {
				System.out.println("Are you sure you would like to quit?\n\t1:\tY\n\t2:\tN");
				quitCheck = in.nextInt();
				if(quitCheck == 1) {
					quit = true;
				}
			}
			
		}while(quit == false);
		
		//////////////////////////////////EXIT//////////////////////////////////////
		
		System.out.println("\nThank you for using ATM. Have a great day!");
		in.close();
	}

}
