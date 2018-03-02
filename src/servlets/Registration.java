package servlets;

import java.io.IOException;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import models.User;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private ServletContext sc;
	//private String path;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
    }
    
    protected void init(HttpServletRequest request, HttpServletResponse response) {
        //this.sc = this.getServletContext(); 
    	//this.path = sc.getRealPath("/WEB-INF/users.properties");
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
		//this.sc = this.getServletContext(); 
    	//this.path = sc.getRealPath("/WEB-INF/users.properties");
		//String userName = request.getParameter("userName"); 
    	//String password = request.getParameter("password");
    	//String userType = request.getParameter("userType");
    	//User user = new User(userName, password, userType);

    	// TODO CHANGE TO ACTUALLY REGISTER USER
    	// User.registerUser(user, this.path);
    	response.sendRedirect("Jsp/Login.jsp");
	}

}
