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
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="ViewAndCheckoutShoppingCart.jsp" class="btn btn-primary">Checkout</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Manage Your Order</h1>
		<div class="row">
		    <div class="col-sm-1">Movie Title</div>
		    <div class="col-sm-1">Theatre</div>
		    <div class="col-sm-1">Start Time</div>
		    <div class="col-sm-1">Seats Available</div>
		    <div class="col-sm-1">Cost per Ticket</div>
		    <div class="col-sm-3">Poster</div>
			<div class="col-sm-1">Tickets Bought</div>
			<div class="col-sm-1">Total Cost</div>
			<div class="col-sm-2">Actions</div>
		</div>
       	<div class="row">
       		<div class="col-sm-9">
	    		<c:forEach items="${order.getShowings()}" var="showing">
	    			<div class="row">
		    			<div class="col-sm-1">${showing.getMovie().getName()}</div>
			            <div class="col-sm-1">${showing.getShowroom().getTheatre().getName()}</div>
			            <div class="col-sm-1">${showing.getStartTime()}</div>
			            <div class="col-sm-1">${showing.getShowroom().getCapacity()}</div>
			            <div class="col-sm-1">${showing.getCost()}</div>
			            <div class="col-sm-3">${showing.getMovie().getThumbnailData()}</div>
			            <div class="col-sm-1">${current.getTicketsByMovie(showing.getMovie())}</div>
	    			</div>
	    			<div class="col-sm-2">
				    	<form name="item" action="${pageContext.request.contextPath}/CancelOrder" >
				    		<input type="hidden" name="showing" value="${showing}"/>
				    		<input type="hidden" name="movie" value="${showing.getMovie().getName()}"/>
				    		<input type="hidden" name="order" value="${order.getID()}"/>
				            <input type="submit" class="btn btn-primary" value="Cancel Item">
				        </form>
				    </div>
				</c:forEach>
			</div>
	    	<div class="col-sm-1">Total Cost: ${current.getCost()}</div>
        </div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
