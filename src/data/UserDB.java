package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.User;

public class UserDB {
	
	public static User createUser(ResultSet rs) {
		try {
		    User user = new User();		    
		    user.setEmailAddress(rs.getString("EmailAddress"));
		    user.setPassword(rs.getString("Password"));
		    user.setType(rs.getString("Type"));
		    user.setFullName(rs.getString("FullName"));
		    user.setStreetAddress(AddressDB.getAddressById(rs.getInt("AddressId")));
		    user.setPhoneNumber(rs.getString("PhoneNumber"));
		    return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int getUserIdByEmailAddress(String emailAddress) {
		String query = "SELECT ID FROM User WHERE EmailAddress='" + emailAddress + "';";
		int id = -1;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next()) {
				id = rs.getInt("ID");
				rs.close();
			    return id;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static User getUserById(int id) {
		String query = "SELECT * FROM User WHERE ID=" + id + ";";
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next()) {
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
	
	public static User getUserByEmailAddress(String emailAddress) {
		String query = "SELECT * FROM User WHERE EmailAddress='" + emailAddress + "';";
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
		int addressId = AddressDB.getAddressIdByAddress1(user.getStreetAddress().getAddress1());
		String query = "INSERT INTO User (EmailAddress, Password, Type, FullName, PhoneNumber, AddressId)"
					 + "VALUES (?, ?, ?, ?, ?, " + addressId + ")";
		ArrayList<String> params = new ArrayList<String>();
		params.add(user.getEmailAddress());
		params.add(user.getPassword());
		params.add(user.getType());
		params.add(user.getFullName());
		params.add(user.getPhoneNumber());
		int i = Database.runUpdate(query, params);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too extensive for a project of this scope.
	public static boolean updateUser(User user) {
		int addressId = AddressDB.getAddressIdByAddress1(user.getStreetAddress().getAddress1());
		String query = "UPDATE User SET Password=?, Type=?, FullName=?, AddressId=" + addressId + ", PhoneNumber=? "
				     + "WHERE EmailAddress=?;";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(user.getPassword());
		params.add(user.getType());
		params.add(user.getFullName());
		params.add(user.getPhoneNumber());
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
	
	public static boolean validateUser(String userEmailAddress) {
		String query = "SELECT * FROM User WHERE EmailAddress='" + userEmailAddress + "';";
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
