<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Shopping Cart</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Shopping Cart</h1>
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
	        <c:forEach items="${cart}" var="current">
		    		<c:forEach items="${current.getMovies()}" var="movie">
		    		<tr>
			    		<td>${movie.getMovie().getName()}</td>
			            <td>${movie.getShowroom().getTheatre().getName()}</td>
			            <td>${movie.getStartTime()}</td>
			            <td>${movie.getShowroom().getCapacity()}</td>
			            <td>${movie.getCost()}</td>
			            <td>${movie.getMovie().getThumbnailFile()}</td>
			            <td>${current.getTicketsBoughtByMovie(movie)}</td>
			         </tr>
			         </c:forEach>
			         <td>Total Price: ${current.getCost()}</td>
			         <form name="item" action="UpdateCard">
		            	<input type='hidden' name='itemIndex' value='<c:out value="${current.getID()}"/>'>
		            	<input type="submit" name="action" value="Remove Order">
			         </form>
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
