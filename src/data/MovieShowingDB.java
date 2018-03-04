package data;

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
	
	public static ArrayList<MovieShowing> getMovieShowingsByShowroomId(int showroomId) {
		String query = "SELECT * FROM MovieShowing WHERE ShowroomId=" + showroomId + ";";
		ResultSet rs = Database.runQuery(query);
		ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
		try {
			while(rs.next())
			{
			    showings.add(createMovieShowing(rs));
			}
			rs.close();
			return showings;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<MovieShowing> getMovieShowingsByMovieId(int movieId) {
		String query = "SELECT * FROM MovieShowing WHERE MovieId=" + movieId + ";";
		ResultSet rs = Database.runQuery(query);
		ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
		try {
			while(rs.next())
			{
			    showings.add(createMovieShowing(rs));
			}
			rs.close();
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
}
