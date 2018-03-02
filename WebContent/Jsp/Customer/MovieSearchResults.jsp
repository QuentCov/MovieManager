<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Search Results</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="ViewOrders.jsp" class="btn btn-primary">View Orders</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Search Results</h1>
		<p>Searched for "Spongebob Squarepants: The New Musical"</p>
		<br>
		<div class="row">
			<div class="col-sm-2"><h4>Theatre Name</h4></div>
			<div class="col-sm-1"><h4>Theatre Number</h4></div>
			<div class="col-sm-2"><h4>Showtime</h4></div>
			<div class="col-sm-2"><h4>Available Seats</h4></div>
			<div class="col-sm-1"><h4>Price</h4></div>
			<div class="col-sm-2"><h4>Thumbnail</h4></div>
			<div class="col-sm-2"><h4>Actions</h4></div>
		</div>
		<div class="row">
    		<div class="col-sm-2">
    			Madden Theatre
    		</div>
    		<div class="col-sm-1">
    			Theatre #89
    		</div>
    		<div class="col-sm-2">
    			08/16/18 6:39
    		</div>
    		<div class="col-sm-2">
    			30 Available Seats
    		</div>
    		<div class="col-sm-1">
    			$40.00
    		</div>
    		<div class="col-sm-2">
    			<div class="card">
	    			<img class="img-fluid" src="https://img.wennermedia.com/article-leads-horizontal/inside-spongebob-play-e579f90d-908e-4ffd-9674-4ae8a0861dbe.jpg">
    			</div>
    		</div>
    		<div class="col-sm-2">
    			<a href="MovieDetailsSelection.jsp" class="btn btn-primary">View Details</a>
    		</div>
    	</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
