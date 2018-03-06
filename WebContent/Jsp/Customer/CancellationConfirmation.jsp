<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancellation Confirmed</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="ViewOrders.jsp" class="btn btn-primary">View All Orders</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Cancelled</h1>
		<p>Your order has been cancelled and your account has been refunded ${current.getCost()}.</p>
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
	    		<c:forEach items="${cancelOrder.getMovies()}" var="movie">
		    		<td>${movie.getMovie().getName()}</td>
		            <td>${movie.getShowroom().getTheatre().getName()}</td>
		            <td>${movie.getStartTime()}</td>
		            <td>${movie.getShowroom().getCapacity()}</td>
		            <td>${movie.getCost()}</td>
		            <td>${movie.getMovie().getThumbnailFile()}</td>
		            <td>${current.getTicketsBoughtByMovie(movie)}</td>
		         </c:forEach>
	         </tr>
		</table>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
