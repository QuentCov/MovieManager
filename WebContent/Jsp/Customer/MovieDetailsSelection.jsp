<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movies Details Selection Page</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="MovieSearchResults.jsp" class="btn btn-primary">Back to search</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Details:</h1>
		<br>
		<div class="row">
			<div class="col-sm-1"><h5>Movie Name</h5></div>
			<div class="col-sm-1"><h5>Description</h5></div>
			<div class="col-sm-1"><h5>Rating</h5></div>
			<div class="col-sm-2"><h5>Poster</h5></div>
			<div class="col-sm-2"><h5>Theatre Name/Number</h5></div>
			<div class="col-sm-1"><h5>Showtime</h5></div>
			<div class="col-sm-1"><h5>Price per Seat</h5></div>
			<div class="col-sm-1"><h5>Seats Available</h5></div>
			<div class="col-sm-2"><h5>Actions</h5></div>
		</div>
		<div class="row">
			<div class="col-sm-1">
				Spongebob Squarepants: The New Musical
			</div>
			<div class="col-sm-1">
				Our Town meets Armageddon under the sea!
			</div>
			<div class="col-sm-1">
				Rating: 4.35 Stars
			</div>
			<div class="col-sm-2">
    			<img class="img-fluid" src="https://img.wennermedia.com/article-leads-horizontal/inside-spongebob-play-e579f90d-908e-4ffd-9674-4ae8a0861dbe.jpg">
			</div>
			<div class="col-sm-2">
				De Marko Theatre #1
			</div>
			<div class="col-sm-1">
				08/16/2018 6:37
			</div>
			<div class="col-sm-1">
				$40.00 per seat
			</div>
			<div class="col-sm-1">
				160 Available Seats
			</div>
			<div class="col-sm-2">
    			<a href="ViewAndCheckoutShoppingCart.jsp" class="btn btn-primary">Add to Cart</a>
			</div>
		</div>
		<h2>Viewer Reviews:</h2>
		<div class="row">
			<div class="col-sm-12">
				<h3>Total Rating: 4.35 Stars</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2"><h5>Name</h5></div>
			<div class="col-sm-1"><h5>Date</h5></div>
			<div class="col-sm-1"><h5>Rating</h5></div>
			<div class="col-sm-8"><h5>Review</h5></div>
		</div>
		<div class="row">
			<div class="col-sm-2">
				Pat Boivan
			</div>
			<div class="col-sm-1">
				01/20/18
			</div>
			<div class="col-sm-1">
				Total Rating: 4.35 Stars
			</div>
			<div class="col-sm-8">
				Too much water.
			</div>
		</div>
	</div>
    <%@ include file="/_partials/scripts.html" %>
</body>
</html>
