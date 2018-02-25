<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancel Order</title>
</head>
<body>
	<!-- <jsp:useBean id="orders" type="models.Order" scope="session" /> -->
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>View Your Orders</h1>
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
		         <td>${current.getTotalPrice()}</td>
	         </tr>
		</table>
		<div class="offset-md-3 col-sm-3">
			<a href="CancellationConfirmation" class="btn btn-success">Confirm Cancellation</a>
		</div>
		<div class="offset-md-3 col-sm-3">
			<a href="ViewAndCheckoutShoppingCart" class="btn btn-success">Discard Cancellation</a>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
