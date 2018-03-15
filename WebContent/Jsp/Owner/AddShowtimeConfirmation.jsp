<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Showtime Added Confirmation</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<form action="${pageContext.request.contextPath}/ViewShowrooms">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="View Showroom">
			</form>
		   	<form action="${pageContext.request.contextPath}/ViewTheatreDetails">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="View Theatre Details">
			</form>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
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
			<div class="col-sm-2"><c:out value="${movieName}"/></div>
			<div class="col-sm-2"><c:out value="${theatreName}"/></div>
			<div class="col-sm-2"><c:out value="${showroomName}"/></div>
			<div class="col-sm-6"><c:out value="${runtime}"/></div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
