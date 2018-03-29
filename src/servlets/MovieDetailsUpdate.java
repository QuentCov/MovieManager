package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import data.MovieDB;
import models.Movie;
import models.User;
import utilities.SecurityUtilities;

/**
 * Servlet implementation class MovieDetailsUpdate
 */
@MultipartConfig
public class MovieDetailsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDetailsUpdate() {
        super();
    }

    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
				int movieId = Integer.parseInt(request.getParameter("movieId"));
				String movieName = request.getParameter("movieName");
				String movieGenre = request.getParameter("movieGenre");		
				
				InputStream stream = null;
		        Part filePart = request.getPart("movieThumbnail");
				// set up variables to hold the thumbnail data
		        String movieThumbnailName = "";
				byte[] movieThumbnailData = new byte[Math.toIntExact(filePart.getSize())];
				// if there is an uploaded file read it into the variables
				// else use the existing movie's data
		        if (Math.toIntExact(filePart.getSize()) != 0) {
		            // store the data
		    		movieThumbnailName = getFileName(filePart);
		        	stream = filePart.getInputStream();
		    		stream.read(movieThumbnailData);
		    		stream.close();
		        } else {
		        	Movie existingMovie = MovieDB.getMovieById(movieId);
		        	movieThumbnailName = existingMovie.getThumbnailName();
		        	movieThumbnailData = existingMovie.getThumbnailData();
		        }
		                
				String movieDescription = request.getParameter("movieDescription");        
				int movieRuntime = Integer.parseInt(request.getParameter("movieRuntime"));
				String movieRating = request.getParameter("movieRating");
				
				Movie newMovie = new Movie(movieName, movieGenre, movieThumbnailName, movieThumbnailData, movieDescription, movieRuntime, movieRating);
				boolean result = MovieDB.updateMovieById(newMovie, movieId);
				if (result) {
					Movie movie = MovieDB.getMovieById(movieId);
					request.setAttribute("movie", movie);
					request.setAttribute("movieId", movieId);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/MovieDetails.jsp");
				    dispatcher.forward(request, response);
				} else {
					response.sendError(500, "Failed to add movie"); 
				}
			}
		}
	}

}
