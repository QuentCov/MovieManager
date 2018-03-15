<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>View Orders</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<form action="${pageContext.request.contextPath}/UpdateShoppingCart">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="Checkout">
			</form>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>View All Orders</h1>
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
				            <div class="col-sm-1"><c:out value="${showing.getShowroom().getCapacity()}"/></div>
				            <div class="col-sm-1"><c:out value="${showing.getCost()}"/></div>
				            <c:set var="data" value="${showing.getMovie().getThumbnailData()}"/>
							<c:choose>
								<c:when test="${empty data}">
									<div class="col-sm-3">Sorry! No thumbnail available.</div>
								</c:when>
								<c:otherwise>
									<div class="col-sm-3"><img class="img-fluid" src="data:image/jpeg;base64,${showing.getMovie().renderImage()}" alt="${showing.getMovie().getName()} Poster"/></div>
								</c:otherwise>
							</c:choose>
				            <div class="col-sm-1">${current.getTicketsByMovie(showing.getMovie())}</div>
		    			</div>
					</c:forEach>
					<div class="row">
				    	<div class="offset-sm-9 col-sm-1">Total Cost: <c:out value="${current.getCost()}"/></div>
				    	<div class="col-sm-2">
					    	<form name="item" action="${pageContext.request.contextPath}/ManageOrders" >
					    		<input type="hidden" name="CSRFToken" value="${CSRFToken}">
					    		<input type="hidden" name="order" value="${current.getID()}"/>
					            <input type="submit" class="btn btn-primary" value="View Order">
					        </form>
				        </div>
			        </div>
				</c:forEach>
				<br>
				<div class="row">
					<div class="offset-sm-11 col-sm-1">
						<form name="item" action="${pageContext.request.contextPath}/UpdateShoppingCart">
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
