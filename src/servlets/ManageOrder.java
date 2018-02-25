package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieDB;
import models.Movie;

/**
 * Servlet implementation class ManageOrders
 */
public class ManageOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//TODO: Make sure this works like I think it does.
		String movieName = request.getParameter("itemIndex");
		Movie movie = MovieDB.getMovie(movieName);
		if(movie != null) {
			//TODO: This isn't even used in ManageOrder, because it shows the entire order. 
			//If there's no point, then just redirect. Discuss with David.
			session.setAttribute("movie", movie);
			response.sendRedirect("ManageOrder.jsp");
		}
		response.sendError(500, "Unable to retrieve movie");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
