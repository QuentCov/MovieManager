package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import models.Address;
import models.CreditCard;
import models.Movie;
import models.MovieShowing;
import models.Order;
import models.User;

public class OrdersDB {
	public static Order getOrder(UUID id) {
		String query = "SELECT * FROM Order o "
			     + "INNER JOIN CreditCard cc ON u.FullName=cc.CardHolderName "
			     + "INNER JOIN User u ON u.ID=o.CustomerId "
			     + "INNER JOIN OrdersMovies oi ON o.ID=oi.OrderId "
			     + "INNER JOIN MovieShowing ms ON ms.ID=oi.ShowingId "
			     + "INNER JOIN Movie m ON ms.movieId=m.ID "
			     + "INNER JOIN Address a ON o.BillingAddressId=a.ID "
			     + "WHERE OurID=" + id.toString() + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		Order order = new Order();
		try {
			ResultSet rs = s.executeQuery();
			if(rs.next())
			{
			    User customer = UserDB.createUser(rs);
			    CreditCard card = CreditCardsDB.createCard(rs);
			    
			    order.setCustomer(customer);
			    order.setDate(new Date());
			    order.setCreditCard(card);
			    order.setCost(rs.getInt("o.Cost"));
			    
			    //Get the billing address.
			    Address address = new Address();
			    
			    address.setAddress1(rs.getString("a.Address1"));
			    address.setAddress2(rs.getString("a.Address2"));
			    address.setCity(rs.getString("a.City"));
			    address.setStateAbbreviation(rs.getString("a.StateAbbreviation"));
			    address.setZipCode(Integer.toString(rs.getInt("a.ZipCode")));
			   
			    order.setShippingAddress(customer.getStreetAddress());
			    order.setBillingAddress(address);
			    
			    ArrayList<Integer> tickets = new ArrayList<Integer>();
			    ArrayList<Movie> movies = new ArrayList<Movie>();
			    tickets.add(rs.getInt("oi.NumTickets"));
			    movies.add(MovieDB.createMovie(rs));
			    
			    while(rs.next()) {
			    	tickets.add(rs.getInt("oi.NumTickets"));
			    	movies.add(MovieDB.createMovie(rs));
			    }
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return order;
	}
	
	public static ArrayList<Order> getOrders(String email) {
		String query = "SELECT * FROM User u "
				     + "INNER JOIN CreditCard cc ON cc.OwnerId=u.ID "
				     + "INNER JOIN Orders o ON o.CustomerId=u.ID "
				     + "INNER JOIN OrdersMovies oi ON oi.OrdersId=o.ID "
				     + "INNER JOIN MovieShowing ms ON oi.MovieId=ms.ID "
				     + "INNER JOIN Movie m ON m.ID=ms.movieId " 
				     + "INNER JOIN Address a ON a.ID=o.BillingAddressId "
				     + "WHERE EmailAddress=?";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			s.setString(1, email);
			ResultSet rs = s.executeQuery();
			while(rs.next())
			{
			    Order order = new Order();
			    User customer = UserDB.createUser(rs);
			    CreditCard card = CreditCardsDB.createCard(rs);
			    
			    order.setCustomer(customer);
			    order.setDate(new Date());
			    order.setCreditCard(card);
			    order.setCost(rs.getInt("o.Cost"));

			    //Get the billing address.
			    Address address = new Address();
			    
			    address.setAddress1(rs.getString("a.Address1"));
			    address.setAddress2(rs.getString("a.Address2"));
			    address.setCity(rs.getString("a.City"));
			    address.setStateAbbreviation(rs.getString("a.StateAbbreviation"));
			    address.setZipCode(Integer.toString(rs.getInt("a.ZipCode")));
			   
			    order.setShippingAddress(customer.getStreetAddress());
			    order.setBillingAddress(address);
			    
			    //Get the tickets and movie showings
			    ArrayList<Integer> tickets = new ArrayList<Integer>();
			    ArrayList<MovieShowing> movies = new ArrayList<MovieShowing>();
			    query = "SELECT * FROM Orders o "
			    	  + "INNER JOIN OrdersMovies om ON om.OrdersId=o.ID "
					  + "INNER JOIN MovieShowing ms ON ms.ID=om.MovieId "
					  + "WHERE o.ID=?";
			    
			    Connection c2 = Database.getConnection();
				PreparedStatement s2 = Database.prepareStatement(c2, query);
			    s2.setInt(1, rs.getInt("o.ID"));
			    
			    ResultSet rs2 = s2.executeQuery();
			    while(rs2.next()) {
			    	tickets.add(rs.getInt("NumTickets"));
				    movies.add(MovieShowingDB.createMovieShowing(rs2));
			    }
			    
			    s2.close();
			    rs2.close();
			
			    orders.add(order);
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static boolean addOrder(Order order) {
		
		String query = "SELECT ID FROM User WHERE EmailAddress=?;";
		
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int custId = -1;
		try {
			s.setString(1, order.getCustomer().getEmailAddress());
			ResultSet rs = s.executeQuery();
			custId = rs.getInt("ID");
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(custId == -1) {
			return false;
		}
		
		query = "INSERT INTO Orders (OurID, OrderDate, CustomerId, Cost, BillingAddress, CreditCardNumber)"
				 + " VALUES (" + order.getID() + ", " + new Date() + ", " + custId + ", " + order.getCost() + ", ?, ?);";
		ArrayList<String> params = new ArrayList<String>();
		params.add(order.getBillingAddress().getAddress1());
		params.add(order.getCreditCard().getCardNumber());
		
		int i = Database.runUpdate(query, params);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	public static boolean deleteOrder(Order order) {
		User owner = order.getCustomer();
		String query = "SELECT ID FROM User WHERE EmailAddress=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		ResultSet rs = null;
		int id = -1;
		try {
			s.setString(1, owner.getEmailAddress());
			rs = s.executeQuery();
			if(rs.next()) {
				id = rs.getInt("ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(id == -1) {
			return false;
		}
		
		query = "SELECT ID FROM Orders WHERE CustomerId=" + id + ";";
		
		try {
			rs = s.executeQuery();
			if(rs.next()) {
				id = rs.getInt("ID");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(id == -1) {
			try {
				rs.close();
				c.close();
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		query = "DELETE FROM OrdersMovies WHERE OrderId=" + id +";";
		int i = Database.runUpdate(query);
		if(i != 0 || i != -1) {
			query = "DELETE FROM Orders WHERE OurId=" + order.getID().toString() + ";";
			i = Database.runUpdate(query);
			
			if(i != 0 || i != -1) {
				try {
					rs.close();
					c.close();
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		try {
			rs.close();
			c.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean refundOrder(Order order, double cost, MovieShowing showing) {
		CreditCard card = order.getCreditCard();
		card.setBalance(card.getBalance() + cost);
		boolean updated = CreditCardsDB.updateBalance(card);
		
		if(updated) {
			order.setCost(order.getCost() - cost);
			int i = order.getMovies().indexOf(showing);
			Movie movie = order.getMovies().get(i).getMovie();
			int tickets = order.getTickets().get(i);
			order.getMovies().remove(i);
			order.getTickets().remove(i);
			
			updated = updateOrder(order);
			
			if(updated) {
				String query = "SELECT ID FROM Movie WHERE Name=" + movie.getName() + ";";
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
				
				query = "SELECT ID FROM Orders WHERE OurId=" + order.getID().toString() + ";";
				s = Database.prepareStatement(c, query);
				int orderId = -1;
				try {
					ResultSet rs = s.executeQuery();
					orderId = rs.getInt("ID");
					rs.close();
					s.close();
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				query = "INSERT INTO OrdersMovies (OrdersId, MovieId, NumTickets) "
							 + "VALUES (" + orderId + ", " + movieId + ", " + tickets + ");";
				i = Database.runUpdate(query);
				if(i == 1) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean updateOrder(Order order) {

		String query = "UPDATE Orders SET Cost=" + order.getCost()
					 + "WHERE OurId=" + order.getID().toString() + ";";
		int i = Database.runUpdate(query);
		if(i == 1) {
			return true;
		}
		return false;
	}

	public static boolean deleteOrderItem(Order order, MovieShowing showing) {
		
		int i = order.getMovies().indexOf(showing);
		order.getMovies().remove(showing);
		order.getTickets().remove(i);
		
		String query = "SELECT ID FROM Movie WHERE Name=" + showing.getMovie().getName() + ";";
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
		
		query = "SELECT ID FROM Showroom WHERE Name=" + showing.getShowroom().getName() + ";";
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
		
		query = "DELETE FROM OrdersMovies WHERE MovieId=" + movieId + " AND ShowingId=" + showroomId + "";
		int j = Database.runUpdate(query);
		if(j == 1) {
			return true;
		}
		return false;
	}
}
