<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Theatre Details Page</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Home Page</a>
			<a href="AddMovie.jsp" class="btn btn-primary">Add Movie</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		
		<h1>Movie Corp Theatres</h1>
	
		<div class="row">
			<div class="col-sm-4"><h4>Theatre Name</h4></div>
			<div class="col-sm-5"><h4>Address</h4></div>
			<div class="col-sm-3"><h4>Actions</h4></div>
		</div>
		
		<div class="row">
			<div class="col-sm-4"><p>Theatre 1</p></div>
			<div class="col-sm-5"><p>42 Wallaby Way Sydney Australia</p></div>
			<div class="col-sm-3"><a href="ViewShowrooms.jsp" class="btn btn-primary">View</a></div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>