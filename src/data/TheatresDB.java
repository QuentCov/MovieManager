package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Theatre;

public class TheatresDB {
	
	public static Theatre getTheatre(String name) {
		String query = "SELECT * FROM theatreBuildings WHERE Name=" + name;
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
		return getTheatre(theatre.getName());
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
}
