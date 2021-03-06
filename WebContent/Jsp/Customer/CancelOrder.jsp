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
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Customer/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<form action="${pageContext.request.contextPath}/ViewOrders">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="View Orders">
			</form>
			<form action="${pageContext.request.contextPath}/UpdateShoppingCart">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="Checkout">
			</form>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Do you want to cancel your tickets for this movie?</h1>
		<div class="row">
		    <div class="col-sm-1">Movie Title</div>
		    <div class="col-sm-1">Theatre</div>
		    <div class="col-sm-1">Start Time</div>
		    <div class="col-sm-1">Seats Available</div>
		    <div class="col-sm-1">Cost per Ticket</div>
		    <div class="col-sm-3">Poster</div>
			<div class="col-sm-1">Tickets Bought</div>
			<div class="col-sm-2">Actions</div>
		</div>
 		<div class="row">
	  		<div class="col-sm-1"><c:out value="${cancelShowingItem.getMovie().getName()}"/></div>
	           <div class="col-sm-1"><c:out value="${cancelShowingItem.getShowroom().getTheatre().getName()}"/></div>
	           <div class="col-sm-1"><c:out value="${cancelShowingItem.getStartTime()}"/></div>
	           <div class="col-sm-1"><c:out value="${cancelShowingItem.getShowroom().getCapacity()}"/></div>
	           <div class="col-sm-1"><c:out value="${cancelShowingItem.getCost()}"/></div>
	           <c:set var="data" value="${showing.getMovie().getThumbnailData()}"/>
			<c:choose>
				<c:when test="${empty data}">
					<div class="col-sm-3">Sorry! No thumbnail available.</div>
				</c:when>
				<c:otherwise>
					<div class="col-sm-3"><img class="img-fluid" src="data:image/jpeg;base64,${showing.getMovie().renderImage()}" alt="${showing.getMovie().getName()} Poster"/></div>
				</c:otherwise>
			</c:choose>
	        <div class="col-sm-1"><c:out value="${cancelOrder.getTicketsByMovie(cancelShowingItem.getMovie())}"/></div>
	        <form name="item" method="POST" action="${pageContext.request.contextPath}/CancelOrderTransaction">
	        	<input type="hidden" name="CSRFToken" value="${CSRFToken}">
	        	<input type="hidden" name="movie" value="${cancelShowingItem.getMovie().getName()}"/>
	        	<input type="hidden" name="order" value="${cancelOrder.getID()}"/>
	        	<input type="submit" class="btn btn-primary" value="Cancel Item">
		    </form>
			<form name="item" method="POST" action="${pageContext.request.contextPath}/UpdateShoppingCart">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
	        	<input type="submit" class="btn btn-primary" value="Discard Cancellation">
	        </form>
		</div>
 	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
