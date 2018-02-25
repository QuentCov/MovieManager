package models;

public class Movie {
	private String name;
	private String genre;
	private String thumbnailFile;
	private String description;
	private int runtime;
	private String rating;
	
	public Movie() {}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getThumbnailFile() {
		return thumbnailFile;
	}
	
	public void setThumbnailFile(String thumbnailFile) {
		this.thumbnailFile = thumbnailFile;
	}
	
	public int getRuntime() {
		return runtime;
	}
	
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public double getAverageRating() {
		return 0;
	}
}
