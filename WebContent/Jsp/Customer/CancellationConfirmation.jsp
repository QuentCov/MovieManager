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
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View All Orders</a>
			<a href="ViewAndCheckoutShoppingCart.jsp" class="btn btn-primary">Checkout</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Cancelled</h1>
		<p>Your order has been cancelled and your account has been refunded ${current.getCost()}.</p>
		<div class="container">
	        <div class="row">
		        <div class="col-sm-1">Movie Title</div>
		        <div class="col-sm-1">Theatre</div>
		        <div class="col-sm-1">Start Time</div>
		        <div class="col-sm-1">Seats Available</div>
		        <div class="col-sm-1">Cost per Ticket</div>
		        <div class="col-sm-1">Poster</div>
				<div class="col-sm-1">Tickets Bought</div>
	        </div>
    		<div class="row">
	    		<c:forEach items="${cancelOrder.getMovies()}" var="movie">
		    		<div class="col-sm-1">${movie.getMovie().getName()}</div>
		            <div class="col-sm-1">${movie.getShowroom().getTheatre().getName()}</div>
		            <div class="col-sm-1">${movie.getStartTime()}</div>
		            <div class="col-sm-1">${movie.getShowroom().getCapacity()}</div>
		            <div class="col-sm-1">${movie.getCost()}</div>
		            <div class="col-sm-1">${movie.getMovie().getThumbnailFile()}</div>
		            <div class="col-sm-1">${current.getTicketsBoughtByMovie(movie)}</div>
		         </c:forEach>
	         </div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
