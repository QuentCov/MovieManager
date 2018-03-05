<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Manage Showtimes</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<form name="item" method="POST" action="${pageContext.request.contextPath}/AddShowtime">
            	<input type="hidden" name="showroomId" value="${showroomId}"/>
            	<input type="hidden" name="theatreId" value="${theatreId}"/>
            	<input type="submit" class="btn btn-primary" value="Add Showtime">
            </form>
		   	<a href="Jsp/Login.jsp" class="btn btn-primary">Log Out</a>
		</div>

		<h1>Manage Showtimes</h1>

		<div class="row">
			<div class="col-sm-2"><h4>Movie Name</h4></div>
			<div class="col-sm-2"><h4>Start Time</h4></div>
			<div class="col-sm-2"><h4>End Time</h4></div>
			<div class="col-sm-2"><h4>Tickets Sold</h4></div>
			<div class="col-sm-2"><h4>Cost Per Ticket</h4></div>
			<div class="col-sm-2"><h4>Actions</h4></div>
		</div>

		<c:forEach items="${showings}" var="showing">
		<div class="row">
			<div class="col-sm-2">${showing.getMovie().getName()}</div>
			<div class="col-sm-2">${showing.getStartTime().toString()}</div>
			<div class="col-sm-2">${showing.getEndTime().toString()}</div>
			<div class="col-sm-2">${showing.getNumTicketsSold()}</div>
			<div class="col-sm-2">${showing.getCost()}</div>
			<div class="col-sm-2">
				<form name="item" method="POST" action="${pageContext.request.contextPath}/CancelShowing">
	            	<input type="hidden" name="showingId" value="${showing.getId()}"/>
	            	<input type="submit" class="btn btn-danger" value="Cancel Showtime">
	            </form>	            
			</div>
		</div>
		</c:forEach>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
