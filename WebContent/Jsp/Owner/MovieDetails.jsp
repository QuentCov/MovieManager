<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Details</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
		    <a href="OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		    <a href="AddMovie.jsp" class="btn btn-primary">Add Movie</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Details</h1>
		<div class="row">
			<div class="col-sm-2"><h4>Movie Name</h4></div>
			<div class="col-sm-1"><h4>Genre</h4></div>
			<div class="col-sm-2"><h4>Thumbnail</h4></div>
			<div class="col-sm-2"><h4>Description</h4></div>
			<div class="col-sm-1"><h4>Runtime</h4></div>
			<div class="col-sm-2"><h4>Tickets Sold</h4></div>
			<div class="col-sm-1"><h4>Rating</h4></div>
			<div class="col-sm-1"><h4>Actions</h4></div>
		</div>

		<div class="row">
			<div class="col-sm-2">Bee Movie</div>
			<div class="col-sm-1">Comedy</div>
			<div class="col-sm-2"><img class="img-fluid" src="https://images-na.ssl-images-amazon.com/images/I/51mY9ddJHZL.jpg" alt="Bee Movie Poster"/></div>
			<div class="col-sm-2">Adult woman falls in love with bee.</div>
			<div class="col-sm-1">2 hrs</div>
			<div class="col-sm-2">109</div>
			<div class="col-sm-1">PG</div>
			<div class="col-sm-1"><button type="button" class="btn btn-primary">Update</button></div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
