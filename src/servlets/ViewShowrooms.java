package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ShowroomDB;
import data.TheatreDB;
import models.Showroom;

/**
 * Servlet implementation class ViewShowrooms
 */
public class ViewShowrooms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewShowrooms() {
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
		String theatreName = request.getParameter("theatreName");
		ArrayList<Showroom> showrooms = ShowroomDB.getShowroomsByTheatreId(TheatreDB.getTheatreIdByName(theatreName));
		request.setAttribute("showrooms", showrooms);
		request.setAttribute("theatreId", TheatreDB.getTheatreIdByName(theatreName));
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/Owner/ViewShowrooms.jsp");
  	    dispatcher.forward(request, response);
	}

}
