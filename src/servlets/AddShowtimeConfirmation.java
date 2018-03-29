package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieDB;
import data.MovieShowingDB;
import data.ShowroomDB;
import data.TheatreDB;
import models.Movie;
import models.MovieShowing;
import models.Showroom;
import models.User;
import utilities.SecurityUtilities;

/**
 * Servlet implementation class AddShowtimeConfirmation
 */
public class AddShowtimeConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShowtimeConfirmation() {
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
			
				String movieName = request.getParameter("movie");
				String showingDate = request.getParameter("showingDate");
				String showingTime = request.getParameter("showingTime");
				String costString = request.getParameter("cost");
				String showroomIdString = request.getParameter("showroomId");
				
				//Filter the strings.
		    	ArrayList<String> parameters = new ArrayList<String>();
		    	parameters.add(movieName); //0
		    	parameters.add(showingDate); //1
		    	parameters.add(showingTime); //2
		    	parameters.add(costString); //3
		    	parameters.add(showroomIdString); //4
		    	
		    	parameters = SecurityUtilities.filterStrings(parameters);
		    	
				Double cost = Double.parseDouble(parameters.get(3));
				int showroomId = Integer.parseInt(parameters.get(4));		
				
				Movie movie = MovieDB.getMovieByName(parameters.get(0));
				Showroom showroom = ShowroomDB.getShowroomById(showroomId);
				
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
				Date startDateTime = new Date();
				try {
					startDateTime = format.parse(parameters.get(1) + " " + parameters.get(2));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long start = startDateTime.getTime();
				long end = (start + (movie.getRuntime() * 60000));
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(end);
				Date endDateTime = calendar.getTime();
				
				
				MovieShowing showing = new MovieShowing(movie, showroom, startDateTime, endDateTime, 0, cost);
				boolean result = MovieShowingDB.addMovieShowing(showing);
				if (result) {
					request.setAttribute("result", "Successfully Added Showing");
					int theatreId = Integer.parseInt(request.getParameter("theatreId"));
					request.setAttribute("theatreName", TheatreDB.getTheatreById(theatreId).getName());
					request.setAttribute("movieName", movie.getName());
					request.setAttribute("showroomName", showroom.getName());
					request.setAttribute("runtime", startDateTime.toString() + " - " + endDateTime.toString());
					RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/AddShowtimeConfirmation.jsp");
			  	    dispatcher.forward(request, response);
				} else {
					response.sendError(500, "Failed to add showtime"); 
				}
			
			}
		}
	}

}
