package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

/**
 * Servlet implementation class Registration
 */
@WebServlet("Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext sc;
	String path;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
    }
    
    protected void init(HttpServletRequest request, HttpServletResponse response) {
    	sc = this.getServletContext(); 
    	path = sc.getRealPath("/WEB-INF/users.properties"); 
    	
    	String userName = request.getParameter("userName"); 
    	String password = request.getParameter("password");
    	data.Users user = new data.Users(userName, password);
    	user.registerUser(user, path);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName"); 
    	String password = request.getParameter("password");
    	data.Users user = new data.Users(userName, password);
    	user.registerUser(user, path);
    	response.sendRedirect("Login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName"); 
    	String password = request.getParameter("password");
    	data.Users user = new data.Users(userName, password);
    	user.registerUser(user, path);
    	response.sendRedirect("Login");
	}

}
