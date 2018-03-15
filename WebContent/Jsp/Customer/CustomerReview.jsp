<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Customer Review</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="${pageContext.request.contextPath}/UpdateShoppingCart" class="btn btn-primary">Checkout</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<div class="row"><h1>Review for: <c:out value="${movie.getName()}"/></h1></div>
		<c:set var="data" value="${Review_Too_Long}"/>
		<c:choose>
			<c:when test="${empty data}">
				<form method="POST" action="${pageContext.request.contextPath}/CustomerReview">
					<div class="form-group">
					  <label for="review">Tell us what you thought:</label>
					  <textarea class="form-control" rows="5" id="reivew" name="review"></textarea>
					</div>
					<div class="row">
						<h5>Rating:</h5>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="1">
						</div>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="2">
						</div>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="3">
						</div>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="4">
						</div>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="5">
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Submit Review">
				</form>
			</c:when>
			<c:otherwise>
				<form method="POST" action="${pageContext.request.contextPath}/CustomerReview">
					<div class="form-group">
					  <label for="review">Please keep reviews under 1000 characters:</label>
					  <textarea class="form-control" rows="5" id="reivew"></textarea>
					</div>
					<div class="row">
						<h5>Rating:</h5>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="1">
						</div>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="2">
						</div>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="3">
						</div>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="4">
						</div>
						<div class="col-xs-1">
							<input type="radio" name="rating" value="5">
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Submit Review">
				</form>
			</c:otherwise>
		</c:choose>
		
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>