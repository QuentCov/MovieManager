package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
			    
			    order.setID(id);
			    order.setDataId(rs.getInt("o.ID"));
			    order.setCustomer(customer);
			    if(rs.getString("o.Date") != null) {
			    	order.setDate(rs.getString("o.Date"));
			    } else {
			    	DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			    	order.setDate(format.format(new Date()));
			    }
			    
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
				     + "WHERE EmailAddress=? AND o.Fulfilled=0;";
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
	
	public static ArrayList<Order> getOrdersByMovieShowingId(int movieShowingsId) {
		String query = "SELECT o.OurId FROM Orders o "
			     		+ "INNER JOIN OrdersMovies om ON om.OrdersID=o.ID "
			     		+ "WHERE om.MovieShowingsId=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			s.setInt(1, movieShowingsId);
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
	
	@SuppressWarnings("resource")
	public static int addOrder(Order order) {
		
		Order newOrder = null;
		String query = "SELECT ID FROM User WHERE EmailAddress=?;";
		
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int custId = -1;
		try {
			s.setString(1, order.getCustomer().getEmailAddress());
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				custId = rs.getInt("ID");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(custId == -1) {
			return -1;
		}
		
		DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"); 
		String strDate = format.format(new Date());
		Double cost = order.getCost();
		
		if(order.getCreditCard() != null && order.getShippingAddress() != null) {
			CreditCard card = CreditCardsDB.getCreditCard(order.getCreditCard().getCardNumber());
			
			int cardId = card.getID();
			int addressId = AddressDB.getAddressIdByAddress1(order.getBillingAddress().getAddress1());
			int shippingId = AddressDB.getAddressIdByAddress1(order.getShippingAddress().getAddress1()); 
			
			query = "INSERT INTO Orders (OurId, CustomerId, Cost, CreditCardId, BillingAddressId, ShippingAddressId, Date) "
				  + "VALUES (?, ?, ?, ?, ?, ?, ?);";
			
			s = Database.prepareStatement(c, query);
			
			int i = -1;
			try {
				s.setString(1, order.getID().toString());
				s.setInt(2, custId);
				s.setDouble(3, cost);
				s.setInt(4, cardId);
				s.setInt(5, addressId);
				s.setInt(6, shippingId);
				s.setString(7, strDate);
				i = s.executeUpdate();
				s.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(i == 1) {
				newOrder = getOrder(order.getID());
			} else {
				return -1;
			}
		} else if(order.getShippingAddress() != null) {
			
			int addressId = AddressDB.getAddressIdByAddress1(order.getBillingAddress().getAddress1());
			int shippingId = AddressDB.getAddressIdByAddress1(order.getShippingAddress().getAddress1());
			
			query = "INSERT INTO Orders (OurId, CustomerId, Cost, BillingAddressId, ShippingAddressId, Date) "
				  + "VALUES (?, ?, ?, ?, ?, ?);";
			
			s = Database.prepareStatement(c, query);
			
			int i = -1;
			try {
				s.setString(1, order.getID().toString());
				s.setInt(2, custId);
				s.setDouble(3, cost);
				s.setInt(4, addressId);
				s.setInt(5, shippingId);
				s.setString(6, strDate);
				i = s.executeUpdate();
				s.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(i == 1) {
				newOrder = getOrder(order.getID());
			} else {
				return -1;
			}
		} else {
			int billingId = AddressDB.getAddressIdByAddress1(order.getBillingAddress().getAddress1());
			
			query = "INSERT INTO Orders (OurId, CustomerId, Cost, BillingAddressId, Date) "
				  + "VALUES (?, ?, ?, ?, ?);";
			
			s = Database.prepareStatement(c, query);
			
			int i = -1;
			try {
				s.setString(1, order.getID().toString());
				s.setInt(2, custId);
				s.setDouble(3, cost);
				s.setInt(4, billingId);
				s.setString(5, strDate);
				i = s.executeUpdate();
				s.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(i == 1) {
				newOrder = getOrder(order.getID());
			    
			} else {
				return -1;
			}
		}
		
		//Add each item to OrdersMovies.
		for(int i = 0; i < order.getShowings().size(); i++) {
			query = "INSERT INTO OrdersMovies (OrdersId, MovieShowingsId, NumTickets) "
				  + "VALUES (?, ?, ?);";
			
			c = Database.getConnection();
			s = Database.prepareStatement(c, query);
			try {
				s.setInt(1, newOrder.getDataId());
				s.setInt(2, order.getShowings().get(i).getID());
				s.setInt(3, order.getTickets().get(i));
				int j = s.executeUpdate();
				if(j != 1) {
					return -1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return newOrder.getDataId();
	}
	
	public static boolean deleteOrder(Order order) {
		if(OrdersDB.isEmpty(order)) {
			return deleteEmptyOrder(order);
		}
		
		Order fullOrder = OrdersDB.getOrder(order.getID());
		
		//Delete MovieShowings.
		String query = "DELETE FROM OrdersMovies WHERE OrdersId=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int i = -1;
		try {
			s.setInt(1, fullOrder.getDataId());
			i = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(i == -1 || i == 0) {
			return false;
		}
		
		//Delete the Order.
		i = -1;
		query = "DELETE FROM Orders WHERE ID=?;";
		s = Database.prepareStatement(c, query);
		try {
			s.setInt(1, fullOrder.getDataId());
			i = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(i == -1 || i == 0) {
			return false;
		}
		
		return true;
	}
	
	//For this method, we ensure that the order is empty. That is, no OrdersMovies entries relate to it.
	public static boolean deleteEmptyOrder(Order order) {
		if(!OrdersDB.isEmpty(order)) {
			return false;
		}
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
		
		query = "SELECT ID FROM Orders WHERE CustomerId=?;";
		
		try {
			s.setInt(1, id);
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
		
		
		query = "DELETE FROM Orders WHERE OurId=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(order.getID().toString());
		int i = Database.runUpdate(query, params);
		
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
			order.getShowings().remove(i);
			order.getTickets().remove(i);
			updated = updateOrder(order);
			
			if(updated) {
				Movie movie = MovieDB.getMovieByName(showing.getMovie().getName());
				return deleteOrderItem(order, showing, movie);
			}
		}
		return false;
	}

	private static boolean updateOrder(Order order) {

		//Due to the combination of a double and a string, the update is run here.
		String query = "UPDATE Orders SET Cost=?, ShippingAddress=?, CreditCardId=? WHERE OurId=?;";
		Connection c = Database.getConnection();
    	PreparedStatement s = Database.prepareStatement(c, query);
    	int i = -1;
		try {
			int addressId = AddressDB.getAddressIdByAddress1(order.getShippingAddress().getAddress1());
			s.setDouble(1, order.getCost());
			s.setInt(2, addressId);
			s.setInt(3, order.getCreditCard().getID());
			s.setString(4, order.getID().toString());
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
		String query = "SELECT ID FROM Orders WHERE OurId=?;";
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
			
			query = "DELETE FROM OrdersMovies WHERE OrdersId=? AND MovieShowingsId=?;";
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
	
	public static boolean isEmpty(Order order) {
		Order dbOrder = getOrder(order.getID());
		return dbOrder.getShowings().isEmpty();
	}
	
	public static boolean addItem(Order order, MovieShowing showing, int ticketCount) {
		String query = "INSERT INTO OrdersMovies (OrdersId, MovieShowingsId, NumTickets) VALUES (?, ?, ?);";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int i = -1;
		try {
			s.setInt(1, order.getDataId());
			s.setInt(2, showing.getID());
			s.setInt(3, ticketCount);
			i = s.executeUpdate();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(i == -1 || i == 0) {
			return false;
		}
		return true;
	}
	
	public static double getTotalCost(ArrayList<Order> orders) {
		double cost = 0;
		for(int i = 0; i < orders.size(); i++ ) {
			cost = cost + orders.get(i).getCost();
		}
		return cost;
	}

	public static int fulfillOrder(Order order) {
		String query = "UPDATE Orders SET Fulfilled=TRUE WHERE OurId=?";
		String id = order.getID().toString();
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		int i = -1;
		try {
			s.setString(1, id);
			i = s.executeUpdate();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
}
