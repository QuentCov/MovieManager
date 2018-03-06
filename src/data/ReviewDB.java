package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.Movie;
import models.Review;
import models.User;

public class ReviewDB {
	
	public static Review createReview(ResultSet rs, User reviewer, Movie movie) {
		Review review = new Review();
		try {
			review.setReviewer(reviewer);
			review.setReview(rs.getString("Review"));
			review.setRating(rs.getInt("Rating"));
			review.setMovie(movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return review;
	}
	
	public static ArrayList<Review> getReviewsByUser(User user) {
		String query = "SELECT * FROM User WHERE EmailAddress=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		try {
			s.setString(1, user.getEmailAddress());
			ResultSet rs = s.executeQuery();
			ArrayList<Review> reviews = new ArrayList<Review>();
			if(rs.next())
			{
			    query = "SELECT * FROM Review r "
			    	  + "INNER JOIN Movies m on r.MovieId=m.ID "
			    	  + "WHERE ReviewerId=" + rs.getInt("ID") + ";";
			    s = Database.prepareStatement(c, query);
				rs = s.executeQuery();
			    while(rs.next()) {
			    	Movie movie = MovieDB.createMovie(rs);
			    	Review review = createReview(rs, user, movie);
			    	reviews.add(review);
			    }
			}
			rs.close();
			s.close();
			c.close();
			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Review> getReviewByMovie(Movie movie) {
		String query = "SELECT * FROM User WHERE Name=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		try {
			s.setString(1, movie.getName());
			ResultSet rs = s.executeQuery();
			ArrayList<Review> reviews = new ArrayList<Review>();
			if(rs.next())
			{
			    query = "SELECT * FROM Review r "
			    	  + "INNER JOIN User u on r.ReviewerId=m.Id "
			    	  + "WHERE MovieId=" + rs.getInt("ID") + ";";
			    s = Database.prepareStatement(c, query);
				rs = s.executeQuery();
			    while(rs.next()) {
			    	User user = UserDB.createUser(rs);
			    	Review review = createReview(rs, user, movie);
			    	reviews.add(review);
			    }
			}
			rs.close();
			s.close();
			c.close();
			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addReview(Review review) {
		String query = "SELECT ID FROM User WHERE EmailAddress=?;";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		
		int ownerId = -1;
		int movieId = -1;
		try {
			s.setString(1, review.getReviewer().getEmailAddress());
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				ownerId = rs.getInt("ID");
			}
			
			query = "SELECT ID FROM Movie WHERE Name=?;";
			s = Database.prepareStatement(c, query);
			s.setString(1, review.getMovie().getName());
			rs = s.executeQuery();
			if(rs.next()) {
					movieId = rs.getInt("ID");
				
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Date date = new Date();
	    query = "INSERT INTO Review (Review, ReviewerId, MovieId, Rating, ReviewDate) "
	    	  + "VALUES (?, " + ownerId + ", " + movieId + ", " + review.getRating() + ", "+ date + ");";
	    ArrayList<String> params = new ArrayList<String>();
	    params.add(review.getReview());
		int i = Database.runUpdate(query);
		if(i == 1) {
		    return true;
		}
		return false;
	}
}
