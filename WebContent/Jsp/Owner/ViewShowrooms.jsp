<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Showroom Details Page</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="AddMovie.jsp" class="btn btn-primary">Add Movie</a>
			<a href="ViewTheatreDetails.jsp" class="btn btn-primary">View Theatre Details</a>
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Home Page</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>

		<h1>Theatre 13 Showrooms</h1>
		<div class="row">
			<div class="col-sm-3"><h4>Showroom Number</h4></div>
			<div class="col-sm-2"><h4>Capacity</h4></div>
			<div class="col-sm-2"><h4>Dreamliner Seats Available</h4></div>
			<div class="col-sm-2"><h4>Status</h4></div>
			<div class="col-sm-3"><h4>Actions</h4></div>
		</div>

		<div class="row">
			<div class="col-sm-3"><p>Showroom 1</p></div>
			<div class="col-sm-2"><p>250</p></div>
			<div class="col-sm-2"><p>Yes</p></div>
			<div class="col-sm-2"><p>Being Cleaned</p></div>
			<div class="col-sm-3"><a href="ManageShowtimes.jsp" class="btn btn-primary">Manage Showtimes</a></div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
