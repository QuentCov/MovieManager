package servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserDB;
import models.User;
import utilities.SecurityUtilities;

/**
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		String emailAddress = request.getParameter("userName"); 
    	String oldPassword = request.getParameter("oldPassword");
    	String newPassword = request.getParameter("newPassword");
    	    	
    	User user = UserDB.getUserByEmailAddress(emailAddress);
    	if(user != null) {
    		// if existing password is the same as the hash of the given old password then they match
    		//      update the password in the db
    		if(Arrays.equals(user.getPassword(), SecurityUtilities.hashString(oldPassword) )) {
    			user.setPassword(SecurityUtilities.hashString(newPassword));
    			UserDB.updateUserPassword(user);
            	response.sendRedirect("Jsp/Login.jsp");
    		} else {
    			response.sendError(403, "Old password does not match");
    		}
    	} else {
			response.sendError(500, "Could not update password");
    	}
    	
	}

}
