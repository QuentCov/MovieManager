package models;

import data.MovieDB;
import java.io.UnsupportedEncodingException;
import org.apache.tomcat.util.codec.binary.Base64;

public class Movie {
	
	private int ID;
	private String name;
	private String genre;
	private String thumbnailName;
	private byte[] thumbnailData;
	private String description;
	private int runtime;
	private String rating;
	
	public Movie() {}
	
	public Movie(String name, String genre, String thumbnailName, byte[] thumbnailData, String description, int runtime, String rating) {
		this.name = name;
		this.genre = genre;
		this.thumbnailName = thumbnailName;
		this.thumbnailData = thumbnailData;
		this.description = description;
		this.runtime = runtime;
		this.rating = rating;
	}
	
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
	
	public String getThumbnailName() {
		return thumbnailName;
	}

	public void setThumbnailName(String thumbnailName) {
		this.thumbnailName = thumbnailName;
	}

	public byte[] getThumbnailData() {
		return thumbnailData;
	}

	public void setThumbnailData(byte[] thumbnailData) {
		this.thumbnailData = thumbnailData;
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
		return MovieDB.getAverageScore(this);
	}
	
	public String renderImage() throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encodeBase64(this.thumbnailData);
        return new String(encodeBase64, "UTF-8");
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
