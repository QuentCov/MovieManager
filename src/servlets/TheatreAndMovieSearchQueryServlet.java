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
 * Servlet implementation class TheatreAndMovieSearchQueryServlet
 */
public class TheatreAndMovieSearchQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheatreAndMovieSearchQueryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//TODO: Discuss search queries with David.
		String name = (String) session.getAttribute("searchString");
		
		Movie movie = MovieDB.getMovie(name);
		if(movie != null) {
			session.setAttribute("movie", movie);
		}
		
		session.setAttribute("results", "Movie Not Found");
		response.sendRedirect("MovieSearchResults.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
