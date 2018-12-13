/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */
import java.util.Scanner;

public class BankAccount {
	Scanner in = new Scanner(System.in);
	private long actNum;
	private double balance;
	private User user;
	
	public BankAccount(String act) {
		this.user = new User(
					removeSpace(act.substring(9,13)), //pin
					removeSpace(act.substring(48,63)), //first
					removeSpace(act.substring(28,48)), //last
					removeSpace(act.substring(63,71)), //dob
					removeSpace(act.substring(81,111)), //street
					removeSpace(act.substring(111,141)), //city
					removeSpace(act.substring(141,143)), //state
					removeSpace(act.substring(143,148)), //postalcode
					Long.valueOf(act.substring(71,81))); // phonenum
		this.actNum = Long.valueOf(act.substring(0,8));
		this.balance = Double.valueOf(act.substring(13,28));
	}
	//getters
	public String removeSpace(String str) 
    { 
        str = str.replaceAll("\\s",""); 
        return str; 
    } 
	public long getActNum() {
		return actNum;
	}
	public double getBalance() {
		return balance;
	}
	public User getUser() {
		return user;
	}
	//setters
	public void setActNum(long actNum) {
		this.actNum = actNum;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//instance methods
	public boolean deposit(double amount) {
		if (amount <= 0) {
			return false;
		}
		else {
			balance = balance + amount;
			return true;
		}
	}
	public boolean withdraw(double amount) {
		if (amount > balance) {
			return false;
		}
		else if (amount <= 0) {
			return false;
		}
		else {
			balance = balance - amount;
			return true;
		}
	}
	public boolean transfer(double amount, BankAccount transferAct) {
			if (amount > balance) {
				return false;
			}
			else if (amount <= 0) {
				return false;
			}
			else {
				balance -= amount;
				transferAct.balance += amount;
				return true;
			}
		}
}
