<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Transaction Confirmed</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="ViewOrders.jsp" class="btn btn-primary">View Orders</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h3>Purchases</h3>
		<table border="1">
	        <tr>
		        <td>Movies</td>
				<td>Tickets Bought</td>
				<td>Total Price</td>
	        </tr>
	        <tr>
    			<c:forEach items="${order.getMovies()}" var="movie">
	    			<td>${movie.getMovie().getName()}</td>
	        	</c:forEach>
	            <td>${order.getTicketCount()}</td>
	            <td>${order.getTotalCost}</td>
	    	</tr>
		</table>
		<a href="PrintOrder" class="btn btn-primary">Print your order details</a>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
