<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Movie Details Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/GeneralStyle.css">
</head>
<body class="container">
	<div class="row">
	    <a href="/Owner/OwnerHomePage.jsp" class="btn btn-default">Owner Homepage</a>
	    <a href="/Owner/AddMovie.jsp" class="btn btn-default">Add Movie</a>
	    <a href="/Login.jsp" class="btn btn-default">Log Out</a>
	</div>
	<div class="row">
		<div class="col-sm-2"><h2>Movie Name</h2></div>
		<div class="col-sm-1"><h2>Genre</h2></div>
		<div class="col-sm-3"><h2>Thumbnail</h2></div>
		<div class="col-sm-2"><h2>Description</h2></div>
		<div class="col-sm-1"><h2>Runtime</h2></div>
		<div class="col-sm-1"><h2>Tickets Purchased</h2></div>
		<div class="col-sm-1"><h2>Rating</h2></div>
		<div class="col-sm-1"><h2>Actions</h2></div>
	</div>
	
	<div class="row">
		<div class="col-sm-2">Bee Movie</div>
		<div class="col-sm-1">Comedy</div>
		<div class="col-sm-3"><img src="https://images-na.ssl-images-amazon.com/images/I/51mY9ddJHZL.jpg" alt="Bee Movie Poster"/></div>
		<div class="col-sm-2">Adult woman falls in love with bee.</div>
		<div class="col-sm-1">2 hrs</div>
		<div class="col-sm-1">109</div>
		<div class="col-sm-1">PG</div>
		<div class="col-sm-1"><button type="button" class="btn btn-default">Update</button></div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>