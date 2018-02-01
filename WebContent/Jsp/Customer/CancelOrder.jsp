<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Cancel Order</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<a href="ViewOrders.jsp" class="btn btn-primary">View All Orders</a>
			</div>
			<div class="offset-md-4 col-md-2">
				<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			</div>
			<div class="offset-md-1 col-md-2">
				<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
			</div>
		</div>
		<h1>Cancel Order</h1>
		<div class="row">
			<div class="col-sm-2"><h5>Order Number</h5></div>
			<div class="col-sm-7">
				<h5>Item To Cancel</h5>
				<div class="row">
					<div class="col-sm-3"><h5>Movie Name</h5></div>
					<div class="col-sm-2"><h5>Tickets Bought</h5></div>
					<div class="col-sm-2"><h5>Total Price</h5></div>
					<div class="col-sm-3"><h5>Theatre Name/Number</h5></div>
					<div class="col-sm-2"><h5>Showtime/Date</h5></div>
				</div>
			</div>
			<div class="col-sm-3"><h5>Actions</h5></div>
		</div>
		<div class="row">
			<div class="col-sm-2"><h5>Order #16</h5></div>
			<div class="col-sm-7">
				<div class="row">
					<div class="col-sm-3">The Bee Movie</div>
					<div class="col-sm-2">13 Tickets</div>
					<div class="col-sm-2">$142.36</div>
					<div class="col-sm-3">De Marco Theatre # 2</div>
					<div class="col-sm-2">08/16/2018, 6:00 PM</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="row">
					<div class="col-sm-6">
						<a href="CancellationConfirmation.jsp" class="btn btn-danger">Confirm Cancellation</a>
					</div>
					<div class="col-sm-6">
						<a href="CustomerHomePage.jsp" class="btn btn-primary">Discard Cancellation</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>