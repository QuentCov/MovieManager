package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.TheatreDB;
import data.UserDB;
import models.Theatre;
import models.User;
import utilities.SecurityUtilities;

/**
 * Servlet implementation class ViewTheatreDetails
 */
public class ViewTheatreDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTheatreDetails() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(!SecurityUtilities.loggedInOwner(user)) {
			response.sendError(403);
		} else {
		
			//Verify the session.
			String sessionToken = (String) session.getAttribute("CSRFToken");
			String requestToken = request.getParameter("CSRFToken");
			
			if(!sessionToken.equals(requestToken)) {
				response.sendError(403, "Possible CSRF attack detected.");
			} else {
				User owner = (User) session.getAttribute("user");
				int ownerId = UserDB.getUserIdByEmailAddress(owner.getEmailAddress());
				ArrayList<Theatre> theatres = TheatreDB.getTheatresByOwnerId(ownerId);
				request.setAttribute("theatres", theatres);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/ViewTheatreDetails.jsp");
		  	    dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
