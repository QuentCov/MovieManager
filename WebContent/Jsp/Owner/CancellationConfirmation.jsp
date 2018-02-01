<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancellation Confirmation Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<a href="/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="/Owner/ViewShowrooms.jsp" class="btn btn-primary"><button type="button" class="btn btn-primary">View Showrooms</button></a>
		   	<a href="/Owner/ManageShowtimes.jsp" class="btn btn-primary"><button type="button" class="btn btn-primary">Manage Showtimes</button></a>
		   	<a href="/Login.jsp" class="btn btn-primary">Log Out</a>
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
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
