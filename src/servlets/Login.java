package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Database;

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
    	Database.setupDatabase();
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
		String userName = request.getParameter("userName"); 
    	String password = request.getParameter("password");
    	User user = new User(userName, password);
    	
    	// check if valid user
    	if (User.isValidUser(user)) {
    		// check user type to determine where to redirect
    		String userType = User.getUserTypeFromDatabase(user);
    		if (userType.equals("Customer")) {
            	response.sendRedirect("Jsp/Customer/CustomerHomePage.jsp");
            	response.sendRedirect("Jsp/Owner/OwnerHomePage.jsp");
    		}
    	} else {
        	response.sendRedirect("Jsp/Registration.jsp");
    	}
    	
	}

}
