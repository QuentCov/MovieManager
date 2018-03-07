package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

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
import models.User;

/**
 * Servlet implementation class CancelOrderTransaction
 */
public class CancelOrderTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderTransaction() {
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
		
		@SuppressWarnings("unchecked")
		ArrayList<Order> cart = (ArrayList<Order>) session.getAttribute("cart");
		User owner = (User) session.getAttribute("user");
		
		int ownerId = owner.getId(owner);
		String movieName = request.getParameter("movie");
		String orderID = request.getParameter("order");
		
		Movie movie = MovieDB.getMovieByName(movieName);
		Order order = OrdersDB.getOrder(UUID.fromString(orderID));
		
		MovieShowing showing = order.getShowingByMovie(movie);
		int ticketCount = order.getTicketsByMovie(movie);
		double cost = showing.getCost() * ticketCount;
		
		//Remove the item from the session's cart.
		for(int i = 0; i < cart.size(); i++) {
			if(cart.get(i).getID().toString().equals(orderID)) {
				double curCost = cart.get(i).getCost();
				MovieShowing show = cart.get(i).getShowingByMovie(movie);
				cart.get(i).getShowings().remove(show);
				cart.get(i).setCost(curCost - cost);
			}
		}
		
		session.setAttribute("cart", cart);
		int size = (int) session.getAttribute("cartSize");
		size--;
		session.setAttribute("cartSize", size);
		
		//Remove the item from the database.
		boolean deleted = OrdersDB.refundOrder(order, ownerId, cost, showing);
		if(deleted) {
			deleted = OrdersDB.deleteOrderItem(order, showing, movie);
			if(deleted) {
				
				response.sendRedirect("CancellationConfirmation.jsp");
			}
		}
		response.sendError(500, "Error in order cancellation");
	}

}
