<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Manage Order</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="ViewOrders.jsp" class="btn btn-primary">View All Orders</a>
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Manage Order</h1>
		<div class="row">
			<div class="col-sm-1"><h5>Order Number</h5></div>
			<div class="col-sm-8">
				<h5>Items</h5>
				<div class="row">
					<div class="col-sm-3"><h5>Movie Name</h5></div>
					<div class="col-sm-1"><h5>Tickets Bought</h5></div>
					<div class="col-sm-1"><h5>Total Price</h5></div>
					<div class="col-sm-2"><h5>Theatre Name/Number</h5></div>
					<div class="col-sm-2"><h5>Showtime/Date</h5></div>
					<div class="col-sm-3"><h5>Actions</h5></div>
				</div>
			</div>
			<div class="col-sm-1"><h5>Order Total</h5></div>
			<div class="col-sm-2"><h5>Ordered Date</h5></div>
		</div>
		<div class="row">
			<div class="col-sm-1"><h5>Order #16</h5></div>
			<div class="col-sm-8">
				<div class="row">
					<div class="col-sm-3">The Bee Movie</div>
					<div class="col-sm-1">13 Tickets</div>
					<div class="col-sm-1">$142.36</div>
					<div class="col-sm-2">De Marco Theatre # 2</div>
					<div class="col-sm-2">08/16/2018, 6:00 PM</div>
					<div class="col-sm-3">
						<div class="row">
							<div class="col-sm-6">
								<a href="MovieDetailsSelection.jsp" class="btn btn-primary">View</a>
							</div>
							<div class="col-sm-6">
								<a href="CancelOrder.jsp" class="btn btn-danger">Cancel</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-1">Total: $154.73</div>
			<div class="col-sm-2">08/15/2018, 2:00 PM</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>