package servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieDB;
import data.OrdersDB;
import models.Movie;
import models.MovieShowing;
import models.Order;

/**
 * Servlet implementation class CancelOrder
 */
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Verify the session.
		String sessionToken = (String) session.getAttribute("CSRFToken");
		String requestToken = request.getParameter("CSRFToken");
		
		if(!sessionToken.equals(requestToken)) {
			response.sendError(403, "Possible CSRF attack detected.");
		} else {
		
			String movieName = request.getParameter("movie");
			String orderId = request.getParameter("order");
			Order order = OrdersDB.getOrder(UUID.fromString(orderId));
			Movie movie = MovieDB.getMovieByName(movieName);
			MovieShowing showing = order.getShowingByMovie(movie);
			
			session.setAttribute("cancelOrder", order);
			session.setAttribute("cancelShowingItem", showing);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/CancelOrder.jsp");
	  	    dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
