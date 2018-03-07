<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancellation Confirmation</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, ${user.getFullName() }</div>
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="${pageContext.request.contextPath}/ViewShowrooms" class="btn btn-primary">View Showrooms</a>
		   	<a href="${pageContext.request.contextPath}/ManageShowtimes" class="btn btn-primary">Manage Showtimes</a>
		   	<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>

		<h1>${ result ? "Cancellation Confirmed" : "Cancellation Error" }</h1>
		<p>Please review details below.</p>

		<div class="row">
			<div class="col-sm-3"><h4>Movie</h4></div>
			<div class="col-sm-3"><h4>Theatre</h4></div>
			<div class="col-sm-3"><h4>Showroom Number</h4></div>
			<div class="col-sm-3"><h4>Refundable Amount</h4></div>
		</div>

		<div class="row">
			<div class="col-sm-3">${showing.getMovie().getName() }</div>
			<div class="col-sm-3">${showing.getShowroom().getTheatre().getName() }</div>
			<div class="col-sm-3">${showing.getShowroom().getName() }</div>
			<div class="col-sm-3">${refundableAmount }</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
