package models;

import java.util.ArrayList;

public class Theatre {
	private String name;
	private Address address;
	private ArrayList<Showroom> showrooms;
	private User owner;
	
	public Theatre() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<Showroom> getShowrooms() {
		return showrooms;
	}

	public void setShowrooms(ArrayList<Showroom> showrooms) {
		this.showrooms = showrooms;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
