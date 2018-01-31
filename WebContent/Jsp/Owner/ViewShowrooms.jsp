<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Showroom Details Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="../../css/GeneralStyle.css">
</head>
<body class="container">

	<a href="/Owner/AddMovie.jsp">Add Movie</a>
	<a href="/Owner/ViewTheatreDetails.jsp">View Theatre Details</a>
	<a href="/Owner/OwnerHomePage.jsp">Home Page</a>
	<a href="/Login.jsp">Log Out</a>
	
	<h2>Theatre 13 Showrooms</h2>

	<div class="row">
		<div class="col-sm-3"><h2>Showroom Number</h2></div>
		<div class="col-sm-3"><h2>Capacity</h2></div>
		<div class="col-sm-3"><h2>Dreamliner Seats Available</h2></div>
		<div class="col-sm-2"><h2>Status</h2></div>
		<div class="col-sm-1"><h2>Actions</h2></div>
	</div>
	
	<div class="row">
		<div class="col-sm-3"><p>Showroom 1</p></div>
		<div class="col-sm-3"><p>250</p></div>
		<div class="col-sm-3"><p>Yes</p></div>
		<div class="col-sm-2"><p>Being Cleaned</p></div>
		<div class="col-sm-1"><a href="/Owner/ManageShowtimes.jsp" class="btn btn-primary">Manage Showtimes</a></div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>