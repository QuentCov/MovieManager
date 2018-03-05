package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MovieShowingDB;

/**
 * Servlet implementation class CancelShowing
 */
public class CancelShowing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelShowing() {
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
		int showtimeId = Integer.parseInt(request.getParameter("showingId"));		
		request.setAttribute("showing", MovieShowingDB.getMovieShowingById(showtimeId));
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/CancelShowing.jsp");
  	    dispatcher.forward(request, response);
	}
}
