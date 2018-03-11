package servlets;

import java.io.IOException;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.AddressDB;
import data.UserDB;
import models.Address;
import models.User;

//import models.User;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
    }
    
    protected void init(HttpServletRequest request, HttpServletResponse response) {
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

		String userName = request.getParameter("userName"); 
    	String password = request.getParameter("password");
    	String userType = request.getParameter("userType");
    	String fullName = request.getParameter("fullName");
    	String phoneNumber = request.getParameter("phoneNumber");
    	
    	Address address = new Address();
    	
    	String address1 = request.getParameter("address1");
    	String address2 = request.getParameter("address2");
    	if(address2 == null) {
    		address2 = "";
    	}
    	
    	String city = request.getParameter("city");
    	String stateAbbreviation = request.getParameter("stateAbbreviation");
    	String zipCode = request.getParameter("zipCode");
    	
    	address.setAddress1(address1);
    	address.setAddress2(address2);
    	address.setCity(city);
    	address.setStateAbbreviation(stateAbbreviation);
    	address.setZipCode(zipCode);
    	
    	User user = new User();
    	user.setEmailAddress(userName);
    	user.setPassword(password);
    	user.setType(userType);
    	user.setFullName(fullName);
    	user.setPhoneNumber(phoneNumber);
    	user.setStreetAddress(address);
    	
    	AddressDB.addAddress(address);
    	UserDB.addUser(user);
    	response.sendRedirect("Jsp/Login.jsp");
	}

}
