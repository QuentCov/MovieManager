<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Showtime Added Confirmation Page</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="ViewShowrooms.jsp" class="btn btn-primary">View Showroom</a>
		   	<a href="ViewTheatreDetails.jsp" class="btn btn-primary">View Theatre Details</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
	
		<h1>Showtime Added</h1>
		<p>Please review details below.</p>
	
		<div class="row">
			<div class="col-sm-3"><h4>Movie</h4></div>
			<div class="col-sm-3"><h4>Theatre</h4></div>
			<div class="col-sm-3"><h4>Showroom Number</h4></div>
			<div class="col-sm-3"><h4>Scheduled Runtime</h4></div>
		</div>
		
		<div class="row">
			<div class="col-sm-3">Star Trek</div>
			<div class="col-sm-3">Theatre 13</div>
			<div class="col-sm-3">Showroom 1</div>
			<div class="col-sm-3">3:00pm-5:00pm</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>