package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MovieShowingDB;
import data.ShowroomDB;
import models.MovieShowing;

/**
 * Servlet implementation class ManageShowtimes
 */
public class ManageShowtimes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageShowtimes() {
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
		String showroomName = request.getParameter("showroomName");		
		int theatreId = Integer.parseInt(request.getParameter("theatreId"));		
		ArrayList<MovieShowing> showings = MovieShowingDB.getMovieShowingsByShowroomId(ShowroomDB.getShowroomIdByName(showroomName));
		request.setAttribute("showings", showings);
		request.setAttribute("theatreId", theatreId);
		request.setAttribute("showroomId", ShowroomDB.getShowroomIdByName(showroomName));
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/ManageShowtimes.jsp");
  	    dispatcher.forward(request, response);
	}
}
