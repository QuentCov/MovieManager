package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		Order order = (Order) session.getAttribute("cancelOrder");
		Movie movie = (Movie) session.getAttribute("movie");
		order = OrdersDB.getOrder(order.getID());
		
		if(order != null) {
			session.setAttribute("cancelOrder", order);
			session.setAttribute("cancelShowingItem", movie);
			session.setAttribute("cancelShowingTicketCount", order.getTicketsByMovie(movie));
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
