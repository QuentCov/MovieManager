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
			<a href="${pageContext.request.contextPath}/ViewTheatreDetails" class="btn btn-primary">View Theatre Details</a>
			<a href="AddMovie.jsp" class="btn btn-primary">Add Movies</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Owner Home Page</h1>
		<form action="${pageContext.request.contextPath}/OwnerMovieSearchResults" name="searchMoviesForm" method="post">
			<div class="form-group">
				<label for="movieToSearch">Search Movies (search without a query to view all movies): </label>
				<input type="text" class="form-control" id="movieToSearch" name="movieToSearch" placeholder="Search Movies">
			</div>
			<input class="btn btn-light" type="submit" value="Search">
		</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
