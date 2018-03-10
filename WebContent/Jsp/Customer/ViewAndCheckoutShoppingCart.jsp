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
			<div class="btn btn-secondary">Hello, ${user.getFullName() }</div>
			<a href="${pageContext.request.contextPath}/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Shopping Cart</h1>
		<c:if test="${cartSize == 0}">
	        	<div class="row">You don't have anything in your shopping cart.</div>
	        </c:if>
	        <c:if test="${cartSize > 0}">
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
		        <c:forEach items="${cart}" var="current">
		        	<div class="row">
		        		<div class="col-sm-9">
				    		<c:forEach items="${current.getShowings()}" var="showing">
				    			<div class="row">
					    			<div class="col-sm-1">${showing.getMovie().getName()}</div>
						            <div class="col-sm-1">${showing.getShowroom().getTheatre().getName()}</div>
						            <div class="col-sm-1">${showing.getStartTime()}</div>
						            <div class="col-sm-1">${showing.getShowroom().getCapacity()}</div>
						            <div class="col-sm-1">${showing.getCost()}</div>
						            <div class="col-sm-3">${showing.getMovie().getThumbnailData()}</div>
						            <div class="col-sm-1">${current.getTicketsByMovie(showing.getMovie())}</div>
				    			</div>
							</c:forEach>
						</div>
				    	<div class="col-sm-1">Total Cost: ${current.getCost()}</div>
			        </div>
				</c:forEach>
			</c:if>
		<div class="row">
			<div class="offset-md-3 col-sm-3">
				Total: ${totalCost.cost}
			</div>
			<div class="offset-md-3 col-sm-3">
				<form name="item" method="POST" action="${pageContext.request.contextPath}/CustomerTransaction">
			        <input type="submit" class="btn btn-primary" value="Checkout">
			    </form>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
