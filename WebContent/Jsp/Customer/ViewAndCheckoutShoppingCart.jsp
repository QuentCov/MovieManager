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
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName()}"/></div>
			<a href="Jsp/Customer/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
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
			</div>
	        <c:forEach items="${cart}" var="current">
	    		<c:forEach items="${current.getShowings()}" var="showing">
	    			<div class="row">
		    			<div class="col-sm-1"><c:out value="${showing.getMovie().getName()}"/></div>
			            <div class="col-sm-1"><c:out value="${showing.getShowroom().getTheatre().getName()}"/></div>
			            <div class="col-sm-1"><c:out value="${showing.getStartTime()}"/></div>
			            <div class="col-sm-1"><c:out value="${showing.getShowroom().getCapacity() - showing.getNumTicketsSold()}"/></div>
			            <div class="col-sm-1"><c:out value="${showing.getCost()}"/></div>
			            <div class="col-sm-3">
			            	<c:set var="data" value="${movie.getThumbnailData()}"/>
							<c:choose>
								<c:when test="${empty data}">
									<td>Sorry! No thumbnail available</td>
								</c:when>
								<c:otherwise>
									<td><img class="img-fluid" src="data:image/jpeg;base64,${movie.renderImage()}" alt="${movie.getName()} Poster"/></td>
								</c:otherwise>
							</c:choose>
			            </div>
			            <div class="col-sm-1"><c:out value="${current.getTicketsByMovie(showing.getMovie())}"/></div>
			            <div class="col-sm-1"><c:out value="${showing.getCost() * current.getTicketsByMovie(showing.getMovie())}"/></div>
					</div>
				</c:forEach>
			</c:forEach>
			<div class="row">
				<div class="offset-md-8 col-sm-3">
					<form name="item" method="POST" action="${pageContext.request.contextPath}/CustomerTransaction">
						<input type="hidden" name="CSRFToken" value="${CSRFToken}">
				        <input type="submit" class="btn btn-primary" value="Checkout">
				    </form>
				</div>
			</div>
		</c:if>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
