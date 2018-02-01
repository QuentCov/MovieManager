<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Cancellation Confirmed</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="ViewOrders.jsp" class="btn btn-primary">View All Orders</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Cancelled</h1>
		<p>Your order has been cancelled and your account has been refunded $142.36.</p>
		<div class="row">
			<div class="col-sm-4"><h5>Order Number</h5></div>
			<div class="col-sm-4"><h5>Movie</h5></div>
			<div class="col-sm-4"></div>
		</div>
		<div class="row">
			<div class="col-sm-4">Order #16</div>
			<div class="col-sm-4">Bee Movie</div>
			<div class="col-sm-4">Movie Cancelled</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
