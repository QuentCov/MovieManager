package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Users;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	private String path;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // Auto-generated constructor stub
    }
    
    protected void init(HttpServletRequest request, HttpServletResponse response) {
        this.sc = this.getServletContext(); 
    	this.path = sc.getRealPath("/WEB-INF/users.properties");
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
		this.sc = this.getServletContext(); 
    	this.path = sc.getRealPath("/WEB-INF/users.properties");
		String userName = request.getParameter("userName"); 
    	String password = request.getParameter("password");
    	Users user = new Users(userName, password);
    	// check if valid user
    	if (Users.isValidUser(user, this.path)) {
    		// check user type to determine where to redirect
    		if (Users.getUserType(user, this.path).equals("Customer")) {
            	response.sendRedirect("Jsp/Customer/CustomerHomePage.jsp");    			
    		} else {
            	response.sendRedirect("Jsp/Owner/OwnerHomePage.jsp");
    		}
    	} else {
        	response.sendRedirect("Jsp/Registration.jsp");
    	}
	}

}
