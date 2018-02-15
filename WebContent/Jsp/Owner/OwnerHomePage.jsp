<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Owner Home Page</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="ViewTheatreDetails.jsp" class="btn btn-primary">View Theatre Details</a>
			<a href="AddMovie.jsp" class="btn btn-primary">Add Movies</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Owner Home Page</h1>
		<form name="searchMoviesForm">
			<div class="form-group">
				<label for="movieToSearch">Search Movies: </label>
				<input type="text" class="form-control" id="movieToSearch" name="movieToSearch" placeholder="Search Movies">
			</div>
			<a href="MovieSearchResults.jsp" class="btn btn-light" type="button">Go!</a>
		</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
