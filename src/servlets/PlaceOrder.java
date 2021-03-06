package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.OrdersDB;
import models.Order;
import models.User;
import utilities.SecurityUtilities;

/**
 * Servlet implementation class PlaceOrder
 */
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
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
				//Remove the orders from the database.
				cart = OrdersDB.getOrders(owner.getEmailAddress());
				int result = 1;
				String message = "Transaction processed and order fulfilled!";
				for(int i = 0; i < cart.size(); i++) {
					int j = OrdersDB.fulfillOrder(cart.get(i));
					if(j == -1) {
						result = 0;
						message = "Internal server error processing order";
					}
				}
				session.setAttribute("cart", new ArrayList<Order>());
				session.setAttribute("cartSize", 0);
				session.setAttribute("completedOrder", cart);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				String jsonStr = "{\"processingResult\": " + result + ", \"processingMessage\": \"" + message + "\"}";
				out.write(jsonStr);
			}	
		}
	}
}
