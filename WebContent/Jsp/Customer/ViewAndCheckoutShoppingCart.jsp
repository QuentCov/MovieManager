<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Shopping Cart</title>
</head>
<body>
	<!-- <jsp:useBean id="allOrders" type="java.util.ArrayList<models.Order>" scope="session" /> -->
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Shopping Cart</h1>
		<table border="1">
	        <tr>
		        <td>Movie Details</td>
				<td>Tickets Bought</td>
				<td>Total Price</td>
				<td>Actions</td>
	        </tr>
	        <c:forEach items="${allOrders}" var="current">
		    	<tr>
		    		<tr>
			    		<c:forEach items="${current.getMovies()}" var="movie">
				    		<td>movie.getMovie().getName()></td>
				            <td>movie.getShowroom().getTheatre().getName()></td>
				            <td>movie.getStartTime()></td>
				            <td>movie.getShowroom().getCapacity()></td>
				            <td>movie.getCost()></td>
				            <td>movie.getMovie().getThumbnailFile()</td>
				            <td>current.getTicketsBoughtByMovie(movie)</td>
				            <!-- TODO: Add the options for each -->
				         </c:forEach>
			         </tr>
			         <td>${current.getTotalPrice()}</td>
			         <!-- TODO: Add Actions -->
		        </tr>
			</c:forEach>
		</table>
		<div class="row">
			<div class="offset-md-3 col-sm-3">
				Total: ${totalCost.cost}
			</div>
			<div class="offset-md-3 col-sm-3">
				<a href="CustomerTransaction.jsp" class="btn btn-success">Checkout</a>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
