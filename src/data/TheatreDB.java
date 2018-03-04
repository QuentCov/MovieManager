package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Theatre;

public class TheatreDB {
	
	public static Theatre createTheatre(ResultSet rs) {
		Theatre theatre = new Theatre();
		try {
			theatre.setName(rs.getString("Name"));
			theatre.setAddress(AddressDB.getAddressById(rs.getInt("AddressId")));
			theatre.setShowrooms(ShowroomDB.getShowroomsByTheatreId(getTheatreIdByName(theatre.getName())));
			theatre.setOwner(UserDB.getUserById(rs.getInt("OwnerId")));
			return theatre;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getTheatreIdByName(String name) {
		String query = "SELECT ID FROM Theatre WHERE Name='" + name + "';";
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
	
	public static Theatre getTheatreByName(String name) {
		String query = "SELECT * FROM Theatre WHERE Name='" + name + "';";
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next()) {
				Theatre theatre = createTheatre(rs);
				rs.close();
				return theatre;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Theatre getTheatreById(int theatreId) {
		String query = "SELECT * FROM Theatre WHERE ID=" + theatreId + ";";
		ResultSet rs = Database.runQuery(query);
		Theatre theatre = new Theatre();
		try {
			if(rs.next()) {
				theatre = createTheatre(rs);
			}
			rs.close();
			return theatre;
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
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too intensive for a project of this scope.
	public static boolean updateTheatre(Theatre theatre) {
		int theatreId = getTheatreIdByName(theatre.getName());
		int addressId = AddressDB.getAddressIdByAddress1(theatre.getAddress().getAddress1());
		int ownerId = UserDB.getUserIdByEmailAddress(theatre.getOwner().getEmailAddress());
		String query = "UPDATE Theatre SET Name=?, AddressId=" + addressId + ", OwnerId=" + ownerId + ", "
						+ "WHERE ID=" + theatreId + ");";
		ArrayList<String> params = new ArrayList<String>();
		params.add(theatre.getName());
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
}
