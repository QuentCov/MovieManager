package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import data.Database;
import data.OrdersDB;
import data.TheatreDB;
import data.UserDB;
import models.Order;
import models.Theatre;
import models.User;
import utilities.SecurityUtilities;

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
    	
    	//Filter the strings.
    	ArrayList<String> parameters = new ArrayList<String>();
    	parameters.add(userName); //0
    	parameters.add(password); //1
    	
    	parameters = SecurityUtilities.filterStrings(parameters);
    	User user = new User(parameters.get(0), SecurityUtilities.hashString(parameters.get(1)));
    	
    	// check if valid user
    	if (User.isValidUser(user)) {
    		
    		// check user type to determine where to redirect
    		User fullUser = UserDB.getUserByEmailAddress(parameters.get(0));
    		String userType = fullUser.getType();
    		
    		// start a new session for use by MovieManager.
			request.getSession().invalidate();
			HttpSession session = request.getSession();
			
			session.setAttribute("user", fullUser);
			
			// generate a secure-random string to use for authentication
    		String secureToken = SecurityUtilities.generateToken();
    		session.setAttribute("CSRFToken", secureToken);
			
    		if (userType.equals("Customer")) {
    			//get the user's cart (or make a new one, if there isn't one)
    			ArrayList<Order> cart = new ArrayList<Order>();
    			cart = OrdersDB.getOrders(parameters.get(0));
    			session.setAttribute("cartSize", cart.size());
    			session.setAttribute("cart", cart);
    			
    			//get the list of theatre's for use on CustomerHomePage.jsp
    			ArrayList<Theatre> theatres = new ArrayList<Theatre>();
    			theatres = TheatreDB.getTheatres();
    			session.setAttribute("theatres", theatres);
    			RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/CustomerHomePage.jsp");
    	  	    dispatcher.forward(request, response);
    		} else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/OwnerHomePage.jsp");
    	  	    dispatcher.forward(request, response);
    		}
    	} else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Registration.jsp");
	  	    dispatcher.forward(request, response);
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
