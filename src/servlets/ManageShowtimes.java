package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		int theatreId = (int) session.getAttribute("theatreId");
		String showroomName = request.getParameter("showroomName");
		int showroomId = ShowroomDB.getShowroomIdByName(showroomName);
		if (showroomId == -1) {
			showroomId = (int) session.getAttribute("showroomId");
		}
		ArrayList<MovieShowing> showings = MovieShowingDB.getMovieShowingsByShowroomId(showroomId);
		session.setAttribute("showroomId", showroomId);
		request.setAttribute("showings", showings);
		request.setAttribute("theatreId", theatreId);
		request.setAttribute("showroomId", showroomId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/ManageShowtimes.jsp");
  	    dispatcher.forward(request, response);
	}
}
