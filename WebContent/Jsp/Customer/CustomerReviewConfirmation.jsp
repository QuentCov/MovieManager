<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Review Submitted</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="ViewAndCheckoutShoppingCart.jsp" class="btn btn-primary">Checkout</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h3>Thank you for your review!</h3>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>