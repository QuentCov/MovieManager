package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MovieDB;
import models.Movie;

/**
 * Servlet implementation class OwnerMovieSearchResults
 */
public class OwnerMovieSearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerMovieSearchResults() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieToSearch = request.getParameter("movieToSearch");		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		if (movieToSearch.equals("")) {
			movies = MovieDB.getAllMovies();
		} else {
			movies = MovieDB.searchMoviesByName(movieToSearch);
		}
	    request.setAttribute("movies", movies);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/MovieSearchResults.jsp");
  	    dispatcher.forward(request, response);
	}
}
