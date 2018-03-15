package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import data.MovieDB;
import models.Movie;
import utilities.SecurityUtilities;

/**
 * Servlet implementation class AddMovie
 */
@MultipartConfig
public class AddMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovie() {
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
		String movieName = request.getParameter("movieName");
		String movieGenre = request.getParameter("movieGenre");
		
		//Filter the strings.
    	ArrayList<String> parameters = new ArrayList<String>();
    	parameters.add(movieName); //0
    	parameters.add(movieGenre); //1
    	
    	parameters = SecurityUtilities.filterStrings(parameters);
		
		InputStream stream = null;
        Part filePart = request.getPart("movieThumbnail");
		// obtains the uploaded file name and data
		String movieThumbnailName = getFileName(filePart);
		byte[] movieThumbnailData = new byte[Math.toIntExact(filePart.getSize())];
        if (filePart != null) {
            // get the input stream and read the data into the byte array
        	stream = filePart.getInputStream();
    		stream.read(movieThumbnailData);
    		stream.close();
        }
		String movieDescription = request.getParameter("movieDescription");
		int movieRuntime = Integer.parseInt(request.getParameter("movieRuntime"));
		String movieRating = request.getParameter("movieRating");
		
		Movie newMovie = new Movie(parameters.get(0), parameters.get(1), movieThumbnailName, movieThumbnailData, movieDescription, movieRuntime, movieRating);
		boolean result = MovieDB.addMovie(newMovie);
		if (result) {
			response.sendRedirect("Jsp/Owner/OwnerHomePage.jsp");
		} else {
			response.sendError(500, "Failed to add movie"); 
		}
	}

}
