package data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDB {
	public static Review getReview() {
		String query = "SELECT * FROM reviewBuildings WHERE Reviewname=";
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
			    //TODO: make review
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Review getReview(Review review) {
		return getThreatre();
	}
	
	public static boolean addReview(Review review) {
		String query = "INSERT INTO reviews VALUES (NULL, ?, ?, ?)";
		//TODO: Add values I need from David.
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here and have brainstormed solutions, 
	//	    but these solutions are too intensive for a project of this scope.
	public static boolean updateReview(Review review) {
		String query = "UPDATE reviews SET Reviewname=?, Password=?, WHERE id=?";
		int i = Database.runUpdate(query);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
	
	public static boolean deleteReview(String reviewName) {
		String query = "DELETE FROM reviews WHERE Reviewname=" + reviewName;
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
	    return false;
	}
	
	public static boolean validateReview(Review review) {
		return validateReview(review.getReviewName(), review.getPassword());
	}
	
	public static boolean validateReview(String reviewName, String pass) {
		String query = "SELECT * FROM reviews WHERE Reviewname=" + reviewName + " AND Password=" + pass;
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
