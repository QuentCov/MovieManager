package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieDB;
import data.TheatresDB;
import models.Movie;
import models.Theatre;

/**
 * Servlet implementation class TheatreAndMovieSearchQueryServlet
 */
public class TheatreAndMovieSearchQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheatreAndMovieSearchQueryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String name = (String) session.getAttribute("searchString");
		session.setAttribute("searchString", null);
		
		if(name == null) {
			name = request.getParameter("theatre");
			
			if(name == null) {
				name = request.getParameter("movieSearchString");
				ArrayList<Movie> movies = MovieDB.searchMoviesByName(name);
				if(movies != null) {
					session.setAttribute("movies", movies);
				} else {
					session.setAttribute("results", "No Movies Found");
					session.setAttribute("movies", null);
				}
			}
			
			ArrayList<Theatre> theatres = TheatresDB.searchTheatreByName(name);
			if(theatres != null) {
				session.setAttribute("movies", theatres);
			} else {
				session.setAttribute("results", "No Movies Found");
				session.setAttribute("movies", null);
			}
			
		} else {
			ArrayList<Movie> movies = MovieDB.searchMoviesByName(name);
			if(movies != null) {
				session.setAttribute("movies", movies);
			} else {
				session.setAttribute("results", "No Movies Found");
				session.setAttribute("movies", null);
			}
		}
		
		response.sendRedirect("MovieSearchResults.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
