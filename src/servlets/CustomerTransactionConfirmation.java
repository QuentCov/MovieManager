package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.CreditCardsDB;
import models.CreditCard;
import models.User;

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
		User owner = (User) session.getAttribute("user");
		
		CreditCard card = (CreditCard) session.getAttribute("card");
		boolean exists = CreditCardsDB.verifyCard(card);
		if(!exists) {
			response.sendError(401, "Invalid Credit Card");
		} else {
			card.setBalance(card.getBalance() - Double.parseDouble((String) session.getAttribute("cost")));
			int ownerId = owner.getId(owner);
			boolean charged = CreditCardsDB.updateBalance(card, ownerId);
			if(!charged) {
				response.sendError(500, "Unable to update card.");
			} else {
				session.removeAttribute("cart");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/CustomerTransactionConfirmation.jsp");
		  	    dispatcher.forward(request, response);
			}
		}
	}
}
