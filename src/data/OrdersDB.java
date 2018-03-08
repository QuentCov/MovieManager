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
		String query = "SELECT * FROM Orders o "
			     + "INNER JOIN User u ON u.ID=o.CustomerId "
			     + "INNER JOIN CreditCard cc ON u.ID=cc.OwnerId "
			     + "INNER JOIN Address ON o.BillingAddressId=Address.ID "
			     + "WHERE OurId=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		Order order = new Order();
		try {
			s.setString(1, id.toString());
			ResultSet rs = s.executeQuery();
			if(rs.next())
			{
			    User customer = UserDB.createUser(rs);
			    CreditCard card = CreditCardsDB.createCard(rs);
			    
			    order.setID(id);
			    order.setCustomer(customer);
			    order.setDate(rs.getString("o.Date"));
			    order.setCreditCard(card);
			    order.setCost(rs.getInt("o.Cost"));
			    order.setShippingAddress(customer.getStreetAddress());
			    
			    //Get the billing address.
			    Address address = new Address();
			    
			    address.setAddress1(rs.getString("Address.Address1"));
			    address.setAddress2(rs.getString("Address.Address2"));
			    address.setCity(rs.getString("Address.City"));
			    address.setStateAbbreviation(rs.getString("Address.StateAbbreviation"));
			    address.setZipCode(Integer.toString(rs.getInt("Address.ZipCode")));
			   
			    order.setShippingAddress(customer.getStreetAddress());
			    order.setBillingAddress(address);
			    
			    //OrdersMovies contains the MovieShowing and the ticket count that is being ordered.
			    ArrayList<Integer> tickets = new ArrayList<Integer>();
			    ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
			    
			    query = "SELECT * FROM OrdersMovies "
			    	  + "INNER JOIN MovieShowing ON OrdersMovies.MovieShowingsId=MovieShowing.ID "
			    	  + "WHERE OrdersId=?;";
			    
			    PreparedStatement s2 = Database.prepareStatement(c, query);
			    s2.setInt(1, rs.getInt("o.ID"));
			    
			    ResultSet rs2 = s2.executeQuery();
			    while(rs2.next()) {
			    	tickets.add(rs2.getInt("NumTickets"));
			    	showings.add(MovieShowingDB.createMovieShowing(rs2));
			    }
			    
			    order.setTickets(tickets);
			    order.setShowings(showings);
			    rs2.close();
			    s2.close();
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
		String query = "SELECT o.OurId FROM User u "
				     + "INNER JOIN Orders o ON o.CustomerId=u.ID "
				     + "WHERE EmailAddress=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			s.setString(1, email);
			ResultSet rs = s.executeQuery();
			while(rs.next())
			{
				UUID id = UUID.fromString(rs.getString("o.OurId"));
			    Order order = getOrder(id);
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
		
		query = "INSERT INTO Orders (OurId, OrderDate, CustomerId, Cost, BillingAddress, CreditCardNumber)"
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
	
	public static boolean refundOrder(Order order, int ownerId, double cost, MovieShowing showing) {
		CreditCard card = order.getCreditCard();
		card.setBalance(card.getBalance() + cost);
		boolean updated = CreditCardsDB.updateBalance(card, ownerId);
		
		if(updated) {
			order.setCost(order.getCost() - cost);
			
			int i = order.getShowings().indexOf(showing);
			int tickets = order.getTickets().get(i);
			order.getShowings().remove(i);
			order.getTickets().remove(i);
			
			updated = updateOrder(order);
			
			if(updated) {
				String query = "SELECT ID FROM Movie WHERE Name=?;";
				Connection c = Database.getConnection();
				 
				
				query = "SELECT ID FROM Orders WHERE OurId=?;";
				PreparedStatement s = Database.prepareStatement(c, query);
				int orderId = -1;
				try {
					s.setString(1, order.getID().toString());
					ResultSet rs = s.executeQuery();
					if(rs.next()) {
						orderId = rs.getInt("ID");
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				query = "DELETE FROM OrdersMovies " + 
						"WHERE OrdersId=? AND NumTickets=?;";
				s = Database.prepareStatement(c, query);
				int j = -1;
				try {
					s.setInt(1, orderId);
					s.setDouble(2, tickets);
					j = s.executeUpdate();
					s.close();
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if(j != -1) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean updateOrder(Order order) {

		//Due to the combination of a double and a string, the update is run here.
		String query = "UPDATE Orders SET Cost=? "
					 + "WHERE OurId=?;";
		Connection c = Database.getConnection();
    	PreparedStatement s = Database.prepareStatement(c, query);
    	int i = -1;
		try {
			s.setDouble(1, order.getCost());
			s.setString(2, order.getID().toString());
	        i = s.executeUpdate();
	        c.close();
	        s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(i == 1) {
			return true;
		}
		return false;
	}

	public static boolean deleteOrderItem(Order order, MovieShowing showing, Movie movie) {
		String query = "SELECT ID FROM Orders WHERE OurId=?";
		Connection c = Database.getConnection();
    	PreparedStatement s = Database.prepareStatement(c, query);
    	int orderId = -1;
    	int showingId = showing.getID();
    	try {
			s.setString(1, order.getID().toString());
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				orderId = rs.getInt("ID");
			}
			rs.close();
			
			query = "DELETE FROM OrdersMovies WHERE OrderId=? AND ShowingId=?";
			s = Database.prepareStatement(c, query);
			s.setInt(1, orderId);
			s.setInt(2, showingId);
			int i = s.executeUpdate();
			if(i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
