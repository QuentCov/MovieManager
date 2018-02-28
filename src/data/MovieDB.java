package data;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Movie;

public class MovieDB {
	
	public static Movie createMovie(ResultSet rs) {
		Movie movie = new Movie();
		try {
			movie.setName(rs.getString("Name"));
			movie.setGenre(rs.getString("Genre"));
			movie.setThumbnailName(rs.getString("ThumbnailName"));
			movie.setThumbnailData(rs.getBytes("ThumbnailData"));
			movie.setDescription(rs.getString("Description"));
			movie.setRuntime(rs.getInt("Runtime"));
			movie.setRating(rs.getString("Rating"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return movie;
	}
	
	public static Movie getMovieById(int id) {
		String query = "SELECT * FROM Movie WHERE ID=" + id + ";";
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
				Movie movie = createMovie(rs);
				rs.close();
			    return movie;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Movie getMovieByName(String name) {
		String query = "SELECT * FROM Movie WHERE Name=" + name + ";";
		ResultSet rs = Database.runQuery(query);
		try {
			if(rs.next())
			{
				Movie movie = createMovie(rs);
				rs.close();
			    return movie;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addMovie(Movie movie) {
		String query = "INSERT INTO Movie (Name, Genre, ThumbnailName, ThumbnailData, Description, Runtime, Rating) "
					 + "VALUES (?, ?, ?, ?, ?, " + movie.getRuntime() + ", ?);";
		ArrayList<String> params = new ArrayList<String>();
		params.add(movie.getName());
		params.add(movie.getGenre());
		params.add(movie.getThumbnailName());
		// set temporary string and instead pass on the stream of image data
		params.add("temp");
		InputStream stream = new ByteArrayInputStream(movie.getThumbnailData());
		params.add(movie.getDescription());
		params.add(movie.getRating());
		int i = Database.runUpdate(query, params, stream, 4);
		if(i == 1) {
		    return true;
		}
		return false;
	}
	
	//Note: We recognize the inefficiency here in updating all values in the row and have brainstormed solutions, 
	//	    but these solutions are too extensive for a project of this scope.
	public static boolean updateMovie(Movie movie) {
		String query = "UPDATE Movie SET Genre=?, ThumbnailName=?, ThumbnailData=?, Description=?, Runtime=" + movie.getRuntime() + ", Rating=? "
				     + "WHERE Name=?;";
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(movie.getGenre());
		params.add(movie.getThumbnailName());
		// set temporary string and instead pass on the stream of image data
		params.add("temp");
		InputStream stream = new ByteArrayInputStream(movie.getThumbnailData());
		params.add(movie.getDescription());
		params.add(movie.getRating());
		params.add(movie.getName());
		int i = Database.runUpdate(query, params, stream, 2);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}
}
