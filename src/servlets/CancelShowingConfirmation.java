package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieShowingDB;
import data.OrdersDB;
import models.MovieShowing;
import models.Order;
import utilities.SecurityUtilities;

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
		
		HttpSession session = request.getSession();
		
		//Verify the session.
		String sessionToken = (String) session.getAttribute("CSRFToken");
		String requestToken = request.getParameter("CSRFToken");
		
		if(!sessionToken.equals(requestToken)) {
			response.sendError(403, "Possible CSRF attack detected.");
		} else {
			String showingIdString = request.getParameter("showingId");
	    	
	    	showingIdString = SecurityUtilities.filterString(showingIdString);
			
			int showingId = Integer.parseInt(showingIdString);
			MovieShowing showing = MovieShowingDB.getMovieShowingById(showingId);
			ArrayList<Order> orders = OrdersDB.getOrdersByMovieShowingId(showingId);
			boolean result1 = false;
			for(int i = 0; i < orders.size(); i++) {
				Order order = orders.get(i);
				result1 = OrdersDB.deleteOrderItem(order, showing, showing.getMovie());
			}
			boolean result2 = MovieShowingDB.deleteMovieShowing(showingId);
			//TODO refund credit cards
			if (result1 && result2) {
				request.setAttribute("result", true);
				request.setAttribute("showing", showing);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/CancellationConfirmation.jsp");
		  	    dispatcher.forward(request, response);
			} else {
				response.sendError(500, "Failed to cancel showing."); 
			}
		}
	}
}
