<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancellation Confirmed</title>
</head>
<body>
	<!-- <jsp:useBean id="orders" type="models.Order" scope="session" /> -->
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="ViewOrders.jsp" class="btn btn-primary">View All Orders</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Cancelled</h1>
		<p>Your order has been cancelled and your account has been refunded ${current.getTotalPrice()}.</p>
		<table border="1">
	        <tr>
		        <td>Movie Details</td>
				<td>Total Price</td>
				<td>Actions</td>
	        </tr>
    		<tr>
	    		<c:forEach items="${orders.getMovies()}" var="movie">
		    		<td>movie.getMovie().getName()></td>
		            <td>movie.getShowroom().getTheatre().getName()></td>
		            <td>movie.getStartTime()></td>
		            <td>movie.getShowroom().getCapacity()></td>
		            <td>movie.getCost()></td>
		            <td>movie.getMovie().getThumbnailFile()</td>
		            <td>current.getTicketsBoughtByMovie(movie)</td>
		            <!-- TODO: Add the options for each -->
		         </c:forEach>
		         <td>current.getTicketsBoughtByMovie(movie)</td>
	         </tr>
		</table>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
