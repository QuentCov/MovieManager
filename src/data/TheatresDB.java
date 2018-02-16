package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Theatre;

public class TheatresDB {
	
	public static Theatre getTheatre() {
		String query = "SELECT * FROM theatreBuildings WHERE Theatrename=";
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
			    //TODO: make theatre
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Theatre getTheatre(Theatre theatre) {
		return getThreatre();
	}
	
	public static boolean addTheatre(Theatre theatre) {
		String query = "INSERT INTO theatres VALUES (NULL, ?, ?, ?)";
		//TODO: Add values I need from David.
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too intensive for a project of this scope.
	public static boolean updateTheatre(Theatre theatre) {
		String query = "UPDATE theatres SET Theatrename=?, Password=?, WHERE id=?";
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean deleteTheatre(String theatreName) {
		String query = "DELETE FROM theatres WHERE Theatrename=" + theatreName;
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
	    return false;
	}
	
	public static boolean validateTheatre(Theatre theatre) {
		return validateTheatre(theatre.getTheatreName(), theatre.getPassword());
	}
	
	public static boolean validateTheatre(String theatreName, String pass) {
		String query = "SELECT * FROM theatres WHERE Theatrename=" + theatreName + " AND Password=" + pass;
		ResultSet rs = Database.runQuery(query);
        try {
			if(rs.next())
			{
			    return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}
}
