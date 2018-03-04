package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Order {
	private UUID ID;
	private Date date;
	private User customer;
	private ArrayList<MovieShowing> movies;
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
		for(int i = 0; i < movies.size(); i++) {
			Showroom room = movies.get(i).getShowroom();
			if(room.getCapacity() < (movies.get(i).getNumTicketsSold() + tickets.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	public int getTicketCount() {
		int j = 0;
		for(Integer i : tickets) {
			i = i + j;
		}
		return j;
	}
	
	public int getTicketsByMovie(Movie movie) {
		for(int i = 0; i < movies.size(); i++) {
			if(movies.get(i).getMovie() == movie) {
				return tickets.get(i);
			}
		}
		
		return 0;
	}

	public ArrayList<MovieShowing> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<MovieShowing> movies) {
		this.movies = movies;
	}

	public UUID getID() {
		return ID;
	}

	public void setID(UUID iD) {
		ID = iD;
	}
}
