package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.ShowroomDB;
import data.TheatreDB;
import models.Showroom;
import models.User;
import utilities.SecurityUtilities;

/**
 * Servlet implementation class ViewShowrooms
 */
public class ViewShowrooms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewShowrooms() {
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
				String theatreName = request.getParameter("theatreName");
				int theatreId = TheatreDB.getTheatreIdByName(theatreName);	
				if (theatreId == -1) {
					theatreId = (int) session.getAttribute("theatreId");
				}
				session.setAttribute("theatreId", theatreId);
				ArrayList<Showroom> showrooms = ShowroomDB.getShowroomsByTheatreId(theatreId);
				request.setAttribute("showrooms", showrooms);
				request.setAttribute("theatreId", theatreId);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/ViewShowrooms.jsp");
		  	    dispatcher.forward(request, response);
			}
		}
	}

}
