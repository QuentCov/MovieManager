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
			<a href="${pageContext.request.contextPath}/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="${pageContext.request.contextPath}/ViewAndCheckoutShoppingCart.jsp" class="btn btn-primary">Checkout</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h3>Purchases</h3>
		<div class="container">
	        <div class="row">
	        	<div class="col-sm-2">Movies</div>
				<div class="col-sm-2">Tickets Bought</div>
				<div class="col-sm-2">Total Price</div>
	        </div>
	        <div class="row">
    			<c:forEach items="${order.getMovies()}" var="movie">
	    			<div class="col-sm-2">${movie.getMovie().getName()}</div>
	        	</c:forEach>
	            <div class="col-sm-2">${order.getTicketCount()}</div>
	            <div class="col-sm-2">${order.getTotalCost}</div>
	    	</div>
		</div>
		<a href="#" class="btn btn-primary">Print your order details (COMING SOON!)</a>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
