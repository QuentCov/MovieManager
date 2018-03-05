package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MovieShowingDB;
import models.MovieShowing;

/**
 * Servlet implementation class CancelShowingConfirmation
 */
public class CancelShowingConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelShowingConfirmation() {
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
		int showingId = Integer.parseInt(request.getParameter("showingId"));
		MovieShowing showing = MovieShowingDB.getMovieShowingById(showingId);
		boolean result1 = MovieShowingDB.deleteMovieShowing(showingId);
		//TODO refund credit cards
		if (result1) {
			request.setAttribute("result", true);
			request.setAttribute("showing", showing);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/CancellationConfirmation.jsp");
	  	    dispatcher.forward(request, response);
		} else {
			response.sendError(500, "Failed to cancel showing."); 
		}
	}
}
