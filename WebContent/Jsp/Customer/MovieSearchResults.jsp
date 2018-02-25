<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Search Results</title>
</head>
<body>
	<jsp:useBean id="allMovies" type="java.util.ArrayList<models.MovieShowing>" scope="session" />
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="ViewOrders.jsp" class="btn btn-primary">View Orders</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Search Results</h1>
			<p>You searched for "${searchString.string}"</p>
		<br>
		<table border="1">
	        <tr>
	            <td>Movie Name:</td>
	            <td>Theatre Name:</td>
	            <td>Showtime:</td>
	            <td>Available Seats:</td>
	            <td>Price:</td>
	            <td>Thumbnail:</td>
	        </tr>
	        <c:forEach items="${allMovies}" var="current">
		    	<tr>
		    		<td>current.getMovie().getName()></td>
		            <td>current.getShowroom().getTheatre().getName()></td>
		            <td>current.getStartTime()></td>
		            <td>current.getShowroom().getCapacity()></td>
		            <td>current.getCost()></td>
		            <td>current.getMovie().getThumbnailFile()</td>
		            <!-- TODO: Add the options for each -->
		    	</tr>
		    </c:forEach>
	    </table>
    </div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
