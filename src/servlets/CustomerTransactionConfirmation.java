package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.CreditCardsDB;
import data.OrdersDB;
import data.UserDB;
import models.CreditCard;
import models.Order;
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
		
		//Verify the session.
		String sessionToken = (String) session.getAttribute("CSRFToken");
		String requestToken = request.getParameter("CSRFToken");
		
		if(!sessionToken.equals(requestToken)) {
			response.sendError(403, "Possible CSRF attack detected.");
		} else {
			User owner = (User) session.getAttribute("user");
			
			int userId = UserDB.getID(owner);
			
			ArrayList<CreditCard> cards = CreditCardsDB.getCreditCardsByUser(userId);
			
			CreditCard card = new CreditCard();
			card.setBalance(0);
			card.setCardNumber(request.getParameter("cardNumber"));
			card.setCardType(request.getParameter("cardType"));
			card.setCcv(Integer.parseInt(request.getParameter("cvv")));
			card.setExpirationMonth(Integer.parseInt(request.getParameter("month")));
			card.setExpirationYear(Integer.parseInt(request.getParameter("year")));
			card.setOwner(owner);
			
			//See if this is a card the user has used before.
			for(int i = 0; i < cards.size(); i++) {
				if(card.getCardNumber().equals(cards.get(i).getCardNumber()) && card.getCardType().equals(cards.get(i).getCardType())
					&& card.getCcv() == cards.get(i).getCcv() && card.getExpirationMonth() == cards.get(i).getExpirationMonth() && card.getExpirationYear() == cards.get(i).getExpirationYear()) {
					//Update the balance on this card, instead of making a new one.
					card.setBalance(cards.get(i).getBalance());
					i = cards.size();
				}
			}
			
			//Update the cards balance.
			@SuppressWarnings("unchecked")
			ArrayList<Order> cart = (ArrayList<Order>) session.getAttribute("cart");
			card.setBalance(card.getBalance() + OrdersDB.getTotalCost(cart));
			
			//Remove the orders from the database.
			cart = OrdersDB.getOrders(owner.getEmailAddress());
			for(int i = 0; i < cart.size(); i++) {
				int j = OrdersDB.fulfillOrder(cart.get(i));
				if(j == -1) {
					response.sendError(500, "Internal Server Error");
				}
			}
			
			session.setAttribute("cart", new ArrayList<Order>());
			session.setAttribute("cartSize", 0);
			session.setAttribute("completedOrder", cart);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/CustomerTransactionConfirmation.jsp");
	  	    dispatcher.forward(request, response);
			
			
		}
	}
}
