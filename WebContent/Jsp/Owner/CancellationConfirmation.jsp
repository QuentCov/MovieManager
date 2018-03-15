<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancellation Confirmation</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<form action="${pageContext.request.contextPath}/ViewShowrooms">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="View Showrooms">
			</form>
		   	<form action="${pageContext.request.contextPath}/ManageShowtimes">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="Manage Showtimes">
			</form>
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
			<div class="col-sm-3"><c:out value="${showing.getMovie().getName() }"/></div>
			<div class="col-sm-3"><c:out value="${showing.getShowroom().getTheatre().getName() }"/></div>
			<div class="col-sm-3"><c:out value="${showing.getShowroom().getName() }"/></div>
			<div class="col-sm-3"><c:out value="${refundableAmount }"/></div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
