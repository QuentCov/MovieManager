package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieShowingDB;
import models.MovieShowing;
import models.Order;

/**
 * Servlet implementation class UpdateShoppingCart
 */
public class UpdateShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShoppingCart() {
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
		if(cart == null) {
			cart = new ArrayList<Order>();
		}
		
		Order newOrder = (Order) session.getAttribute("order");
		String t = (String) session.getAttribute("type");
		
		if(t != null && t.equals("add")) {
			//Check to ensure that the order is possible.
			if(newOrder != null) {
				//Check the capacity of the showrooms of the movie's in the order.
				ArrayList<MovieShowing> movies = newOrder.getMovies();
				ArrayList<Integer> tickets = newOrder.getTickets();
				
				boolean cap = newOrder.isNotOverCapacity();
				if(!cap) {
					//We can't sell that many tickets.
					response.sendError(400, "That's too many tickets.");
				}
				
				//All purchases are valid.
				for(int i = 0; i < movies.size(); i++) {
					int ticketsSold = movies.get(i).getNumTicketsSold();
					int orderCapacity = tickets.get(i);
					
					movies.get(i).setNumTicketsSold(orderCapacity + ticketsSold);
					MovieShowingDB.updateMovieShowing(movies.get(i));
				}
				
				//Give this item an identifier for use by the cart.
				UUID id = UUID.randomUUID();
				newOrder.setID(id);
				cart.add(newOrder);
			}
		} else {
			//Remove the order
			if(newOrder != null) {
				cart.remove(newOrder);
			}
		}
		
		session.setAttribute("cart", cart);
		response.sendRedirect("ViewAndCheckoutShoppingCart.jsp");
	}

}
