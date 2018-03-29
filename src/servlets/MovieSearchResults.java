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
import data.MovieShowingDB;
import data.ReviewDB;
import models.Movie;
import models.MovieShowing;
import models.Review;
import models.User;
import utilities.SecurityUtilities;

/**
 * Servlet implementation class MovieSearchResults
 */
public class MovieSearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieSearchResults() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(!SecurityUtilities.loggedInCustomer(user)) {
			response.sendError(403);
		} else {
		
			//Verify the session.
			String sessionToken = (String) session.getAttribute("CSRFToken");
			String requestToken = request.getParameter("CSRFToken");
			
			if(!sessionToken.equals(requestToken)) {
				response.sendError(403, "Possible CSRF attack detected.");
			} else {
				//Get the showing's id, either from the search bar, or the most recent search.
				String showingIdtemp = request.getParameter("itemIndex");
				int showingId = -1;
				if(showingIdtemp == null) {
					showingId = (int) session.getAttribute("itemIndex");
				} else {
					showingId = Integer.parseInt(showingIdtemp);
					session.setAttribute("itemIndex", showingId);
				}
				
				MovieShowing showing = MovieShowingDB.getMovieShowingById(showingId);
	
				
				showingId = Integer.parseInt(request.getParameter("itemIndex"));
				showing = MovieShowingDB.getMovieShowingById(showingId);
				
				if(showing != null) {
					Movie movie = MovieDB.getMovieByName(showing.getMovie().getName());
					if(movie != null) {
						ArrayList<Review> reviews = ReviewDB.getReviewByMovie(movie);
						if(reviews == null) {
							session.setAttribute("reviews", null);
						} else {
							session.setAttribute("reviews", reviews);
						}
						session.setAttribute("results", null);
						session.setAttribute("movie", movie);
						session.setAttribute("showing", showing);
					} else {
						session.setAttribute("results", "Movie Not Found");
						session.setAttribute("movie", null);
						session.setAttribute("showing", null);
						session.setAttribute("reviews", null);
					}
					
				} else {
					session.setAttribute("results", "Movie Not Found");
					session.setAttribute("movie", null);
					session.setAttribute("showing", null);
					session.setAttribute("reviews", null);
				}
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/MovieDetailsSelection.jsp");
		  	    dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
