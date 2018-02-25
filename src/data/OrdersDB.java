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
	public static Order getOrder(String email) {
		String query = "SELECT * FROM users u WHERE EmailAddress=" + email 
				     + "INNER JOIN creditCards cc ON u.FullName=cc.CardHolderName"
				     + "INNER JOIN orders o ON u.Id=o.CustomerId"
				     + "INNER JOIN orderItems oi ON o.Id=oi.OrderId"
				     + "INNER JOIN movieShowing ms ON ms.Id=oi.ShowingId"
				     + "INNER JOIN movies m ON ms.movieId=m.Id";
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
			    Order order = new Order();
			    User customer = UserDB.createUser(rs);
			    CreditCard card = CreditCardsDB.createCard(rs);
			    
			    order.setCustomer(customer);
			    order.setDate(new Date());
			    order.setCreditCard(card);
			    order.setCost(rs.getInt("o.TotalCost"));
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
			    rs.close();
			    return order;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addOrder(Order order) {
		String query = "INSERT INTO users (OrderDate, CustomerId, TotalCost, BillingAddress, CreditCardNumber)"
				 + "VALUES (" + new Date() + ", ?, " + order.getCost() + ", ?, ?)";
		ArrayList<String> params = new ArrayList<String>();
		params.add(order.getBillingAddress().getAddress1());
		params.add(order.getCreditCard().getCardNumber());
		int i = Database.runUpdate(query, params);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too intensive for a project of this scope.
	public static boolean updateOrder(Order order) {
		String query = "UPDATE orders SET Ordername=?, Password=?, WHERE id=?";
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
}
