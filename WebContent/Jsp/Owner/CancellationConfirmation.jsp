<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cancellation Confirmation Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="../../css/GeneralStyle.css">
</head>
<body class="container">

	<div class="row">
		<a href="/Owner/OwnerHomePage.jsp" class="btn btn-default">Owner Homepage</a>
	   	<a href="/Owner/ViewShowrooms.jsp" class="btn btn-default"><button type="button" class="btn btn-default">View Showrooms</button></a>
	   	<a href="/Owner/ManageShowtimes.jsp" class="btn btn-default"><button type="button" class="btn btn-default">Manage Showtimes</button></a>
	   	<!-- TODO hook up log out button to send to login page -->
	   	<button type="button" class="btn btn-default">Log Out</button>
	</div>

	<h2>Showing Cancellation Confirmed</h2>

	<div class="row">
		<div class="col-sm-3"><h2>Movie</h2></div>
		<div class="col-sm-3"><h2>Theatre</h2></div>
		<div class="col-sm-3"><h2>Showroom Number</h2></div>
		<div class="col-sm-3"><h2>Refundable Amount</h2></div>
	</div>
	
	<div class="row">
		<div class="col-sm-3"><h2>Star Trek</h2></div>
		<div class="col-sm-3"><h2>Theatre 13</h2></div>
		<div class="col-sm-3"><h2>Showroom 1</h2></div>
		<div class="col-sm-3"><h2>$123.45</h2></div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>
