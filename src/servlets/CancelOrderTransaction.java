package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
import models.User;
import utilities.SecurityUtilities;

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
			
				@SuppressWarnings("unchecked")
				ArrayList<Order> cart = (ArrayList<Order>) session.getAttribute("cart");
				User owner = (User) session.getAttribute("user");
					
				String movieName = request.getParameter("movie");
				String orderID = request.getParameter("order");
				
				Movie movie = MovieDB.getMovieByName(movieName);
				Order order = OrdersDB.getOrder(UUID.fromString(orderID));
				
				if(owner == null || movie == null || order == null)  {
					//The user refreshed the page.
					RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/CancellationConfirmation.jsp");
			  	    dispatcher.forward(request, response);
				} else {
					int ownerId = owner.getId(owner);
					
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
					
					
					//Remove the item from the database.
					boolean deleted = OrdersDB.refundOrder(order, ownerId, cost, showing);
					
					if(!deleted) {
						response.sendError(500, "Error in order cancellation");
					} else {
						//Check if the order is now empty.
						if(OrdersDB.isEmpty(order)) {
							OrdersDB.deleteEmptyOrder(order);
							cart.remove(order);
							session.setAttribute("cart", cart);
							int size = (int) session.getAttribute("cartSize");
							size--;
							session.setAttribute("cartSize", size);
						}
						
						session.setAttribute("deletedOrder", order);
						session.setAttribute("deletedItem", showing);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/CancellationConfirmation.jsp");
				  	    dispatcher.forward(request, response);
					}
				}
			}
		}
	}
}
