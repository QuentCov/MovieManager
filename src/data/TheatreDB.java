package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

import models.Theatre;

public class TheatreDB {
	
	public static Theatre createTheatre(ResultSet rs) {
		Theatre theatre = new Theatre();
		try {
			theatre.setName(rs.getString("Name"));
			theatre.setAddress(AddressDB.getAddressById(rs.getInt("AddressId")));
			theatre.setOwner(UserDB.getUserById(rs.getInt("OwnerId")));
			return theatre;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getTheatreIdByName(String name) {
		String query = "SELECT ID FROM Theatre WHERE Name=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(name);
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
	
	public static Theatre getTheatreByName(String name) {
		String query = "SELECT * FROM Theatre WHERE Name=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(name);
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query, params);
		try {
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				Theatre theatre = createTheatre(rs);
				rs.close();
				statement.getConnection().close();
				statement.close();
				return theatre;
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Theatre getTheatreById(int theatreId) {
		String query = "SELECT * FROM Theatre WHERE ID=" + theatreId + ";";
		PreparedStatement statement = Database.prepareStatement(query);
		Theatre theatre = new Theatre();
		try {
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				theatre = createTheatre(rs);
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
			return theatre;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Theatre> getTheatresByOwnerId(int ownerId) {
		String query = "SELECT * FROM Theatre WHERE OwnerId=" + ownerId + ";";
		PreparedStatement statement = Database.prepareStatement(query);
		try {
			ResultSet rs = statement.executeQuery();
			ArrayList<Theatre> theatres = new ArrayList<Theatre>();
			while(rs.next()) {
				theatres.add(createTheatre(rs));
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
			return theatres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addTheatre(Theatre theatre) {
		int addressId = AddressDB.getAddressIdByAddress1(theatre.getAddress().getAddress1());
		int ownerId = UserDB.getUserIdByEmailAddress(theatre.getOwner().getEmailAddress());
		String query = "INSERT INTO Theatre (Name, AddressId, OwnerId) "
						+ "VALUES (?, " + addressId + ", " + ownerId + ");";
		ArrayList<String> params = new ArrayList<String>();
		params.add(theatre.getName());
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
}
