package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Showroom;

public class ShowroomDB {
	public static Showroom createShowroom(ResultSet rs) {
		try {
			Showroom showroom = new Showroom();
			showroom.setName(rs.getString("Name"));
			showroom.setCapacity(rs.getInt("Capacity"));
			int theatreId = rs.getInt("TheatreId");
			showroom.setTheatre(TheatreDB.getTheatreById(theatreId));
		    return showroom;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addShowroom(Showroom showroom) {
		int theatreId = TheatreDB.getTheatreIdByName(showroom.getTheatre().getName());
		String query = "INSERT INTO Showroom (Name, Capacity, TheatreId) " + 
						"VALUES (?, " + showroom.getCapacity() + ", " + theatreId + ");";
		ArrayList<String> params = new ArrayList<String>();
		params.add(showroom.getName());
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static int getShowroomIdByName(String name) {
		String query = "SELECT ID FROM Showroom WHERE Name='" + name + "';";
		int id = -1;
		try {
			PreparedStatement statement = Database.prepareStatement(query);
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
	
	public static Showroom getShowroomById(int id) {
		String query = "SELECT * FROM Showroom WHERE ID=" + id + ";";
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				Showroom showroom = createShowroom(rs);
				rs.close();
				statement.getConnection().close();
				statement.close();
			    return showroom;
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Showroom getShowroomByName(String name) {
		String query = "SELECT * FROM Showroom WHERE Name='" + name + "';";
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				Showroom showroom = createShowroom(rs);
				rs.close();
				statement.getConnection().close();
				statement.close();
			    return showroom;
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Showroom> getShowroomsByTheatreId(int theatreId) {
		String query = "SELECT * FROM Showroom WHERE TheatreId=" + theatreId + ";";
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			ArrayList<Showroom> showrooms = new ArrayList<Showroom>();
			while(rs.next()) {
				showrooms.add(createShowroom(rs));
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
			return showrooms;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too extensive for a project of this scope.
	public static boolean updateShowroomById(Showroom showroom, int id) {
		int theatreId = TheatreDB.getTheatreIdByName(showroom.getTheatre().getName());
		String query = "UPDATE Showroom SET Name=?, Capacity=" + showroom.getCapacity() + ", TheatreId=" + theatreId
						+ " WHERE ID=" + id + ";";
		ArrayList<String> params = new ArrayList<String>();
		params.add(showroom.getName());
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
}
