package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Address;
import models.User;

public class UserDB {
	
	public static User createUser(ResultSet rs) {
		try {
		    User user = new User();
		    Address address = new Address();
		    
		    user.setEmailAddress(rs.getString("EmailAddress"));
		    user.setPassword(rs.getString("Password"));
		    user.setType(Integer.toString(rs.getInt("Type")));
		    user.setFullName(rs.getString("fullName"));
		    user.setPhoneNumber(rs.getString("PhoneNumber"));
		    
		    address.setAddress1(rs.getString("Address"));
		    address.setCity(rs.getString("City"));
		    address.setStateAbbreviation(rs.getString("State"));
		    address.setZipCode(rs.getString("ZipCode"));
		    
		    user.setStreetAddress(address);
		    return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static User getUser(int id) {
		String query = "SELECT * FROM users WHERE Id=" + id;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
				User user = createUser(rs);
				rs.close();
			    return user;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static User getUser(String userName) {
		String query = "SELECT * FROM users WHERE EmailAddress=" + userName;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
				User user = createUser(rs);
				rs.close();
			    return user;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addUser(User user) {
		Address address = user.getStreetAddress();
		String query = "INSERT INTO users (EmailAddress, Password, Type, FullName, PhoneNumber, Address, City, State, ZipCode)"
					 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		ArrayList<String> params = new ArrayList<String>();
		params.add(user.getEmailAddress());
		params.add(user.getPassword());
		params.add(user.getType());
		params.add(user.getFullName());
		params.add(user.getPhoneNumber());
		params.add(address.getAddress1());
		params.add(address.getCity());
		params.add(address.getStateAbbreviation());
		params.add(address.getZipCode());
		int i = Database.runUpdate(query, params);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too extensive for a project of this scope.
	public static boolean updateUser(User user) {
		Address address = user.getStreetAddress();
		String query = "UPDATE users SET Password=?, Type=?, FullName=?, PhoneNumber=?, Address=?, City=?, State=?, ZipCode=? "
				     + "WHERE EmailAddress=?";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(user.getPassword());
		params.add(user.getType());
		params.add(user.getFullName());
		params.add(user.getPhoneNumber());
		params.add(address.getAddress1());
		params.add(address.getCity());
		params.add(address.getStateAbbreviation());
		params.add(address.getZipCode());
		params.add(user.getEmailAddress());
		
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean validateUser(User user) {
		return validateUser(user.getEmailAddress());
	}
	
	public static boolean validateUser(String userName) {
		String query = "SELECT * FROM users WHERE EmailAddress=" + userName;
		ResultSet rs = Database.runQuery(query);
        try {
			if(rs.next())
			{
				rs.close();
			    return true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}
}
