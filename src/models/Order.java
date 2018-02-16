package models;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private Date date;
	private User customer;
	private ArrayList<Movie> movies;
	private ArrayList<Integer> tickets;
	private double cost;
	private CreditCard creditCard;
	private Address billingAddress;
	private Address shippingAddress;

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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	// checks if the billing address is a valid address
	public boolean isValidBillingAddress() {
		return this.billingAddress.isValidAddress();
	}

    // checks if the shipping address is a valid address
    public boolean isValidShippingAddress() {
        return this.shippingAddress.isValidAddress();
    }

	// checks if the order makes the showroom(s) over capacity
	public boolean isNotOverCapacity() {
		return true;
	}

}
