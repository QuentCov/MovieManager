package data;

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
			movie.setThumbnailFile(rs.getString("Thumbnail"));
			movie.setDescription(rs.getString("Description"));
			movie.setRuntime(rs.getInt("Runtime"));
			movie.setRating(rs.getString("Rating"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movie;
	}

	public static Movie getMovie(int id) {
		String query = "SELECT * FROM movies WHERE Id=" + id;
		ResultSet rs = Database.runQuery(query);
		try {
			if (rs.next()) {
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

	public static Movie getMovie(String name) {
		String query = "SELECT * FROM movies WHERE Name=" + name;
		ResultSet rs = Database.runQuery(query);
		try {
			if (rs.next()) {
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
		String query = "INSERT INTO movies (Name, Genre, Thumbnail, Description, Runtime, Rating)"
				+ "VALUES (?, ?, ?, ?, " + movie.getRuntime() + ", ?)";
		ArrayList<String> params = new ArrayList<String>();
		params.add(movie.getName());
		params.add(movie.getGenre());
		params.add(movie.getThumbnailFile());
		params.add(movie.getDescription());
		params.add(movie.getRating());
		int i = Database.runUpdate(query, params);
		if (i == 1) {
			return true;
		}
		return false;
	}

	// Note: We recognize the inefficiency here and have brainstormed solutions,
	// but these solutions are too extensive for a project of this scope.
	public static boolean updateMovie(Movie movie) {
		String query = "UPDATE users SET Genre=?, Thumbnail=?, Description=?, Runtime=" + movie.getRuntime()
				+ ", Rating=? " + "WHERE Name=?";

		ArrayList<String> params = new ArrayList<String>();
		params.add(movie.getGenre());
		params.add(movie.getThumbnailFile());
		params.add(movie.getDescription());
		params.add(movie.getRating());
		params.add(movie.getName());
		int i = Database.runUpdate(query, params);
		if (i == 1) {
			return true;
		}
		return false;
	}

	public static double getAverageScore(Movie movie) {
		String query = "SELECT r.Rating FROM movies m WHERE Name=?"
					 + "INNER JOIN ratings r ON m.Id=r.movieId";
		ArrayList<String> params = new ArrayList<String>();
		params.add(movie.getName());
		ResultSet rs = Database.runQuery(query, params);
		double i = 0;
		int count = 0;
		try {
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