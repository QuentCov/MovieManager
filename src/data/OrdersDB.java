package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Order;

public class OrdersDB {
	public static Order getOrder(String name) {
		String query = "SELECT * FROM orders WHERE Name=" + name;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
			    //TODO: make order
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Order getOrder(Order order) {
		return getOrder(order.getCustomer().getFullName());
	}
	
	public static boolean addOrder(Order order) {
		String query = "INSERT INTO orders VALUES (NULL, ?, ?, ?)";
		//TODO: Add values I need from David.
		int i = Database.runUpdate(query);
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
	
	public static boolean deleteOrder(String orderName) {
		String query = "DELETE FROM orders WHERE Ordername=" + orderName;
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
	    return false;
	}
}
