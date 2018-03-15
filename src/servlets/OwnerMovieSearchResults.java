package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieDB;
import models.Movie;
import utilities.SecurityUtilities;

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
		HttpSession session = request.getSession();
		//Verify the session.
		String sessionToken = (String) session.getAttribute("CSRFToken");
		String requestToken = request.getParameter("CSRFToken");
		
		if(!sessionToken.equals(requestToken)) {
			response.sendError(403, "Possible CSRF attack detected.");
		} else {
			String movieToSearch = request.getParameter("movieToSearch");
			movieToSearch = SecurityUtilities.filterString(movieToSearch);
			
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
}
