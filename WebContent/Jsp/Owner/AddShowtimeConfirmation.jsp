<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Showtime Added Confirmation</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="Jsp/Owner/ViewShowrooms.jsp" class="btn btn-primary">View Showroom</a>
		   	<a href="Jsp/Owner/ViewTheatreDetails.jsp" class="btn btn-primary">View Theatre Details</a>
		    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>

		<h1>${result}</h1>
		<p>Please review details below.</p>

		<div class="row">
			<div class="col-sm-2"><h4>Movie</h4></div>
			<div class="col-sm-2"><h4>Theatre Name</h4></div>
			<div class="col-sm-2"><h4>Showroom Name</h4></div>
			<div class="col-sm-6"><h4>Scheduled Runtime</h4></div>
		</div>

		<div class="row">
			<div class="col-sm-2">${movieName}</div>
			<div class="col-sm-2">${theatreName}</div>
			<div class="col-sm-2">${showroomName}</div>
			<div class="col-sm-6">${runtime}</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
