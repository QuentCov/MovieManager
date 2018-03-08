<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Customer Review</title>
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
		<div class="row"><h1>Review for: ${movie.getName()}</h1></div>
		<form action="${pageContext.request.contextPath}/CustomerReviewConfirmation">
			<div class="form-group">
			  <label for="review">Tell us what you thought:</label>
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
			<a href="${pageContext.request.contextPath}/CustomerReview" class="btn btn-primary">Submit Review</a>
		</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>