<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Showroom Details Page</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Owner/AddMovie.jsp" class="btn btn-primary">Add Movie</a>
			<a href="${pageContext.request.contextPath}/ViewTheatreDetails" class="btn btn-primary">View Theatre Details</a>
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Home Page</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>

		<h1>Theatre 13 Showrooms</h1>
		<div class="row">
			<div class="col-sm-3"><h4>Showroom Name</h4></div>
			<div class="col-sm-3"><h4>Theatre Name</h4></div>
			<div class="col-sm-3"><h4>Capacity</h4></div>
			<div class="col-sm-3"><h4>Actions</h4></div>
		</div>
		
		<c:forEach items="${showrooms}" var="showroom">
		<div class="row">
			<div class="col-sm-3"><p><c:out value="${showroom.getName()}"/></p></div>
			<div class="col-sm-3"><p><c:out value="${showroom.getTheatre().getName()}"/></p></div>
			<div class="col-sm-3"><p><c:out value="${showroom.getCapacity()}"/></p></div>
			<div class="col-sm-3">
				<form name="item" method="POST" action="${pageContext.request.contextPath}/ManageShowtimes">
	            	<input type="hidden" name="showroomName" value="${showroom.getName()}"/>
	            	<input type="hidden" name="theatreId" value="${theatreId}"/>
	            	<input type="submit" class="btn btn-primary" value="Manage Showtimes">
	            </form>
			</div>
		</div>
		</c:forEach>
		
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
