<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Cancellation Confirmed</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<a href="ViewOrders.jsp" class="btn btn-default">View All Orders</a>
			</div>
			<div class="offset-md-4 col-md-2">
				<a href="CustomerHomePage.jsp" class="btn btn-default">Home</a>
			</div>
			<div class="offset-md-1 col-md-2">
				<a href="/Login.jsp" class="btn btn-default">Sign Out</a>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				Your order has been cancelled and your account has been refunded $142.36.
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
