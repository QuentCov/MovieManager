package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import data.Database;
import data.OrdersDB;
import data.TheatresDB;
import data.UserDB;
import models.Order;
import models.Theatre;
import models.User;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    protected void init(HttpServletRequest request, HttpServletResponse response) {
    	//Database.setupDatabase();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName"); 
    	String password = request.getParameter("password");
    	User user = new User(userName, password);
    	
    	// check if valid user
    	if (User.isValidUser(user)) {
    		// check user type to determine where to redirect
    		User fullUser = UserDB.getUser(userName);
    		String userType = fullUser.getType();
    		
    		// start a new session for use by MovieManager.
			request.getSession().invalidate();
			HttpSession session = request.getSession();
			session.setAttribute("user", fullUser);
    		if (userType.equals("Customer")) {
    			//get the user's cart (or make a new one, if there isn't one)
    			ArrayList<Order> cart = new ArrayList<Order>();
    			cart = OrdersDB.getOrders(userName);
    			session.setAttribute("cartSize", cart.size());
    			session.setAttribute("cart", cart);
    			
    			//get the list of theatre's for use on CustomerHomePage.jsp
    			ArrayList<Theatre> theatres = new ArrayList<Theatre>();
    			theatres = TheatresDB.getTheatres();
    			session.setAttribute("theatres", theatres);
    			
            	response.sendRedirect("Jsp/Customer/CustomerHomePage.jsp");
    		} else {
    			response.sendRedirect("Jsp/Owner/OwnerHomePage.jsp");
    		}
    	} else {
        	response.sendRedirect("Jsp/Registration.jsp");
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
