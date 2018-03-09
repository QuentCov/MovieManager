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
import data.TheatreDB;
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
		
		String name = null;
		name = request.getParameter("theatre");
			
		if(name == null) {
			name = request.getParameter("movieSearchString");
			ArrayList<Movie> movies = MovieDB.getAllMovies();
			if(movies != null) {
				session.setAttribute("movies", movies);
				session.setAttribute("type", "movie");
			} else {
				session.setAttribute("results", "No Movies Found");
				session.setAttribute("type", "movie");
				session.setAttribute("movies", null);
			}
		}
		else {
			ArrayList<Theatre> theatres = TheatreDB.searchTheatreByName(name);
			if(theatres != null) {
				session.setAttribute("theatres", theatres);
				session.setAttribute("type", "theatre");
			} else {
				session.setAttribute("results", "No theatres Found");
				session.setAttribute("type", "theatre");
				session.setAttribute("theatres", null);
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/MovieSearchResults.jsp");
  	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
