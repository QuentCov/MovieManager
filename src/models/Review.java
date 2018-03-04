package models;

import java.util.Date;

public class Review {
	private User reviewer;
	private String review;
	private int rating;
	private Date date;
	private Movie movie;
	private final int MAX_REVIEW_LENGTH = 1000;
	
	public User getReviewer() {
		return reviewer;
	}
	
	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}
	
	public String getReview() {
		return review;
	}
	
	public void setReview(String review) {
		if(!isReviewTooLong(review)) {
			this.review = review;
		}
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public int getMAX_REVIEW_LENGTH() {
		return MAX_REVIEW_LENGTH;
	}
	
	// checks if the review is longer than the maximum review length
	public boolean isReviewTooLong(String review) {
		return this.review.length() >= this.getMAX_REVIEW_LENGTH();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
