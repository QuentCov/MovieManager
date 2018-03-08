package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MovieDB;
import data.MovieShowingDB;
import data.ShowroomDB;
import data.TheatreDB;
import models.Movie;
import models.MovieShowing;
import models.Showroom;

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
		String movieName = request.getParameter("movie");
		String showingDate = request.getParameter("showingDate");
		String showingTime = request.getParameter("showingTime");
		Double cost = Double.parseDouble(request.getParameter("cost"));
		int showroomId = Integer.parseInt(request.getParameter("showroomId"));		
		
		Movie movie = MovieDB.getMovieByName(movieName);
		Showroom showroom = ShowroomDB.getShowroomById(showroomId);
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
		Date startDateTime = new Date();
		try {
			startDateTime = format.parse(showingDate + " " + showingTime);
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
