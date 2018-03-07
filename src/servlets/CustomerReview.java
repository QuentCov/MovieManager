package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.ReviewDB;
import models.Movie;
import models.Review;
import models.User;

/**
 * Servlet implementation class CustomerReview
 */
public class CustomerReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerReview() {
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
		User reviewer = (User) session.getAttribute("user");
		Movie movie = (Movie) session.getAttribute("movie");
		String reviewString = request.getParameter("review");
		int rating = Integer.parseInt(request.getParameter("rating"));
		
		Review review = new Review();
		review.setReviewer(reviewer);
		review.setReview(reviewString);
		review.setRating(rating);
		review.setMovie(movie);
		review.setDate(new Date());
		
		boolean added = ReviewDB.addReview(review);
		if(!added) {
			response.sendError(500, "Failed to add review");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/CustomerReview.jsp");
	  	    dispatcher.forward(request, response);
		}
		
	}

}
