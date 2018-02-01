<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancel Showing Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<a href="/Owner/OwnerHomePage.jsp" class="btn btn-default">Owner Homepage</a>
		   	<a href="/Owner/ManageShowtimes.jsp" class="btn btn-default"><button type="button" class="btn btn-default">Manage Showtime</button></a>
		   	<a href="/Login.jsp" class="btn btn-default">Log Out</a>
		</div>
	
		<h2>Cancel Movie Showing</h2>
	
		<div class="row">
			<div class="col-sm-3"><h2>Movie</h2></div>
			<div class="col-sm-3"><h2>Theatre</h2></div>
			<div class="col-sm-2"><h2>Showroom Number</h2></div>
			<div class="col-sm-2"><h2>Scheduled Runtime</h2></div>
			<div class="col-sm-2"><h2>Actions</h2></div>
		</div>
		
		<div class="row">
			<div class="col-sm-3"><h2>Star Trek</h2></div>
			<div class="col-sm-3"><h2>Theatre 13</h2></div>
			<div class="col-sm-2"><h2>Showroom 1</h2></div>
			<div class="col-sm-2"><h2>3:00pm-5:00pm</h2></div>
			<div class="col-sm-2">
				<a href="/Owner/CancellationConfirmation.jsp" class="btn btn-primary">Confirm Cancellation</a>
				<a href="/Owner/OwnerHomePage.jsp" class="btn btn-danger">Discard Cancellation</a>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-5"><p>Theatre 1</p></div>
			<div class="col-sm-6"><p>42 Wallaby Way Sydney Australia</p></div>
			<div class="col-sm-1"><a href="/Owner/ViewShowrooms.jsp" class="btn btn-primary">View</a></div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
