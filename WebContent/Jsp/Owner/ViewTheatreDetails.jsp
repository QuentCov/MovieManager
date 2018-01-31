<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Theatre Details Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/GeneralStyle.css">
</head>
<body class="container">
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
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>