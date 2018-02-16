package models;

public class Review {
	private User reviewer;
	private String review;
	private double rating;
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
		this.review = review;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
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
	public boolean isReviewTooLong() {
		return this.review.length() >= this.getMAX_REVIEW_LENGTH();
	}
	
}