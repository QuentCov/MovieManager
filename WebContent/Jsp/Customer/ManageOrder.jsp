<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Manage Order</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Manage Your Orders</h1>
		<table border="1">
	        <tr>
		        <td>Movie Title</td>
		        <td>Theatre</td>
		        <td>Start Time</td>
		        <td>Seats Available</td>
		        <td>Cost per Ticket</td>
		        <td>Poster</td>
				<td>Tickets Bought</td>
				<td>Actions</td>
	        </tr>
	        <c:forEach items="${allOrders}" var="current">
		    	<tr>
		    		<c:forEach items="${current.getMovies()}" var="movie">
			    		<td>movie.getMovie().getName()></td>
			            <td>movie.getShowroom().getTheatre().getName()></td>
			            <td>movie.getStartTime()></td>
			            <td>movie.getShowroom().getCapacity()></td>
			            <td>movie.getCost()></td>
			            <td>movie.getMovie().getThumbnailFile()</td>
			            <td>current.getTicketsBoughtByMovie(movie)</td>
			            <form name="item" method="POST" action="CartController">
			            	<input type='hidden' name='itemIndex' value='<c:out value="${movie.getName()}"/>'>
			            	<input type="submit" name="action" value="View">
			            </form>
			         </c:forEach>
			         <td>Order Cost: current.getCost()</td>
		        </tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
