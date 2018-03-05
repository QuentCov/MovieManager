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
		String query = "SELECT * FROM Order o WHERE OurID=" + id.toString() + " "
			     + "INNER JOIN CreditCard cc ON u.FullName=cc.CardHolderName "
			     + "INNER JOIN User u ON u.ID=o.CustomerId "
			     + "INNER JOIN OrdersMovies oi ON o.ID=oi.OrderId "
			     + "INNER JOIN MovieShowing ms ON ms.ID=oi.ShowingId "
			     + "INNER JOIN Movie m ON ms.movieId=m.ID "
			     + "INNER JOIN Address a ON o.BillingAddressId=a.ID;";
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
			    order.setCost(rs.getInt("o.TotalCost"));
			    
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
		String query = "SELECT * FROM User u WHERE EmailAddress=? "
				     + "INNER JOIN CreditCard cc ON u.FullName=cc.CardHolderName "
				     + "INNER JOIN Order o ON u.Id=o.CustomerId "
				     + "INNER JOIN OrdersMovies oi ON o.Id=oi.OrderId "
				     + "INNER JOIN MovieShowing ms ON ms.Id=oi.ShowingId "
				     + "INNER JOIN Movie m ON ms.movieId=m.Id " 
				     + "INNER JOIN Address a ON o.BillingAddressId=a.ID;";
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
			    order.setCost(rs.getInt("o.TotalCost"));

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
		
		query = "INSERT INTO Order (OurID, OrderDate, CustomerId, TotalCost, BillingAddress, CreditCardNumber)"
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
		
		query = "SELECT ID FROM Order WHERE CustomerId=" + id + ";";
		
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
			query = "DELETE FROM Order WHERE OurId=" + order.getID().toString() + ";";
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
				
				query = "SELECT ID FROM Order WHERE OurId=" + order.getID().toString() + ";";
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

		String query = "UPDATE Order SET Cost=" + order.getCost()
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
