package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MovieDB;

/**
 * Servlet implementation class AddShowtime
 */
public class AddShowtime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShowtime() {
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
		String showroomId = request.getParameter("showroomId");		
		String theatreId = request.getParameter("theatreId");		
		request.setAttribute("showroomId", showroomId);
		request.setAttribute("theatreId", theatreId);
		request.setAttribute("movies", MovieDB.getAllMovies());
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/AddShowtime.jsp");
  	    dispatcher.forward(request, response);
	}

}
