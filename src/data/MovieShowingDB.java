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
import models.Theatre;

public class MovieShowingDB {
	
	public static MovieShowing createMovieShowing(ResultSet rs) {
		MovieShowing showing = new MovieShowing();
		try {
			showing.setID(rs.getInt("MovieShowing.ID"));
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
	
	public static int getTicketsSoldByMovieId(int movieId) {
		String query = "SELECT NumTicketsSold FROM MovieShowing WHERE MovieId=" + movieId + ";";
		int ticketsSold = 0;
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ticketsSold += rs.getInt("NumTicketsSold");
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
	
	public static MovieShowing getMovieShowingById(int id) {
		String query = "SELECT * FROM MovieShowing WHERE ID=" + id + ";";
		MovieShowing showing = null;
		try {
			PreparedStatement statement = Database.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				showing = createMovieShowing(rs);
			}
			return showing;
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
	
	public static ArrayList<MovieShowing> getMovieShowings(int showroomId) {
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
	
	public static boolean updateMovieShowingByID(MovieShowing movieShowing, int id) {
		String query = "UPDATE MovieShowing SET NumTicketsSold=" + movieShowing.getNumTicketsSold() + " WHERE ID=" + id + ";";
		
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean updateMovieShowing(MovieShowing movieShowing) {
		String query = "SELECT ID FROM Movie WHERE Name=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int movieId = -1;
		try {
			s.setString(1, movieShowing.getMovie().getName());
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				movieId = rs.getInt("ID");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(movieId == -1) {
			return false;
		}
		
		query = "SELECT ID FROM Showroom WHERE Name=?;";
		s = Database.prepareStatement(c, query);
		int showroomId = -1;
		try {
			s.setString(1, movieShowing.getShowroom().getName());
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				showroomId = rs.getInt("ID");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "UPDATE MovieShowing SET NumTicketsSold=? WHERE MovieId=? AND ShowroomId=?;";
		s = Database.prepareStatement(c, query);
		int i = -1;
		try {
			s.setInt(1, movieShowing.getNumTicketsSold());
			s.setInt(2, movieId);
			s.setInt(3, showroomId);
			i = s.executeUpdate();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    if(i != -1) {
	    	return true;
	    }
	    return false;
	}

	public static ArrayList<MovieShowing> getAllShowings() {
		String query = "SELECT * FROM MovieShowing;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
		try {
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				showings.add(createMovieShowing(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return showings;
	}

	public static ArrayList<MovieShowing> getMovieShowingsByTheatre(Theatre theatre) {
		String query = "SELECT * FROM Theatre t "
				     + "INNER JOIN Showroom s ON s.TheatreId=t.ID "
				     + "INNER JOIN MovieShowing ON MovieShowing.ShowroomId=s.ID "
				     + "WHERE t.Name=?;";
		
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
		
		try {
			s.setString(1, theatre.getName());
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				showings.add(createMovieShowing(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return showings;
	}
}
