package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.MovieShowing;
import models.Showroom;
import models.Theatre;

public class MovieShowingDB {
	
	public static MovieShowing createMovieShowing(ResultSet rs) {
		MovieShowing showing = new MovieShowing();
		try {
			showing.setStartTime(rs.getDate("StartTime"));
			showing.setEndTime(rs.getDate("EndTime"));
			showing.setCost(rs.getDouble("Price"));
			showing.setNumTicketsSold(rs.getInt("NumTicketsSold"));
			
			String query = "SELECT * FROM Movie WHERE ID=" + rs.getInt("MovieId") + ";";
			Connection c = Database.getConnection();
			PreparedStatement s = Database.prepareStatement(c, query);
			ResultSet rs2 = s.executeQuery();
			if(rs2.next()) {
				showing.setMovie(MovieDB.createMovie(rs2));
			}
			query = "SELECT * FROM Showroom WHERE ID=" + rs.getInt("ShowroomId") + ";";
			s = Database.prepareStatement(c, query);
			rs2 = s.executeQuery();
			if(rs2.next()) {
				Showroom showroom = new Showroom();
				showroom.setName(rs2.getString("Name"));
				showroom.setCapacity(rs2.getInt("Capacity"));
				
				query = "SELECT * FROM Theatre WHERE ID=" + rs2.getInt("TheatreID") + ";";
				s = Database.prepareStatement(c, query);
				ResultSet rs3 = s.executeQuery();
				if(rs3.next()) {
					Theatre theatre = TheatresDB.createTheatre(rs3);
					showroom.setTheatre(theatre);
				}
				
				showing.setShowroom(showroom);
				rs3.close();
			}
			rs2.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return showing;
	}
	
	public static ArrayList<MovieShowing> getMovieShowings(int showroomId) {
		String query = "SELECT * FROM MovieShowings WHERE ShowroomId=" + showroomId + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
		try {
			ResultSet rs = s.executeQuery();
			while(rs.next())
			{
			    showings.add(createMovieShowing(rs));
			}
			rs.close();
			s.close();
			c.close();
			return showings;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addMovieShowing(MovieShowing movieShowing) {
		
		String query = "SELECT ID FROM Movie WHERE Name=" + movieShowing.getMovie().getName() + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int movieId = -1;
		try {
			ResultSet rs = s.executeQuery();
			movieId = rs.getInt("ID");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "SELECT ID FROM Showroom WHERE Name=" + movieShowing.getShowroom().getName() + ";";
		s = Database.prepareStatement(c, query);
		int showroomId = -1;
		try {
			ResultSet rs = s.executeQuery();
			showroomId = rs.getInt("ID");
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "INSERT INTO MovieShowing (Price, NumTicketsSold, StartTime, EndTime, MovieId, ShowroomId "
				     + "VALUES (" + movieShowing.getCost() + ", " + movieShowing.getNumTicketsSold() + ", " + movieShowing.getStartTime()
				     + ", " + movieShowing.getEndTime() + ", " + movieId + ", " + showroomId + ");";
		
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean updateMovieShowing(MovieShowing movieShowing) {
		
		String query = "SELECT ID FROM Movie WHERE Name=" + movieShowing.getMovie().getName() + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int movieId = -1;
		try {
			ResultSet rs = s.executeQuery();
			movieId = rs.getInt("ID");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "SELECT ID FROM Showroom WHERE Name=" + movieShowing.getShowroom().getName() + ";";
		s = Database.prepareStatement(c, query);
		int showroomId = -1;
		try {
			ResultSet rs = s.executeQuery();
			showroomId = rs.getInt("ID");
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "INSERT INTO MovieShowing (NumTicketsSold) VALUES (" + movieShowing.getNumTicketsSold() + ") "
			  + "WHERE MovieId=" + movieId + " AND ShowroomId=" + showroomId + ";";
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
}
