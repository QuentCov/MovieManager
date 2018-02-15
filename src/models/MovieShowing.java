package models;

import java.util.Date;

public class MovieShowing {
	private Movie movie;
	private Showroom showroom;
	private Date startTime;
	private Date endTime;
	private int numTicketsSold;
	private double cost;
	
	public MovieShowing() {}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getNumTicketsSold() {
		return numTicketsSold;
	}

	public void setNumTicketsSold(int numTicketsSold) {
		this.numTicketsSold = numTicketsSold;
	}

	public Showroom getShowroom() {
		return showroom;
	}

	public void setShowroom(Showroom showroom) {
		this.showroom = showroom;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	
}
