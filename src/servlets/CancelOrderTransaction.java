package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.OrdersDB;
import models.MovieShowing;
import models.Order;

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
		
		//We assume that the order to be deleted exists.
		MovieShowing showing = (MovieShowing) session.getAttribute("cancelShowingItem");
		int ticketCount = (Integer) session.getAttribute("cancelShowingTicketCount");
		Order order = (Order) session.getAttribute("order");
		double cost = showing.getCost() * ticketCount;
		
		boolean deleted = OrdersDB.refundOrder(order, cost, showing);
		if(deleted) {
			deleted = OrdersDB.deleteOrderItem(order, showing);
			if(deleted) {
				response.sendRedirect("CancellationConfirmation");
			}
		}
		response.sendError(500, "Error in order cancellation");
	}

}
