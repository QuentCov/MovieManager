<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Add Showtime</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<form action="${pageContext.request.contextPath}/MangeShowtimes">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="Mange Showtimes">
			</form>
		   	<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>

		<h1>Add Showtime</h1>
		<form name="showtimeForm" method="POST" action="${pageContext.request.contextPath}/AddShowtimeConfirmation">
			<input type="hidden" name="CSRFToken" value="${CSRFToken}">
			<div class="form-group">
				<label for="movie">Select Movie:</label>
				<select name="movie">
				<c:forEach items="${movies}" var="movie">
					<option><c:out value="${movie.getName() }"/></option>
				</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="showtimeStart">Date (mm/dd/yyyy):</label>
				<input type="text" class="form-control" id="showingDate" name="showingDate" placeholder="mm/dd/yyyy">
			</div>
			<div class="form-group">
				<label for="showtimeStart">Time (hh:mm AM or PM):</label>
				<input type="text" class="form-control" id="showingTime" name="showingTime" placeholder="hh:mm (AM or PM)">
			</div>
			<div class="form-group">
				<label for="showtimeStart">Cost:</label>
				<input type="text" class="form-control" id="cost" name="cost" placeholder="xx.xx">
			</div>
			<input type="hidden" name="showroomId" value="${showroomId}"/>
			<input type="hidden" name="theatreId" value="${theatreId}"/>
           	<input type="submit" class="btn btn-primary" value="Add Showtime">
		</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
