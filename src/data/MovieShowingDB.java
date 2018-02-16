package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.MovieShowing;

public class MovieShowingDB {
	public static MovieShowing getMovieShowing(String name) {
		String query = "SELECT * FROM movieShowings WHERE Name=" + name;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
			    //TODO: make movieShowing
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static MovieShowing getMovieShowing(MovieShowing movieShowing) {
		return getMovieShowing(movieShowing.getMovie().getName());
	}
	
	public static boolean addMovieShowing(MovieShowing movieShowing) {
		String query = "INSERT INTO movieShowings VALUES (NULL, ?, ?, ?)";
		//TODO: Add values I need from David.
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too intensive for a project of this scope.
	public static boolean updateMovieShowing(MovieShowing movieShowing) {
		String query = "UPDATE movieShowings SET MovieShowingname=?, Password=?, WHERE id=?";
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean deleteMovieShowing(String movieShowingName) {
		String query = "DELETE FROM movieShowings WHERE MovieShowingname=" + movieShowingName;
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
	    return false;
	}
}
