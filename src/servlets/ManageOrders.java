package servlets;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.OrdersDB;
import models.Order;

/**
 * Servlet implementation class ManageOrders
 */
public class ManageOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageOrders() {
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
		
			String id = request.getParameter("order");
			UUID uuid = UUID.fromString(id);
			Order order = OrdersDB.getOrder(uuid);
			
			if(order == null) {
				response.sendError(500, "Unable to retrieve order");
			} else {
				session.setAttribute("order", order);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/ManageOrder.jsp");
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
