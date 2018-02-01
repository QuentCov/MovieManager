package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Users {
	String userName;
	String password;
	String type;
	final static String TYPE_MOD = "_TYPE";
	
	public Users(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.type = "Customer";
	}
	
	public Users(String userName, String password, String type) {
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
	
	public static void registerUser(Users user, String path) {
		
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
	
	public static boolean isValidUser(Users user, String path) {
		
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
	
	public static String getUserType(Users user, String path) {
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
	
	public static void deleteUser(Users user, String path) {
		
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
