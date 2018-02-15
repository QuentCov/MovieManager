package models;

import java.util.ArrayList;
import data.User;

public class Theatre {
	private String name;
	private String address;
	private ArrayList<Showroom> showrooms;
	private User owner;
	
	public Theatre() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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
