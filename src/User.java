/**
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.util.Scanner;

public class User {
	Scanner in = new Scanner(System.in);

	private String first;
	private String last;
	private String dob;
	private String street;
	private String city;
	private String state;
	private String pin;
	private String postalCode;
	private long phoneNum;
	public User(String pin, String first, String last, String dob, String street, String city, String state, String postalCode, long phoneNum) {
		this.first = first;
		this.last = last;
		this.dob = dob;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.phoneNum = phoneNum;
		this.pin = pin;
	}

	//getters
	public String getPin() {
		return pin;
	}
	public String getFirst() {
		return first;
	}
	public String getLast() {
		return last;
	}
	public String getDob() {
		return dob;
	}
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public long getPhoneNum() {
		return phoneNum;
	}
	//setters
	public boolean setPin(String pin) {
		System.out.println("Please enter your current pin:");
		String currentPin = in.nextLine();
		if(currentPin.equals(this.pin)) {
			this.pin = pin;
			return true;
		}
		else {
			return false;
		}
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
}
