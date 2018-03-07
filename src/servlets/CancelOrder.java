package servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieDB;
import data.OrdersDB;
import models.Movie;
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
		
		String movieName = request.getParameter("movie");
		String orderId = request.getParameter("order");
		Order order = OrdersDB.getOrder(UUID.fromString(orderId));
		Movie movie = MovieDB.getMovieByName(movieName);
		
		if(order != null) {
			session.setAttribute("cancelOrder", order);
			session.setAttribute("cancelShowingItem", movie);
			response.sendRedirect("CancelOrder.jsp");
		}
		
		response.sendError(500, "Error in movie fetching");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
