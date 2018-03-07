package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MovieDB;
import data.MovieShowingDB;
import models.Movie;

/**
 * Servlet implementation class MovieDetails
 */
public class MovieDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDetails() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieName = request.getParameter("movieName");		
		Movie movie = MovieDB.getMovieByName(movieName);
		int movieId = MovieDB.getMovieIdByName(movieName);
		int ticketsSold = MovieShowingDB.getTicketsSoldByMovieId(movieId);
		request.setAttribute("movie", movie);
		request.setAttribute("movieId", movieId);
		request.setAttribute("ticketsSold", ticketsSold);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/MovieDetails.jsp");
  	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}