<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Theatre Details Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<a href="/Owner/AddMovie.jsp">Add Movie</a>
			<a href="/Owner/OwnerHomePage.jsp">Home Page</a>
			<a href="/Login.jsp">Log Out</a>
		</div>
		
		<h2>Movie Corp Theatres</h2>
	
		<div class="row">
			<div class="col-sm-5"><h2>Theatre Name</h2></div>
			<div class="col-sm-6"><h2>Address</h2></div>
			<div class="col-sm-1"><h2>Actions</h2></div>
		</div>
		
		<div class="row">
			<div class="col-sm-5"><p>Theatre 1</p></div>
			<div class="col-sm-6"><p>42 Wallaby Way Sydney Australia</p></div>
			<div class="col-sm-1"><a href="/Owner/ViewShowrooms.jsp" class="btn btn-primary">View</a></div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>