<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Theatre Details Page</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Home Page</a>
			<a href="Jsp/Owner/AddMovie.jsp" class="btn btn-primary">Add Movie</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>

		<h1>View Theatres</h1>

		<div class="row">
			<div class="col-sm-4"><h4>Theatre Name</h4></div>
			<div class="col-sm-5"><h4>Address</h4></div>
			<div class="col-sm-3"><h4>Actions</h4></div>
		</div>
		
		<c:forEach items="${theatres}" var="theatre">
		<div class="row">
			<div class="col-sm-4"><p><c:out value="${theatre.getName()}"/></p></div>
			<div class="col-sm-5"><p><c:out value="${theatre.getAddress().toString()}"/></p></div>
			<div class="col-sm-3">
				<form name="item" method="POST" action="${pageContext.request.contextPath}/ViewShowrooms">
					<input type="hidden" name="CSRFToken" value="${CSRFToken}">
	            	<input type="hidden" name="theatreName" value="${theatre.getName()}"/>
	            	<input type="submit" class="btn btn-primary" value="View Showrooms">
	            </form>
			</div>
		</div>
		</c:forEach>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
