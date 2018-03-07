package data;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Address;
import models.User;

public class UserDB {
	
	//This method gets an address entry's id. As this is only used by this class, it has been placed here.
	public static int getAddressId(Address address) {
		String query = "SELECT ID FROM Address WHERE Address1=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		try {
			s.setString(1, address.getAddress1());
			ResultSet rs = s.executeQuery();
			if(rs.next())
			{
				int id = rs.getInt("ID");
				rs.close();
				s.close();
				c.close();
			    return id;
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
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
		String query = "SELECT ID FROM User WHERE EmailAddress=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(emailAddress);
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query, params);
		int id = -1;
		try {
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				id = rs.getInt("ID");
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static User getUserById(int id) {
		String query = "SELECT * FROM User WHERE ID=" + id + ";";
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				User user = createUser(rs);
				rs.close();
				statement.getConnection().close();
				statement.close();
			    return user;
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static User getUserByEmailAddress(String emailAddress) {
		String query = "SELECT * FROM User WHERE EmailAddress=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(emailAddress);
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query, params);
		try {
			ResultSet rs = statement.executeQuery();
			if(rs.next())
			{
				User user = createUser(rs);
				rs.close();
				statement.getConnection().close();
				statement.close();
			    return user;
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addUser(User user) {
		int addressId = AddressDB.getAddressIdByAddress1(user.getStreetAddress().getAddress1());
		
		// if the address is not already inserted, then insert it
		if (addressId == -1) {
			String query = "";
			Address address = user.getStreetAddress();
			ArrayList<String> params = new ArrayList<String>();
			if(address.getAddress2() == null || address.getAddress2().equals("")) {
				query = "INSERT INTO Address (Address1, City, StateAbbreviation, ZipCode) "
				      + "VALUES (?, ?, ?, ?);";
				params.add(address.getAddress1());
				params.add(address.getCity());
				params.add(address.getStateAbbreviation());
				params.add(address.getZipCode());
			} else {
				query = "INSERT INTO Address (Address1, Address2, City, StateAbbreviation, ZipCode) "
					  + "VALUES (?, ?, ?, ?, ?);";
				params.add(address.getAddress1());
				params.add(address.getAddress2());
				params.add(address.getCity());
				params.add(address.getStateAbbreviation());
				params.add(address.getZipCode());
			}
			int j = Database.runUpdate(query, params);
			if(j != 1) {
				return false;
			} else {
				addressId = AddressDB.getAddressIdByAddress1(user.getStreetAddress().getAddress1());
			}
			
		}
		
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
        try {
        	PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next())
			{
				rs.close();
				statement.getConnection().close();
				statement.close();
			    return true;
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}
}
