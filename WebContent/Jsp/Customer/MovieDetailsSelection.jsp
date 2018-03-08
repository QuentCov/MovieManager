<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movies Details Selection</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="${pageContext.request.contextPath}/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="${pageContext.request.contextPath}/ViewAndCheckoutShoppingCart.jsp" class="btn btn-primary">Checkout</a>
			<a href="${pageContext.request.contextPath}/MovieSearchResults.jsp" class="btn btn-primary">Back to search</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Details:</h1>
		<br>
		<div class="row">
		    <div class="col-sm-1">Movie Title</div>
		    <div class="col-sm-1">Description</div>
		    <div class="col-sm-1">Rating</div>
		    <div class="col-sm-3">Poster</div>
		    <div class="col-sm-1">Theatre</div>
		    <div class="col-sm-1">Start Time</div>
			<div class="col-sm-1">Cost per Ticket</div>
			<div class="col-sm-1">Tickets Available</div>
			<div class="col-sm-2">Actions</div>
		</div>
       	<div class="row">
       		<div class="col-sm-9">
	    		<c:forEach items="${current.getShowings()}" var="showing">
	    			<div class="row">
		    			<div class="col-sm-1">${showing.getMovie().getName()}</div>
			            <div class="col-sm-1">${showing.getMovie().getDescription()}</div>
			            <div class="col-sm-1">${showing.getMovie().getRating}</div>
			            <div class="col-sm-3">${showing.getMovie().getThumbnailFile()}</div>
			            <div class="col-sm-1">${showing.getShowroom().getTheatre().getName()}</div>
			            <div class="col-sm-1">${showing.getStartTime()}</div>
			            <div class="col-sm-1">${movie.getCost()}</div>
			            <div class="col-sm-1">${movie.getShowroom().getCapacity()}</div>
	    			</div>
				</c:forEach>
			</div>
	    	<div class="col-sm-2">
		    	<form name="item" action="CustomerReview">
		        	<input type='hidden' name='reviewMovie' value="${movie.getName()}">
		        	<input type="submit" class="btn btn-primary" value="Add Review">
		        </form>
		        <form name="item" action="UpdateShoppingCart">
		        	<input type='hidden' name='updateItem' value="${movie.getName()}">
		        	<input type='text' name='ticketCount' value="Ticket Count">
		        	<input type="submit" class="btn btn-primary" value="Add to Cart">
		        </form>
	        </div>
        </div>
		<h2>Viewer Reviews:</h2>
		<div class="row">Total Rating: ${movieShowing.getMovie().getAverageRating()}</div>
		<div class="row">
			<div class="col-sm-1">Reviewer</div>
			<div class="col-sm-1">Date</div>
			<div class="col-sm-1">Rating</div>
			<div class="col-sm-1">Review</div>
		</div>
   		<c:forEach items="${reviews}" var="review">
   		<div class="row">
   			<div class="col-sm-1">${review.getUser().getFullName()}</div>
		    <div class="col-sm-1">${review.getDate()}</div>
		    <div class="col-sm-1">${review.getRating()}</div>
		    <div class="col-sm-6">${review.getReview()}</div>
   		</div>
        </c:forEach>
	</div>
    <%@ include file="/_partials/scripts.html" %>
</body>
</html>
