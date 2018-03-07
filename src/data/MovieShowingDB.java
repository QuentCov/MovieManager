package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.Movie;
import models.MovieShowing;
import models.Showroom;

public class MovieShowingDB {
	
	public static MovieShowing createMovieShowing(ResultSet rs) {
		MovieShowing showing = new MovieShowing();
		try {
			Movie movie = MovieDB.getMovieById(rs.getInt("MovieId"));
			Showroom showroom = ShowroomDB.getShowroomById(rs.getInt("ShowroomId"));
			showing.setId(rs.getInt("ID"));
			showing.setMovie(movie);
			showing.setShowroom(showroom);
			
			String startTimeString = rs.getString("StartTime");
			String endTimeString = rs.getString("EndTime");
			DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			Date startTimeDate = format.parse(startTimeString);
			Date endTimeDate = format.parse(endTimeString);
			showing.setStartTime(startTimeDate);
			showing.setEndTime(endTimeDate);
			
			showing.setNumTicketsSold(rs.getInt("NumTicketsSold"));
			showing.setCost(rs.getDouble("Cost"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return showing;
	}
	
	public static MovieShowing getMovieShowingById(int id) {
		String query = "SELECT * FROM MovieShowing WHERE ID=" + id + ";";
		MovieShowing showing = null;
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				showing = createMovieShowing(rs);
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
			return showing;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getTicketsSoldByMovieId(int movieId) {
		String query = "SELECT NumTicketsSold FROM MovieShowing WHERE MovieId=" + movieId + ";";
		int ticketsSold = 0;
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ticketsSold = rs.getInt("NumTicketsSold");
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
			return ticketsSold;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketsSold;
	}
	
	public static ArrayList<MovieShowing> getMovieShowingsByShowroomId(int showroomId) {
		String query = "SELECT * FROM MovieShowing WHERE ShowroomId=" + showroomId + ";";
		ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
			    showings.add(createMovieShowing(rs));
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
			return showings;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<MovieShowing> getMovieShowingsByMovieId(int movieId) {
		String query = "SELECT * FROM MovieShowing WHERE MovieId=" + movieId + ";";
		ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
			    showings.add(createMovieShowing(rs));
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
			return showings;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addMovieShowing(MovieShowing movieShowing) {
		int movieId = MovieDB.getMovieIdByName(movieShowing.getMovie().getName());
		int showroomId = ShowroomDB.getShowroomIdByName(movieShowing.getShowroom().getName());
		String query = "INSERT INTO MovieShowing (MovieId, ShowroomId, StartTime, EndTime, NumTicketsSold, Cost) " + 
						"VALUES (" + movieId + ", " + showroomId + ", ?, ?, " + 
									movieShowing.getNumTicketsSold() + ", " + movieShowing.getCost() + ");";
		ArrayList<String> params = new ArrayList<String>();
		params.add(movieShowing.getStartTime().toString());
		params.add(movieShowing.getEndTime().toString());
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean deleteMovieShowing(int id) {
		String query = "DELETE FROM MovieShowing "
							+ " WHERE ID=" + id + ";";
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too extensive for a project of this scope.
	public static boolean updateMovieShowingByID(MovieShowing movieShowing, int id) {
		int movieId = MovieDB.getMovieIdByName(movieShowing.getMovie().getName());
		int showroomId = ShowroomDB.getShowroomIdByName(movieShowing.getShowroom().getName());
		
		String query = "UPDATE MovieShowing SET MovieId=" + movieId + ", ShowroomId=" + showroomId + ", StartTime=?, EndTime=?, "
							+ "NumTicketsSold=" + movieShowing.getNumTicketsSold() + ", Cost=" + movieShowing.getCost()
							+ " WHERE ID=" + id + ";";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(movieShowing.getStartTime().toString());
		params.add(movieShowing.getEndTime().toString());
		int i = Database.runUpdate(query, params);
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
