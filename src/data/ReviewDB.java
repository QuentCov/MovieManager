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
		String query = "SELECT * FROM users WHERE EmailAddress=" + user.getEmailAddress() + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		try {
			ResultSet rs = s.executeQuery(query);
			ArrayList<Review> reviews = new ArrayList<Review>();
			if(rs.next())
			{
			    query = "SELECT * FROM reviews r WHERE userId=" + rs.getInt("Id")
			    	  + "INNER JOIN movies m on r.movieId=m.Id" + ";";
			    s = Database.prepareStatement(c, query);
				rs = s.executeQuery(query);
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
		String query = "SELECT * FROM users WHERE Name=" + movie.getName() + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		try {
			ResultSet rs = s.executeQuery(query);
			ArrayList<Review> reviews = new ArrayList<Review>();
			if(rs.next())
			{
			    query = "SELECT * FROM reviews r WHERE movieId=" + rs.getInt("Id")
			    	  + "INNER JOIN users u on r.userId=m.Id;";
			    s = Database.prepareStatement(c, query);
				rs = s.executeQuery(query);
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
		String query = "SELECT Id FROM users WHERE EmailAddress=" + review.getReviewer().getEmailAddress() + ";";
		Connection c = Database.getConnection();
		PreparedStatement s = Database.prepareStatement(c, query);
		
		int ownerId = -1;
		int movieId = -1;
		try {
			ResultSet rs = s.executeQuery(query);
			if(rs.next()) {
				ownerId = rs.getInt("Id");
			}
			
			query = "SELECT Id FROM movies WHERE Name=" + review.getMovie().getName() + ";";
			s = Database.prepareStatement(c, query);
			rs = s.executeQuery(query);
			if(rs.next()) {
					movieId = rs.getInt("Id");
				
			}
			rs.close();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Date date = new Date();
	    query = "INSERT INTO reviews (Review, userId, movieId, Rating, ReviewDate) "
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
