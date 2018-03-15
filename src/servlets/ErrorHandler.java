package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//This class is a modified version of the example ErrorHandler found at:
//https://www.tutorialspoint.com/servlets/servlets-exception-handling.htm
public class ErrorHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	  */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {
			servletName = "Unknown";
	    }
		
	    String requestUri = (String)
	    request.getAttribute("javax.servlet.error.request_uri");
	      
	    if (requestUri == null) {
	    	requestUri = "Unknown";
	    }
	    
	    //Print out a sanitized version of the error, including only the type and message, or the status code.
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = "Error/Exception Information";
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>" + title + "</title");
	    out.println("</head>");
	    out.println("<body>");

	    out.println("<h2>We've got an error on our hands.</h2>");
        if (throwable == null && statusCode == null) {
	        out.println("<p>It seems Movie Manager has errored unexpectedly.</p>");
	       
	    } else if (statusCode != null) {
	        out.println("If you run into a programmer, tell them the status code is: " + statusCode);
	    } else {
	    	out.println("<br>");
	        out.println("<h3>Error information, if you know about that kind of thing: </h3>");
	        out.println("Exception Type : " + throwable.getClass( ).getName( ) + "</br></br>");
	        out.println("The exception message: " + throwable.getMessage( ));
	    }
        
        out.println("<p>Please, sign back into Movie Manager here: ");
	    out.println("<a href=\"" + response.encodeURL("http://localhost:8080/MovieManager/Jsp/Login.jsp") + "\">Login</a>.");
	    out.println("</body>");
	    out.println("</html>");
	}
	
	/**
	  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	  */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}