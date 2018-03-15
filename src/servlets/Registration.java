package servlets;

import java.io.IOException;
import java.util.ArrayList;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.AddressDB;
import data.UserDB;
import models.Address;
import models.User;
import utilities.SecurityUtilities;

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
    	
    	//Filter the strings.
    	ArrayList<String> parameters = new ArrayList<String>();
    	parameters.add(userName); //0
    	parameters.add(password); //1
    	parameters.add(userType); //2
    	parameters.add(fullName); //3
    	parameters.add(phoneNumber); //4
    	parameters.add(address1); //5
    	parameters.add(address2); //6
    	parameters.add(city); //7
    	parameters.add(stateAbbreviation); //8
    	parameters.add(zipCode); //9
    	
    	parameters = SecurityUtilities.filterStrings(parameters);
    	
    	address.setAddress1(parameters.get(5));
    	address.setAddress2(parameters.get(6));
    	address.setCity(parameters.get(7));
    	address.setStateAbbreviation(parameters.get(8));
    	address.setZipCode(parameters.get(9));
    	
    	User user = new User();
    	user.setEmailAddress(parameters.get(0));
    	user.setPassword(SecurityUtilities.hashString(parameters.get(1)));
    	user.setType(parameters.get(2));
    	user.setFullName(parameters.get(3));
    	user.setPhoneNumber(parameters.get(4));
    	user.setStreetAddress(address);
    	
    	AddressDB.addAddress(address);
    	UserDB.addUser(user);
    	response.sendRedirect("Jsp/Login.jsp");
	}

}
