<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancel Movie Showing</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="ManageShowtimes.jsp" class="btn btn-primary"><button type="button" class="btn btn-primary">Manage Showtime</button></a>
		   	<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>

		<h1>Cancel Movie Showing</h1>

		<div class="row">
			<div class="col-sm-2"><h4>Movie</h4></div>
			<div class="col-sm-3"><h4>Theatre</h4></div>
			<div class="col-sm-2"><h4>Showroom Number</h4></div>
			<div class="col-sm-2"><h4>Scheduled Runtime</h4></div>
			<div class="col-sm-3"><h4>Actions</h4></div>
		</div>

		<div class="row">
			<div class="col-sm-2">Star Trek</div>
			<div class="col-sm-3">Theatre 13</div>
			<div class="col-sm-2">Showroom 1</div>
			<div class="col-sm-2">3:00pm-5:00pm</div>
			<div class="col-sm-3">
				<div class="row">
					<a href="CancellationConfirmation.jsp" class="btn btn-danger">Confirm Cancellation</a>
					<a href="OwnerHomePage.jsp" class="btn btn-primary">Discard Cancellation</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
