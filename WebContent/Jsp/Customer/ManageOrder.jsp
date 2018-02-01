<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Manage Order</title>
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
				<a href="/Login.jsp" class="btn btn-default">Log Out</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-1">
				Order #16
			</div>
			<div class="col-sm-2">
				The Bee Movie
			</div>
			<div class="col-sm-1">
				13 Tickets
			</div>
			<div class="col-sm-1">
				$142.36
			</div>
			<div class="col-sm-1">
				De Marco Theatre # 2
			</div>
			<div class="col-sm-2">
				08/16/2018, 6:00 PM
			</div>
			<div class="col-sm-2">
				<a href="MovieDetailsSelection.jsp" class="btn btn-default">View Product Details</a>
			</div>
			<div class="col-sm-2">
				<a href="CustomerHomePage.jsp" class="btn btn-default">Cancel Order</a>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>