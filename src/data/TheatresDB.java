package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Address;
import models.MovieShowing;
import models.Theatre;
import models.User;
import models.Showroom;

public class TheatresDB {
	
	public static Theatre createTheatre(ResultSet rs) {
		Theatre theatre = new Theatre();
		try {
			if(rs.next())
			{
			    Address address = new Address();
			    User owner = UserDB.getUser(rs.getInt("theatreBuildings.ownerId"));
			    address.setAddress1(rs.getString("theatreBuildings.Address"));
			    address.setCity(rs.getString("theatreBuildings.City"));
			    address.setStateAbbreviation(rs.getString("theatreBuildings.State"));
			    address.setZipCode(rs.getString("theatreBuildings.ZipCode"));
			    theatre.setName(rs.getString("theatreBuildings.Name"));
			    theatre.setAddress(address);
			    theatre.setOwner(owner);
			    
			    ArrayList<Showroom> showrooms = new ArrayList<Showroom>();
				Showroom showroom = new Showroom();
				showroom.setName(rs.getString("showrooms.Name"));
				showroom.setCapacity(rs.getInt("showrooms.AvailableSeats"));
				showroom.setTheatre(theatre);
				
				ArrayList<MovieShowing> showings = MovieShowingDB.getMovieShowings(rs.getInt("showrooms.Id"));
				showroom.setShowings(showings);
				showrooms.add(showroom);
				
				
				while(rs.next()) {
					showroom = new Showroom();
					showroom.setName(rs.getString("showrooms.Name"));
					showroom.setCapacity(rs.getInt("showrooms.AvailableSeats"));
					showroom.setTheatre(theatre);
					
					showings = MovieShowingDB.getMovieShowings(rs.getInt("showrooms.Id"));
					showroom.setShowings(showings);
					showrooms.add(showroom);
				}
				
				theatre.setShowrooms(showrooms);
				return theatre;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Theatre getTheatre(String name) {
		String query = "SELECT theatreBuildings.ownerId, theatreBuildings.Id, theatreBuildings.Address, "
						    + "theatreBuildings.City, theatreBuildings.State, theatreBuildings.ZipCode,"
						    + "theatreBuildings.Name, showrooms.Id, showrooms.Name, showrooms.AvailableSeats,"
						    + "FROM theatreBuildings t WHERE Name=" + name 
						    + "INNER JOIN showrooms ON theatreBuildings.Id=showrooms.theatreId";
		ResultSet rs = Database.runQuery(query);
		return createTheatre(rs);
	}
	
	public static boolean addTheatre(Theatre theatre) {
		Address address = theatre.getAddress();
		User owner = theatre.getOwner();
		
		String query = "SELECT Id from users where EmailAddress=" + owner.getEmailAddress();
		ResultSet rs = Database.runQuery(query);
		int ownerId = -1;
		try {
			if(rs.next()) {
				ownerId = rs.getInt("Id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "INSERT INTO users (Name, Address, ownerId, City, State, ZipCode)"
			  + "VALUES (?, ?,"+ ownerId +", ?, ?, ?)";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(theatre.getName());
		params.add(address.getAddress1());
		params.add(address.getCity());
		params.add(address.getStateAbbreviation());
		params.add(address.getZipCode());
		
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too intensive for a project of this scope.
	public static boolean updateTheatre(Theatre theatre) {
		Address address = theatre.getAddress();
		User owner = theatre.getOwner();
		
		String query = "SELECT Id from users where EmailAddress=" + owner.getEmailAddress();
		ResultSet rs = Database.runQuery(query);
		int ownerId = -1;
		try {
			if(rs.next()) {
				ownerId = rs.getInt("Id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "UPDATE theatres SET Address=?, ownerId=" + ownerId + ", City=?, State=?, ZipCode=? WHERE Name=?)";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(address.getAddress1());
		params.add(address.getCity());
		params.add(address.getStateAbbreviation());
		params.add(address.getZipCode());
		params.add(theatre.getName());
		
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean deleteTheatre(String theatreName) {
		String query = "DELETE FROM theatres WHERE Name=" + theatreName;
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
	    return false;
	}
}
