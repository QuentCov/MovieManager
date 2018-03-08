package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Address;

public class AddressDB {
	public static Address createAddress(ResultSet rs) {
		Address address = new Address();
		try {
			address.setAddress1(rs.getString("Address1"));
			address.setAddress2(rs.getString("Address2"));
			address.setCity(rs.getString("City"));
			address.setStateAbbreviation(rs.getString("StateAbbreviation"));
			address.setZipCode(rs.getString("ZipCode"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}
	
	public static int getAddressIdByAddress1(String address1) {
		String query = "SELECT ID FROM Address WHERE Address1=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(address1);
		Connection conn = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(conn, query, params);
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
	
	public static Address getAddressById(int id) {
		String query = "SELECT * FROM Address WHERE ID=" + id + ";";
		PreparedStatement statement = Database.prepareStatement(query);
		Address address = null;
		try {
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				address = createAddress(rs);
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}
	
	public static boolean addAddress(Address address) {
		String query = "INSERT INTO Address (Address1, Address2, City, StateAbbreviation, ZipCode) "
					 + "VALUES (?, ?, ?, ?, " + address.getZipCode() + ");";
		ArrayList<String> params = new ArrayList<String>();
		params.add(address.getAddress1());
		params.add(address.getAddress2());
		params.add(address.getCity());
		params.add(address.getStateAbbreviation());
		int i = Database.runUpdate(query, params);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here in updating all values in the row and have brainstormed solutions, 
	//	    but these solutions are too extensive for a project of this scope.
	public static boolean updateAddressById(Address address, int id) {
		String query = "UPDATE Address SET Address1=?, Address2=?, City=?, StateAbbreviation=?, ZipCode=" + address.getZipCode() + ", "
				     + "WHERE ID=" + id + ";";
		ArrayList<String> params = new ArrayList<String>();
		params.add(address.getAddress1());
		params.add(address.getAddress2());
		params.add(address.getCity());
		params.add(address.getStateAbbreviation());
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
}
