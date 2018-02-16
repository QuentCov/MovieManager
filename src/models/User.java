package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class User {
	private String userName;
	private String password;
	private String type;
	private String emailAddress;
	private String fullName;
	private Address streetAddress;
	private String phoneNumber;

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.type = "Customer";
	}

	public User(String userName, String password, String type) {
		super();
		this.userName = userName;
		this.password = password;
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	// register a user in the system
	public static void registerUser(User user, String path) {
		Properties prop = new Properties();
		OutputStream output = null;
		FileInputStream fis = null;

		try {

			// load existing properties file
			fis = new FileInputStream(path);
			prop.load(fis);

			// set the properties value for username and user type
			prop.setProperty(user.getUserName(), user.getPassword());
			prop.setProperty(user.getUserName()+TYPE_MOD, user.getType());

			// save to properties file
			output = new FileOutputStream(path);
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// check if a user is already in the system
	public static boolean isValidUser(User user, String path) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		boolean isValid = false;

		try {

			// load existing properties file
			fis = new FileInputStream(path);
			prop.load(fis);

			// check if properties contains username and password
			if( (prop.containsKey(user.getUserName())) && (prop.containsValue(user.getPassword())) ) {
				isValid = true;
			}
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isValid;
	}

	// get the user type of the given user
	public static String getUserType(User user, String path) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		String type = "";

		try {

			// load existing properties file
			fis = new FileInputStream(path);
			prop.load(fis);

			// retrieve user's type
			type = prop.getProperty(user.getUserName()+TYPE_MOD);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return type;
	}

	// delete the user from the system
	public static void deleteUser(User user, String path) {
		Properties prop = new Properties();
		FileInputStream fis = null;
		OutputStream output = null;

		try {

			// load existing properties file
			fis = new FileInputStream(path);
			prop.load(fis);

			// remove username and user type
			prop.remove(user.getUserName());
			prop.remove(user.getUserName()+TYPE_MOD);

			// save to properties file
			output = new FileOutputStream(path);
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
