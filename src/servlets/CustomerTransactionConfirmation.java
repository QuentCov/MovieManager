package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.CreditCardsDB;
import models.CreditCard;

/**
 * Servlet implementation class CustomerTransactionConfirmation
 */
public class CustomerTransactionConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerTransactionConfirmation() {
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
		CreditCard card = (CreditCard) session.getAttribute("card");
		boolean exists = CreditCardsDB.verifyCard(card);
		if(exists) {
			card.setBalance(card.getBalance() - Double.parseDouble((String) session.getAttribute("cost")));
			boolean charged = CreditCardsDB.updateBalance(card);
			if(charged) {
				session.removeAttribute("cart");
				response.sendRedirect("CustomerTransactionConfirmation.jsp");
			}
			response.sendError(500, "Unable to update card.");
		}
		response.sendError(401, "Invalid Credit Card");
	}

}
