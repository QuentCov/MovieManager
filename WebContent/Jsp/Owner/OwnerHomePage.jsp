<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Owner Home Page</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<form action="${pageContext.request.contextPath}/ViewTheatreDetails">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="View Theatre Details">
			</form>
			<a href="AddMovie.jsp" class="btn btn-primary">Add Movies</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Owner Home Page</h1>
		<form action="${pageContext.request.contextPath}/OwnerMovieSearchResults" name="searchMoviesForm" method="post">
			<input type="hidden" name="CSRFToken" value="${CSRFToken}">
			<div class="form-group">
				<label for="movieToSearch">Search Movies (search without a query to view all movies): </label>
				<input type="text" class="form-control" id="movieToSearch" name="movieToSearch" placeholder="Search Movies">
			</div>
			<input class="btn btn-light" type="submit" value="Search">
		</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
