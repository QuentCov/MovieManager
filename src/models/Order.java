package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Order {
	
	private int dataId;
	private UUID ID;
	private Date date;
	private User customer;
	private ArrayList<MovieShowing> showings;
	private ArrayList<Integer> tickets;
	private double cost;
	private CreditCard creditCard;
	private Address billingAddress;
	private Address shippingAddress;

	public Date getDate() {
		return date;
	}

	public void setDate(String string) {
		SimpleDateFormat sdfmt1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
		try {
			this.date = sdfmt1.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		for(int i = 0; i < showings.size(); i++) {
			Showroom room = showings.get(i).getShowroom();
			if(room.getCapacity() < (showings.get(i).getNumTicketsSold() + tickets.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	public int getTicketCount() {
		int j = 0;
		for(Integer i : tickets) {
			j = i + j;
		}
		return j;
	}
	
	public int getTicketsByMovie(Movie movie) {
		for(int i = 0; i < showings.size(); i++) {
			if(showings.get(i).getMovie().equals(movie)) {
				return tickets.get(i);
			}
		}
		
		return 0;
	}
	
	public MovieShowing getShowingByMovie(Movie movie) {
		for(int i = 0; i < showings.size(); i++) {
			if(showings.get(i).getMovie() != null && movie.getName() != null && showings.get(i).getMovie().getName().equals(movie.getName())) {
				return showings.get(i);
			}
		}
		return null;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<MovieShowing> getShowings() {
		return showings;
	}

	public void setShowings(ArrayList<MovieShowing> showings) {
		this.showings = showings;
	}

	public UUID getID() {
		return ID;
	}

	public void setID(UUID iD) {
		ID = iD;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
}
