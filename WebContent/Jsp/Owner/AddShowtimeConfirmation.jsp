<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Showtime Added Confirmation Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<a href="/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="/Owner/ViewShowroom.jsp" class="btn btn-primary">View Showroom</a>
		   	<a href="/Owner/ViewTheatreDetails.jsp" class="btn btn-primary">View Theatre Details</a>
		    <a href="/Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
	
		<h2>Showtime Added Confirmation</h2>
	
		<div class="row">
			<div class="col-sm-4"><h2>Movie</h2></div>
			<div class="col-sm-4"><h2>Theatre</h2></div>
			<div class="col-sm-2"><h2>Showroom Number</h2></div>
			<div class="col-sm-2"><h2>Scheduled Runtime</h2></div>
		</div>
		
		<div class="row">
			<div class="col-sm-4"><h2>Star Trek</h2></div>
			<div class="col-sm-4"><h2>Theatre 13</h2></div>
			<div class="col-sm-2"><h2>Showroom 1</h2></div>
			<div class="col-sm-2"><h2>3:00pm-5:00pm</h2></div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>