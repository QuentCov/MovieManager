<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Owner Home Page</title>
</head>
<body>
	<div class="container">
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
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>