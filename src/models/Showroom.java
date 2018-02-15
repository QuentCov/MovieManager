package models;

import java.util.ArrayList;

public class Showroom {
	private String name;
	private int capacity;
	private ArrayList<MovieShowing> showings;
	private Theatre theatre;
	
	public Showroom() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public ArrayList<MovieShowing> getShowings() {
		return showings;
	}

	public void setShowings(ArrayList<MovieShowing> showings) {
		this.showings = showings;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	
}
