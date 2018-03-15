package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MovieDB;
import data.MovieShowingDB;
import data.TheatreDB;
import models.Movie;
import models.MovieShowing;
import models.Theatre;

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
		
		String name = null;
		name = request.getParameter("theatre");

		if(name == null) {
			name = request.getParameter("date");
			if(name != null) {
				//Date searching.
				ArrayList<MovieShowing> showings = MovieShowingDB.getAllShowings();
				ArrayList<MovieShowing> hits = new ArrayList<MovieShowing>();
				for(int i = 0; i < showings.size(); i++) {
					String date2 = new SimpleDateFormat("yyyy-MM-dd").format(showings.get(i).getStartTime());
					if(name.equals(date2)) {
						hits.add(showings.get(i));
					}
				}
				
				session.setAttribute("type", "date");
				if(hits.isEmpty()) {
					session.setAttribute("showings", null);
				} else {
					session.setAttribute("showings", hits);
				}
				
			} else {
				//Movie Name Searching.
				name = request.getParameter("movieSearchString");
				if(name == null) {
					name = (String) session.getAttribute("searchString");
				} else {
					session.setAttribute("searchString", name);
				}
				ArrayList<Movie> movies = MovieDB.searchMoviesByName(name);
				ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
				int movieId = 0;
				
				//Get all showings of each movie, if we have any.
				if(movies == null) {
					session.setAttribute("type", "movie");
					session.setAttribute("showings", null);
				} else {
					for(int i = 0; i < movies.size(); i++) {
						movieId = movies.get(i).getID();
						showings.addAll(MovieShowingDB.getMovieShowingsByMovieId(movieId));
					}
					
					session.setAttribute("showings", showings);
					session.setAttribute("type", "movie");
				}
			}
		}
		else {
			//Theatre Searching.
			ArrayList<Theatre> theatres = TheatreDB.searchTheatreByName(name);
			ArrayList<MovieShowing> showings = new ArrayList<MovieShowing>();
			for(int i = 0; i < theatres.size(); i++) {
				showings.addAll(MovieShowingDB.getMovieShowingsByTheatre(theatres.get(i)));
			}
			
			session.setAttribute("showings", showings);
			session.setAttribute("type", "theatre");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Customer/MovieSearchResults.jsp");
  	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
