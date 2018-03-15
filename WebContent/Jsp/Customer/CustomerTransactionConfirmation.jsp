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
		<h3>Purchases</h3>
		<div class="container">
	        <div class="row">
	        	<div class="col-sm-2">Movies</div>
				<div class="col-sm-2">Tickets Bought</div>
				<div class="col-sm-2">Total Price</div>
	        </div>
	        <div class="row">
		        <c:forEach items="${completedOrder}" var="order">
	    			<c:forEach items="${order.getShowings()}" varStatus="loop" var="showing">
		    			<div class="col-sm-2"><c:out value="${showing.getMovie().getName()}"/></div>
		    			<div class="col-sm-2"><c:out value="${order.getTickets()[loop.index]}"/></div>
		        	</c:forEach>
		            <div class="col-sm-2"><c:out value="${order.getCost()}"/></div>
	            </c:forEach>
	    	</div>
		</div>
		<a href="#" class="btn btn-primary">Print your order details (COMING SOON!)</a>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
