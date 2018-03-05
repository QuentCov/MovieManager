package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			    User owner = UserDB.getUser(rs.getInt("Theatre.OwnerId"));
			    address.setAddress1(rs.getString("Address.Address1"));
			    address.setAddress2(rs.getString("Address.Address2"));
			    address.setCity(rs.getString("Address.City"));
			    address.setStateAbbreviation(rs.getString("Address.StateAbbreviation"));
			    address.setZipCode(rs.getString("Address.ZipCode"));
			    theatre.setName(rs.getString("Theatre.Name"));
			    theatre.setAddress(address);
			    theatre.setOwner(owner);
			    
			    ArrayList<Showroom> showrooms = new ArrayList<Showroom>();
				Showroom showroom = new Showroom();
				showroom.setName(rs.getString("Showroom.Name"));
				showroom.setCapacity(rs.getInt("Showroom.Capacity"));
				showroom.setTheatre(theatre);
				
				ArrayList<MovieShowing> showings = MovieShowingDB.getMovieShowings(rs.getInt("Showroom.ID"));
				showroom.setShowings(showings);
				showrooms.add(showroom);
				
				
				while(rs.next()) {
					showroom = new Showroom();
					showroom.setName(rs.getString("Showroom.Name"));
					showroom.setCapacity(rs.getInt("Showroom.Capacity"));
					showroom.setTheatre(theatre);
					
					showings = MovieShowingDB.getMovieShowings(rs.getInt("Showroom.ID"));
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
		String query = "SELECT Theatre.ownerId, Theatre.ID, Address.Address1, "
						    + "Address.Address2, Address.City, Address.State, Address.ZipCode, "
						    + "Theatre.Name, Showroom.ID, Showroom.Name, Showroom.Capacity, "
						    + "FROM Theatre t "
						    + "INNER JOIN Showroom ON Theatre.ID=Showroom.TheatreId "
						    + "INNER JOIN Address ON Address.ID=Theatre.AddressId "
						    + "WHERE Name=" + name + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		Theatre theatre = null;
		try {
			ResultSet rs = s.executeQuery();
			theatre = createTheatre(rs);
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theatre;
	}
	
	public static boolean addTheatre(Theatre theatre) {
		User owner = theatre.getOwner();
		
		String query = "SELECT ID, AddressId FROM User "
				     + "WHERE EmailAddress=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		
		int ownerId = -1;
		int addressId = -1;
		try {
			s.setString(1, owner.getEmailAddress());
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				ownerId = rs.getInt("ID");
				addressId = rs.getInt("AddressId");
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "INSERT INTO Theatre (Name, AddressId, ownerId) "
			  + "VALUES (?, " + addressId + ", " + ownerId + ");";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(theatre.getName());
		
		int i = Database.runUpdate(query, params);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	//TODO: Discuss this.
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too intensive for a project of this scope.
//	public static boolean updateTheatre(Theatre theatre) {
//		Address address = theatre.getAddress();
//		User owner = theatre.getOwner();
//		
//		String query = "SELECT ID from User where EmailAddress=" + owner.getEmailAddress() + ";";
//		Connection c = Database.getConnection();
//		PreparedStatement s = Database.prepareStatement(c, query);
//		int ownerId = -1;
//		try {
//			ResultSet rs = s.executeQuery();
//			if(rs.next()) {
//				ownerId = rs.getInt("ID");
//			}
//			rs.close();
//			s.close();
//			c.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		query = "UPDATE Theatre SET Address=?, ownerId=" + ownerId + ", City=?, State=?, ZipCode=? WHERE Name=?);";
//		
//		ArrayList<String> params = new ArrayList<String>();
//		params.add(address.getAddress1());
//		params.add(address.getCity());
//		params.add(address.getStateAbbreviation());
//		params.add(address.getZipCode());
//		params.add(theatre.getName());
//		
//		int i = Database.runUpdate(query, params);
//	    if(i == 1) {
//	    	return true;
//	    }
//	    return false;
//	}
}
