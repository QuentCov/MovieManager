<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/GeneralStyle.css">
<title>Movie Manager - Cancel Order</title>
</head>
<body>
	<div class="containter">
		<div class="row">
			<div class="col-md-3">
				<a href="/ViewOrders.jsp" class="btn btn-default">View All Orders</a>
			</div>
			<div class="offset-md-4 col-md-2">
				<a href="/CustomerHomePage.jsp" class="btn btn-default">Home</a>
			</div>
			<div class="offset-md-1 col-md-2">
				<a href="/Login.jsp" class="btn btn-default">Sign Out</a>
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
		</div>
		<div class="row">
			<div class="col-sm-2">
				<a href="/CancellationConfirmation.jsp" class="btn btn-default">Confirm Cancellation</a>
			</div>
			<div class="offset-sm-5 col-sm-2">
				<a href="/CustomerHomePage.jsp" class="btn btn-default">Discard Cancellation</a>
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</html>