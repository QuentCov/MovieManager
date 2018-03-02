package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.CreditCard;
import models.Movie;
import models.Order;
import models.User;

public class OrdersDB {
	public static ArrayList<Order> getOrders(String email) {
		String query = "SELECT * FROM users u WHERE EmailAddress=" + email 
				     + " INNER JOIN creditCards cc ON u.FullName=cc.CardHolderName "
				     + "INNER JOIN orders o ON u.Id=o.CustomerId "
				     + "INNER JOIN orderItems oi ON o.Id=oi.OrderId "
				     + "INNER JOIN movieShowing ms ON ms.Id=oi.ShowingId "
				     + "INNER JOIN movies m ON ms.movieId=m.Id;";
		ResultSet rs = Database.runQuery(query);
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			while(rs.next())
			{
			    Order order = new Order();
			    User customer = UserDB.createUser(rs);
			    CreditCard card = CreditCardsDB.createCard(rs);
			    
			    order.setCustomer(customer);
			    order.setDate(new Date());
			    order.setCreditCard(card);
			    order.setCost(rs.getInt("o.TotalCost"));
			    //TODO: ...
			    //Address address = new Address();
			    //order.setBillingAddress();
			    //private Address shippingAddress;
			    
			    ArrayList<Integer> tickets = new ArrayList<Integer>();
			    ArrayList<Movie> movies = new ArrayList<Movie>();
			    tickets.add(rs.getInt("oi.Quantity"));
			    movies.add(MovieDB.createMovie(rs));
			    
			    while(rs.next()) {
			    	tickets.add(rs.getInt("oi.Quantity"));
			    	movies.add(MovieDB.createMovie(rs));
			    }
			
			    orders.add(order);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static boolean addOrder(Order order) {
		String query = "INSERT INTO users (OrderDate, CustomerId, TotalCost, BillingAddress, CreditCardNumber)"
				 + " VALUES (" + new Date() + ", ?, " + order.getCost() + ", ?, ?);";
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
		String query = "SELECT Id FROM users WHERE Email=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(owner.getEmailAddress());
		ResultSet rs = Database.runQuery(query, params);
		int id = -1;
		try {
			if(rs.next()) {
				id = rs.getInt("Id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(id == -1) {
			return false;
		}
		
		query = "SELECT Id FROM orders WHERE CustomerId=?;";
		params.clear();
		params.add(Integer.toString(id));
		rs = Database.runQuery(query, params);
		try {
			if(rs.next()) {
				id = rs.getInt("Id");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(id == -1) {
			return false;
		}
		
		query = "DELETE FROM orderItems WHERE OrderId=?;";
		params.clear();
		params.add(Integer.toString(id));
		int i = Database.runUpdate(query, params);
		if(i != 0 || i != -1) {
			query = "DELETE FROM orders WHERE CartId=?;";
			params.clear();
			params.add(Integer.toString(order.getID()));
			i = Database.runUpdate(query, params);
			
			if(i != 0 || i != -1) {
				return true;
			}
		}
		return false;
	}

	public static boolean refundOrder(Order order) {
		CreditCard card = order.getCreditCard();
		card.setBalance(card.getBalance() + order.getCost());
		boolean updated = CreditCardsDB.updateBalance(card);
		
		if(updated) {
			return true;
		}
		return false;
	}
}
