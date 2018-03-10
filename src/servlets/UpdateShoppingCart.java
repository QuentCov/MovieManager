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

import data.MovieShowingDB;
import models.MovieShowing;
import models.Order;
import models.User;

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/ViewAndCheckoutShoppingCart");
  	    dispatcher.forward(request, response);
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
		
		Order order = (Order) session.getAttribute("order");
		MovieShowing showing = (MovieShowing) session.getAttribute("showing");
		int ticket = (Integer) session.getAttribute("tickets");
		String t = request.getParameter("type");
		
		UUID id = UUID.randomUUID();
		if(order == null) {
			//Make a new order.
			order = new Order();
			User owner = (User) session.getAttribute("user");
			order.setCustomer(owner);
			order.setID(id);
			
			ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
			showings.add(showing);
			order.setShowings(showings);
			
			ArrayList<Integer> tickets = new ArrayList<Integer>();
			tickets.add(ticket);
			order.setTickets(tickets);
		}
		
		
		if(t != null && t.equals("add")) {
			//Check to ensure that the order is possible.
			if(order != null) {
				//Check the capacity of the showrooms of the movie's in the order.
				ArrayList<MovieShowing> movies = order.getShowings();
				ArrayList<Integer> tickets = order.getTickets();
				
				boolean cap = order.isNotOverCapacity();
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
				id = UUID.randomUUID();
				order.setID(id);
				cart.add(order);
			}
		} else if(t.equals("delete")) {
			//Remove the order
			if(order != null) {
				cart.remove(order);
			}
		}
		
		session.setAttribute("cart", cart);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/ViewAndCheckoutShoppingCart");
  	    dispatcher.forward(request, response);
	}

}
