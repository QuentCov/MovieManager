<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Owner Home Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/GeneralStyle.css">
</head>
<body class="container">

	<div class="row">
		<h2>Navigation</h2>
		<a href="TheatreBuildingDetails.jsp">View Building Details</a>
		<a href="AddMovie.jsp">Add Movies</a>
		<a href="/Login.jsp">Log Out</a>
	</div>

	<div class="row">
		<form name="searchMoviesForm" action=searchMoviesResults method="get">
			Search Movies: <input type=text name=movieToSearch><br>
			<input type=submit value=searchMoviesResults> <br>  		
		</form>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>