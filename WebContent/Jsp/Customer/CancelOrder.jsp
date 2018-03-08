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
			<div class="btn btn-secondary">Hello, ${user.getFullName() }</div>
			<a href="${pageContext.request.contextPath}/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="${pageContext.request.contextPath}/ViewAndCheckoutShoppingCart.jsp" class="btn btn-primary">Checkout</a>
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
       		<div class="col-sm-9">
    			<div class="row">
	    			<div class="col-sm-1">${cancelShowingItem.getMovie().getName()}</div>
		            <div class="col-sm-1">${cancelShowingItem.getShowroom().getTheatre().getName()}</div>
		            <div class="col-sm-1">${cancelShowingItem.getStartTime()}</div>
		            <div class="col-sm-1">${cancelShowingItem.getShowroom().getCapacity()}</div>
		            <div class="col-sm-1">${cancelShowingItem.getCost()}</div>
		            <div class="col-sm-3">${cancelShowingItem.getMovie().getThumbnailData()}</div>
		            <div class="col-sm-1">${cancelOrder.getTicketsByMovie(cancelShowingItem.getMovie())}</div>
    			</div>
			</div>
        </div>
        <form name="item" action="${pageContext.request.contextPath}/CancelOrderTransaction">
        	<input type="hidden" name="movie" value="${cancelShowingItem.getMovie().getName()}"/>
        	<input type="hidden" name="order" value="${cancelOrder.getID()}"/>
        	<input type="submit" class="btn btn-primary" value="Cancel Item">
        </form>
		<form name="item" action="${pageContext.request.contextPath}/ViewAndCheckoutShoppingcart.jsp">
        	<input type="submit" class="btn btn-primary" value="Discard Cancellation">
        </form>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
