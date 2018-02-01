<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Transaction Complete!</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="ViewOrders.jsp" class="btn btn-primary">View All Orders</a>
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Your order has been placed</h1>
		<p>Please review your order details below.</p>
		<br>
		<h4> </h4>
		<div class="row">
			<div class="col-sm-4">
				Name:
			</div>
			<div class="col-sm-4">
				Billing Address:
			</div>
			<div class="col-sm-4">
				Shipping Address:
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				Woolie Madden
			</div>
			<div class="col-sm-4">
				100 Royal Road, Maddentown, Grenada
			</div>
			<div class="col-sm-4">
				001 Zaibatzu Trail, Montreal, Canada
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-3"><h4>Movie Name</h4></div>
			<div class="col-sm-3"><h4>Tickets</h4></div>
			<div class="col-sm-3"><h4>Total Price</h4></div>
			<div class="col-sm-3"><h4>Theatre Name</h4></div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				The Bee Movie
			</div>
			<div class="col-sm-3">
				13 Tickets
			</div>
			<div class="col-sm-3">
				$142.36
			</div>
			<div class="col-sm-3">
				De Marco Theatre # 2
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				Get Out
			</div>
			<div class="col-sm-3">
				1 Ticket
			</div>
			<div class="col-sm-3">
				$12.37
			</div>
			<div class="col-sm-3">
				De Marco Theatre # 6
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>