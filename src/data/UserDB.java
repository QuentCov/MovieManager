package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

public class UserDB {
	
	public static User getUser(String userName, String pass) {
		String query = "SELECT * FROM users WHERE Username=" + userName + " AND Password=" + pass;
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
			    //TODO: make user
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static User getUser(User user) {
		return getUser(user.getUserName(), user.getPassword());
	}
	
	public static boolean addUser(User user) {
		String query = "INSERT INTO users VALUES (NULL, ?, ?, ?)";
		//TODO: Add values I need from David.
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too intensive for a project of this scope.
	public static boolean updateUser(User user) {
		String query = "UPDATE users SET Username=?, Password=?, WHERE id=?";
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean deleteUser(String userName) {
		String query = "DELETE FROM users WHERE Username=" + userName;
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
	    return false;
	}
	
	public static boolean validateUser(User user) {
		return validateUser(user.getUserName(), user.getPassword());
	}
	
	public static boolean validateUser(String userName, String pass) {
		String query = "SELECT * FROM users WHERE Username=" + userName + " AND Password=" + pass;
		ResultSet rs = Database.runQuery(query);
        try {
			if(rs.next())
			{
			    return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}
}
