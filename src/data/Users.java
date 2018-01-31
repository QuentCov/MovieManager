package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Users {
	String userName;
	String password;
	
	public Users(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
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
	
	public static void registerUser(Users user, String path) {
		
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			
			output = new FileOutputStream(path);
			
			// set the properties value
			prop.setProperty(user.getUserName(), user.getPassword());

			// save properties to project root folder
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
	
	public static boolean validateUser(Users user, String propFilePath) {
		
		Properties prop = new Properties();
		FileInputStream fis = null;

		boolean isValid = false;
		try {
			
			fis = new FileInputStream(propFilePath);
			prop.load(fis);
			
			// check if properties contains username and password
			if( (prop.containsKey(user.getUserName())) && (prop.containsKey(user.getPassword())) ) {
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
	
	public static void deleteUser(Users user, String propFilePath) {
		
		Properties prop = new Properties();
		FileInputStream fis = null;

		try {
			
			fis = new FileInputStream(propFilePath);
			prop.load(fis);
			
			// check if properties contains username and password
			prop.remove(user.getUserName());
			
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
