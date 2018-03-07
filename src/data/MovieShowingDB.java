package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.MovieShowing;
import models.Showroom;
import models.Theatre;

public class MovieShowingDB {
	
	public static MovieShowing createMovieShowing(ResultSet rs) {
		MovieShowing showing = new MovieShowing();
		try {
			
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
			Date sDate = null;
			Date eDate = null;
			try {
				sDate = sdfmt1.parse(rs.getString("StartTime"));
				eDate = sdfmt1.parse(rs.getString("EndTime"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			showing.setStartTime(sDate);
			showing.setEndTime(eDate);
			showing.setCost(rs.getDouble("Cost"));
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
				
				query = "SELECT * FROM Theatre "
					  + "INNER JOIN Address on Theatre.AddressId=Address.ID "
					  + "WHERE Theatre.ID=?;";
				s = Database.prepareStatement(c, query);
				s.setInt(1, rs2.getInt("TheatreID"));
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
	
	public static MovieShowing getMovieShowing(int id) {
		String query = "SELECT * FROM MovieShowing WHERE ShowroomId=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		MovieShowing showing = new MovieShowing();
		try {
			s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			while(rs.next())
			{
			    showing = createMovieShowing(rs);
			}
			rs.close();
			s.close();
			c.close();
			return showing;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<MovieShowing> getMovieShowings(int showroomId) {
		String query = "SELECT * FROM MovieShowing WHERE ShowroomId=" + showroomId + ";";
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
