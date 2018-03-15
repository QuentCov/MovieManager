<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Search Results</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName()}"/></div>
			<a href="CustomerHomePage" class="btn btn-primary">Home</a>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="${pageContext.request.contextPath}/UpdateShoppingCart" class="btn btn-primary">Checkout</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
	    <div class="row">
			<div class="col-sm-2"><h4>Movie Name</h4></div>
			<div class="col-sm-1"><h4>Theatre Name</h4></div>
			<div class="col-sm-2"><h4>Showtime</h4></div>
			<div class="col-sm-1"><h4>Available Seats</h4></div>
			<div class="col-sm-1"><h4>Price</h4></div>
			<div class="col-sm-3"><h4>Thumbnail</h4></div>
			<div class="col-sm-2"><h4>Actions</h4></div>
		</div>
        <c:forEach items="${showings}" var="showing">
    		<div class="row">
				<div class="col-sm-2"><c:out value="${showing.getMovie().getName()}"/></div>
				<div class="col-sm-1"><c:out value="${showing.getShowroom().getName()}"/></div>
				<div class="col-sm-2"><c:out value="${showing.getStartTime()}"/></div>
				<div class="col-sm-1"><c:out value="${showing.getShowroom().getCapacity() - showing.getNumTicketsSold()}"/></div>
				<div class="col-sm-1"><c:out value="${showing.getCost()}"/></div>
				<div class="col-sm-3">
					<c:set var="data" value="${showing.getMovie().getThumbnailData()}"/>
					<c:choose>
						<c:when test="${empty data}">
							<td>Sorry! No thumbnail available</td>
						</c:when>
						<c:otherwise>
							<td><img class="img-fluid" src="data:image/jpeg;base64,${showing.getMovie.renderImage()}" alt="${showing.getMovie().getName()} Poster"/></td>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-sm-2">
					<form name="item" method="POST" action="${pageContext.request.contextPath}/MovieSearchResults">
		            	<input type='hidden' name='itemIndex' value='<c:out value="${showing.getID()}"/>'>
		            	<input type="submit" class="btn btn-primary" name="action" value="View More Details">
		            </form>
				</div>
			</div>
	    </c:forEach>
    </div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
