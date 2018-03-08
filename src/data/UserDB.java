package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
		    Address address = new Address();
		    
		    user.setEmailAddress(rs.getString("EmailAddress"));
		    user.setPassword(rs.getString("Password"));
		    user.setType(rs.getString("Type"));
		    user.setFullName(rs.getString("FullName"));
		    user.setPhoneNumber(rs.getString("PhoneNumber"));
		    
		    address.setAddress1(rs.getString("Address.Address1"));
		    String address2 = rs.getString("Address.Address2");
		    if (address2 == null) {
		    	address2 = "";
		    }
		    address.setAddress2(address2);
		    address.setCity(rs.getString("Address.City"));
		    address.setStateAbbreviation(rs.getString("Address.StateAbbreviation"));
		    address.setZipCode(rs.getString("Address.ZipCode"));
		    
		    user.setStreetAddress(address);
		    return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static User getUser(int id) {
		String query = "SELECT * FROM User "
				     + "INNER JOIN Address ON Address.ID=User.AddressId "
				     + "WHERE User.ID=" + id + ";";
		
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		try {
			ResultSet rs = s.executeQuery();
			if(rs.next())
			{
				User user = createUser(rs);
				rs.close();
				s.close();
				c.close();
			    return user;
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static User getUser(String userName) {
		//Because email addresses use @ symbols, we cannot use in line queries.
		String query = "SELECT * FROM User "
					 + "INNER JOIN Address ON Address.ID=User.AddressId "
					 + "WHERE EmailAddress=?";
		
		Connection c = Database.getConnection();
		ArrayList<String> params = new ArrayList<String>();
		params.add(userName);
		PreparedStatement s = Database.prepareStatement(c, query, params);
		try {
			ResultSet rs = s.executeQuery();
			if(rs.next())
			{
				User user = createUser(rs);
				rs.close();
				s.close();
				c.close();
			    return user;
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addUser(User user) {
		int addressId = -1;
		addressId = getAddressId(user.getStreetAddress());
		Address address = user.getStreetAddress();
		
		//The address is new, insert the new value.
		if (addressId == -1) {
			String query = "";
			ArrayList<String> params = new ArrayList<String>();
			if(address.getAddress2() == null || address.getAddress2().equals("")) {
				query = "INSERT INTO Address (Address1, City, StateAbbreviation, ZipCode) "
				      + "VALUES (?, ?, ?, ?)";
				params.add(address.getAddress1());
				params.add(address.getCity());
				params.add(address.getStateAbbreviation());
				params.add(address.getZipCode());
			} else {
				query = "INSERT INTO Address (Address1, Address2, City, StateAbbreviation, ZipCode) "
					  + "VALUES (?, ?, ?, ?, ?)";
				params.add(address.getAddress1());
				params.add(address.getAddress2());
				params.add(address.getCity());
				params.add(address.getStateAbbreviation());
				params.add(address.getZipCode());
			}
			int j = Database.runUpdate(query, params);
			if(j != 1) {
				return false;
			}
			
			query = "SELECT ID FROM Address WHERE Address1=?";
			Connection c = Database.getConnection();
			PreparedStatement s = Database.prepareStatement(c, query);
			try {
				s.setString(1, address.getAddress1());
				ResultSet rs = s.executeQuery();
				if(rs.next())
				{
					addressId = rs.getInt("ID");
				}
				rs.close();
				s.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		String query = "INSERT INTO User (EmailAddress, Password, Type, FullName, PhoneNumber, AddressId)"
					 + " VALUES (?, ?, ?, ?, ?, " + addressId + ");";
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
		int addressId = getAddressId(user.getStreetAddress());
				
		String query = "UPDATE User SET Password=?, Type=?, FullName=?, PhoneNumber=?, AddressId=" + addressId + " "
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
	
	public static boolean validateUser(String userName) {
		String query = "SELECT * FROM User WHERE EmailAddress=?;";
		Connection c = Database.getConnection();
		ArrayList<String> params = new ArrayList<String>();
		params.add(userName);
		PreparedStatement s = Database.prepareStatement(c, query, params);
        try {
        	ResultSet rs = s.executeQuery();
			if(rs.next())
			{
				rs.close();
				s.close();
				c.close();
			    return true;
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}

	public static int getID(User user) {
		String query = "SELECT ID FROM User WHERE EmailAddress=?";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int ownerId = -1;
		try {
			s.setString(1, user.getEmailAddress());
			ResultSet rs = s.executeQuery();
			if(rs.next())
			{
				ownerId = rs.getInt("ID");
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ownerId;
	}
}
