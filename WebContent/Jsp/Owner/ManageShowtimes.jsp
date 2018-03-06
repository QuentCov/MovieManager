<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Manage Showtimes</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="AddShowtime.jsp" class="btn btn-primary"><button type="button" class="btn btn-primary">Add Showtime</button></a>
		   	<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>

		<h1>Manage Showtimes</h1>

		<div class="row">
			<div class="col-sm-3"><h4>Movie Playing</h4></div>
			<div class="col-sm-4"><h4>Scheduled Runtime</h4></div>
			<div class="col-sm-2"><h4>Tickets Sold</h4></div>
			<div class="col-sm-3"><h4>Actions</h4></div>
		</div>

		<div class="row">
			<div class="col-sm-3"><p>Avatar</p></div>
			<div class="col-sm-4"><p>8:30pm-11:00pm</p></div>
			<div class="col-sm-2"><p>Tickets Purchased</p></div>
			<div class="col-sm-3">
				<a href="MovieDetails.jsp" class="btn btn-primary">Movie Details</a>
				<a href="CancelShowing.jsp" class="btn btn-danger">Cancel</a>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
