package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class CustomerTransaction
 */
public class CustomerTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerTransaction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				ArrayList<Order> cart = OrdersDB.getOrders(user.getEmailAddress());
				
				session.setAttribute("cart", cart);
				session.setAttribute("totalCost", OrdersDB.getTotalCost(cart));
				String temp = getServletContext().getInitParameter("BankingUrl");
				session.setAttribute("bankingUrl", temp);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/ConfirmOrder.jsp");
		  	    dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
