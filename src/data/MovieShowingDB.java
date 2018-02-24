package data;

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
			showing.setNumTicketsSold(rs.getInt("NumberPurchased"));
			String query = "SELECT * FROM movie WHERE Id=" + rs.getInt("movieId");
			ResultSet rs2 = Database.runQuery(query);
			if(rs2.next()) {
				showing.setMovie(MovieDB.createMovie(rs2));
			}
			query = "SELECT * FROM showrooms WHERE Id=" + rs.getInt("showroomId");
			rs2 = Database.runQuery(query);
			if(rs2.next()) {
				Showroom showroom = new Showroom();
				showroom.setName(rs2.getString("Name"));
				showroom.setCapacity(rs2.getInt("AvailableSeats"));
				
				query = "SELECT * FROM theatreBuildings WHERE Id=" + rs2.getInt("theatreBuilding");
				ResultSet rs3 = Database.runQuery(query);
				if(rs3.next()) {
					Theatre theatre = TheatresDB.createTheatre(rs3);
					showroom.setTheatre(theatre);
				}
				
				showing.setShowroom(showroom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return showing;
	}
	
	public static ArrayList<MovieShowing> getMovieShowings(int showroomId) {
		String query = "SELECT * FROM movieShowings WHERE showroomId=" + showroomId;
		ResultSet rs = Database.runQuery(query);
		ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
		try {
			while(rs.next())
			{
			    showings.add(createMovieShowing(rs));
			}
			return showings;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addMovieShowing(MovieShowing movieShowing) {
		String query = "UPDATE movieShowing SET Price=?, NumberPurchased=?, StartTime=?, EndTime=?, movieId=?, showroomId=? "
				     + "WHERE EmailAddress=?";
		
		ArrayList<String> params = new ArrayList<String>();
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean deleteMovieShowing(int id) {
		String query = "DELETE FROM movieShowings WHERE Id=" + id;
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
	    return false;
	}
}
