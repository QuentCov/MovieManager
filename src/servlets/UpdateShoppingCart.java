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
import data.OrdersDB;
import models.MovieShowing;
import models.Order;
import models.User;
import utilities.SecurityUtilities;

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
		HttpSession session = request.getSession();
		
		//Verify the session.
		String sessionToken = (String) session.getAttribute("CSRFToken");
		String requestToken = request.getParameter("CSRFToken");
		
		if(!sessionToken.equals(requestToken)) {
			response.sendError(403, "Possible CSRF attack detected.");
		} else {
		    //Get the shopping cart again, in case it was modified since the last time.
    		HttpSession session = request.getSession();
    		User user = (User) session.getAttribute("user");
    		ArrayList<Order> cart = OrdersDB.getOrders(user.getEmailAddress());
    		session.setAttribute("cart", cart);
    		
			RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/ViewAndCheckoutShoppingCart.jsp");
			dispatcher.forward(request, response);
		}
		
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
		
			@SuppressWarnings("unchecked")
			ArrayList<Order> cart = (ArrayList<Order>) session.getAttribute("cart");
			if(cart == null) {
				cart = new ArrayList<Order>();
			}
			
			Order order = (Order) session.getAttribute("order");
			MovieShowing showing = (MovieShowing) session.getAttribute("showing");
			
			//Filter the strings.
	    	ArrayList<String> parameters = new ArrayList<String>();
	    	parameters.add(request.getParameter("ticketCount")); //0
	    	parameters.add(request.getParameter("type")); //1
	    	
	    	parameters = SecurityUtilities.filterStrings(parameters);
			
	    	int ticket = Integer.parseInt(parameters.get(0));
	    	
			int orderId = -1;
			UUID id = UUID.randomUUID();
			if(order == null) {
				//Make a new order.
				order = new Order();
				User owner = (User) session.getAttribute("user");
				order.setCustomer(owner);
				order.setBillingAddress(owner.getStreetAddress());
				order.setID(id);
				
				ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
				showings.add(showing);
				order.setShowings(showings);
				
				ArrayList<Integer> tickets = new ArrayList<Integer>();
				tickets.add(ticket);
				order.setTickets(tickets);
				orderId = OrdersDB.addOrder(order);
				order.setDataId(orderId);
			}
			
			
			if(parameters.get(1) != null && parameters.get(1).equals("add")) {
				//Check to ensure that the order is possible.
				if(order != null) {
					//Check the capacity of the showrooms of the movie's in the order.
					ArrayList<MovieShowing> movies = order.getShowings();
					ArrayList<Integer> tickets = order.getTickets();
					
					boolean cap = order.isNotOverCapacity();
					if(!cap) {
						//We can't sell that many tickets.
						response.sendError(400, "That's too many tickets.");
					} else {
						//All purchases are valid.
						for(int i = 0; i < movies.size(); i++) {
							int ticketsSold = movies.get(i).getNumTicketsSold();
							int orderCapacity = tickets.get(i);
							
							movies.get(i).setNumTicketsSold(orderCapacity + ticketsSold);
							order.setCost(ticketsSold * movies.get(i).getCost());
							MovieShowingDB.updateMovieShowing(movies.get(i));
							OrdersDB.addItem(order, movies.get(i), tickets.get(i));
						}
						
						cart.add(order);
						int cartSize = (Integer) session.getAttribute("cartSize");
						session.setAttribute("cartSize", cartSize+1);
						session.setAttribute("cart", cart);
						RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/ViewAndCheckoutShoppingCart.jsp");
				  	    dispatcher.forward(request, response);
					}
				}
			} else if(parameters.get(1).equals("delete")) {
				//Remove the order
				if(order != null) {
					cart.remove(order);
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/ViewAndCheckoutShoppingCart.jsp");
		  	    dispatcher.forward(request, response);
			}
		}
		
	}
}
