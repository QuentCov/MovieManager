<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Transaction Complete!</title>
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
			Your order has been placed. Please review your order details below.
		</div>
		<div class="row">
			Order Information
		</div>
		<div class="row">
			Name: Woolie Madden
		</div>
		<div class="row">
			<div class="col-md-6">
				Billing Address: 100 Royal Road, Maddentown, Grenada
			</div>
			<div class="col-md-6">
				Shipping Address: 001 Zaibatzu Trail, Montreal, Canada
			</div>
		</div>
		<div class="row">
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
		</div>
		<div class="row">
			<div class="col-sm-2">
				Get Out
			</div>
			<div class="col-sm-1">
				1 Ticket
			</div>
			<div class="col-sm-1">
				$12.37
			</div>
			<div class="col-sm-1">
				De Marco Theatre # 6
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>