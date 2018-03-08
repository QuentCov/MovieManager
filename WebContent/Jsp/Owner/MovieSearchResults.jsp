<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Search Results</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, ${user.getFullName() }</div>
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Search Results</h1>
		<div class="row">
			<div class="col-sm-2"><h4>Movie Name</h4></div>
			<div class="col-sm-4"><h4>Description</h4></div>
			<div class="col-sm-4"><h4>Thumbnail</h4></div>
			<div class="col-sm-2"><h4>Actions</h4></div>
		</div>
		
		<c:forEach items="${movies}" var="movie">
		<div class="row">
			<div class="col-sm-2"><p>${movie.getName()}</p></div>
			<div class="col-sm-4"><p>${movie.getDescription()}</p></div>
			<div class="col-sm-4"><img class="img-fluid" src="data:image/jpeg;base64,${movie.renderImage()}" alt="${movie.getName()} Poster"/></div>
			<div class="col-sm-2">
				<form name="item" method="POST" action="${pageContext.request.contextPath}/MovieDetails">
	            	<input type="hidden" name="movieName" value="${movie.getName()}"/>
	            	<input type="submit" class="btn btn-primary" value="View Movie Details">
	            </form>
			</div>
		</div>
		</c:forEach>
		
	</div>
    <%@ include file="/_partials/scripts.html" %>
</body>
</html>
