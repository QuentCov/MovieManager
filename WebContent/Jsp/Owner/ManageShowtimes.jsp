<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Details Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<a href="/Owner/OwnerHomePage.jsp" class="btn btn-default">Owner Homepage</a>
		   	<a href="/Owner/AddMovie.jsp" class="btn btn-default"><button type="button" class="btn btn-default">Add Showtime</button></a>
		   	<a href="/Login.jsp" class="btn btn-default">Log Out</a>
		</div>
	
		<h2>Manage Showtimes In Showroom 1</h2>
	
		<div class="row">
			<div class="col-sm-4"><h2>Movie Playing</h2></div>
			<div class="col-sm-4"><h2>Scheduled Runtime</h2></div>
			<div class="col-sm-2"><h2>Tickets Purchased</h2></div>
			<div class="col-sm-2"><h2>Actions</h2></div>
		</div>
		
		<div class="row">
			<div class="col-sm-4"><h2>Avatar</h2></div>
			<div class="col-sm-4"><h2>8:30pm-11:00pm</h2></div>
			<div class="col-sm-2"><h2>Tickets Purchased</h2></div>
			<div class="col-sm-2">
				<a href="/Owner/MovieDetails.jsp" class="btn btn-primary">Movie Details</a>
				<a href="/Owner/CancelShowing.jsp" class="btn btn-danger">Cancel</a>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>