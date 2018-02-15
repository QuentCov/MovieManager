package models;

import java.util.ArrayList;
import java.util.Date;

import data.User;

public class Order {
	private Date date;
	private User customer;
	private ArrayList<Movie> movies;
	private ArrayList<Integer> tickets;
	private ArrayList<Double> cost;
	private String cardType;
	private int cardNumber;
	private int ccv;
	private int expirationMonth;
	private int expirationYear;
	private String billingAddress;
	private String shippingAddress;
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public User getCustomer() {
		return customer;
	}
	
	public void setCustomer(User customer) {
		this.customer = customer;
	}	
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	public ArrayList<Integer> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Integer> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<Double> getCost() {
		return cost;
	}

	public void setCost(ArrayList<Double> cost) {
		this.cost = cost;
	}

	public String getCardType() {
		return cardType;
	}
	
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public int getCcv() {
		return ccv;
	}
	
	public void setCcv(int ccv) {
		this.ccv = ccv;
	}
	
	public int getExpirationMonth() {
		return expirationMonth;
	}
	
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	public int getExpirationYear() {
		return expirationYear;
	}
	
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	public String getBillingAddress() {
		return billingAddress;
	}
	
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public String getShippingAddress() {
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	// checks if the address is a valid address
	public boolean isValidAddress() {
		return true;
	}
	
	// checks if the order makes the showroom(s) over capacity
	public boolean isNotOverCapacity() {
		return true;
	}
	
}
