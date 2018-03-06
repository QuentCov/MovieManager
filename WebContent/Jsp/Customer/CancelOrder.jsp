<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancel Order</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Do you want to cancel your tickets for this movie??</h1>
		<table border="1">
	        <tr>
		        <td>Movie Title</td>
		        <td>Theatre</td>
		        <td>Start Time</td>
		        <td>Seats Available</td>
		        <td>Cost per Ticket</td>
		        <td>Poster</td>
				<td>Tickets Bought</td>
	        </tr>
    		<tr>
	    		<td>${movie.getMovie().getName()}</td>
	            <td>${movie.getShowroom().getTheatre().getName()}</td>
	            <td>${movie.getStartTime()}</td>
	            <td>${movie.getShowroom().getCapacity()}</td>
	            <td>${movie.getCost()}</td>
	            <td>${movie.getMovie().getThumbnailFile()}</td>
	            <td>${order.getTicketsBoughtByMovie(movie)}</td>
	         </tr>
		</table>
		<div class="offset-md-3 col-sm-3">
			<a href="CancelOrder" class="btn btn-success">Confirm Cancellation</a>
		</div>
		<div class="offset-md-3 col-sm-3">
			<a href="ViewAndCheckoutShoppingCart" class="btn btn-success">Discard Cancellation</a>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
