package data;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public static int getMovieIdByName(String name) {
		String query = "SELECT ID FROM Movie WHERE Name=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(name);
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query, params);
		int id = -1;
		try {
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				id = rs.getInt("ID");
		    }
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static ArrayList<Movie> getAllMovies() {
		String query = "SELECT * FROM Movie;";
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query);
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
		    	Movie movie = MovieDB.createMovie(rs);
		    	movies.add(movie);
		    }
			rs.close();
			statement.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}
	
	public static Movie getMovieById(int id) {
		String query = "SELECT * FROM Movie WHERE ID=?;";
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query);
		Movie movie = null;
		try {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				movie = createMovie(rs);
			}
			rs.close();
			statement.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}
	
	public static Movie getMovieByName(String name) {
		String query = "SELECT * FROM Movie WHERE Name=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(name);
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query, params);
		Movie movie = null;
		try {
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				movie = createMovie(rs);
			}
			rs.close();
			statement.getConnection().close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}
	
	public static ArrayList<Movie> searchMoviesByName(String name) {
		String query = "SELECT * FROM Movie WHERE Name LIKE ?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add("%" + name + "%");
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query, params);
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
		    	Movie movie = MovieDB.createMovie(rs);
		    	movies.add(movie);
		    }
			rs.close();
			c.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
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
	public static boolean updateMovieById(Movie movie, int id) {
		String query = "UPDATE Movie SET Name=?, Genre=?, ThumbnailName=?, ThumbnailData=?, Description=?, Runtime=" + movie.getRuntime() + ", Rating=? "
				     + "WHERE ID=" + id + ";";
		
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
		int i = Database.runUpdate(query, params, stream, 3);
	    if(i == 1) {
	    	return true;
	    }
	    return false;
	}

	public static double getAverageScore(Movie movie) {
		String query = "SELECT r.Rating FROM Movies m "
					 + "INNER JOIN Review r ON m.ID=r.MovieId "
					 + "WHERE Name=?;";
		ArrayList<String> params = new ArrayList<String>();
		params.add(movie.getName());
		Connection c = Database.getConnection();
		PreparedStatement statement = Database.prepareStatement(c, query);
		
		double i = 0;
		int count = 0;
		try {
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				i = i + rs.getInt("Rating");
				count++;
			}
			if(count != 0) {
				i = (i / count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}
}