package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class User {
	private String emailAddress;
	private String password;
	private String type;
	private String fullName;
	private Address streetAddress;
	private String phoneNumber;

	public User(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
		this.type = "Customer";
	}

	public User(String emailAddress, String password, String type) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Address getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(Address streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// checks if the given phone number is in the correct format
	public boolean isValidPhoneNumberFormat() {
        String pattern = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";
        return this.phoneNumber.matches(pattern);
	}

	// checks if the given email address is valid
	public boolean isValidEmailAddress() {
        String pattern = "\\w+@\\w+.\\w";
        return this.emailAddress.matches(pattern);
	}
}
