<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancel Movie Showing</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<form action="${pageContext.request.contextPath}/ManageShowtimes">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="Manage Showtime">
			</form>
		   	<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>

		<h1>Cancel Movie Showing</h1>

		<div class="row">
			<div class="col-sm-2"><h4>Movie</h4></div>
			<div class="col-sm-2"><h4>Showroom</h4></div>
			<div class="col-sm-2"><h4>Scheduled Runtime</h4></div>
			<div class="col-sm-2"><h4>Tickets Sold</h4></div>
			<div class="col-sm-2"><h4>Cost Per Ticket</h4></div>
			<div class="col-sm-2"><h4>Actions</h4></div>
		</div>

		<div class="row">
			<div class="col-sm-2"><c:out value="${showing.getMovie().getName() }"/></div>
			<div class="col-sm-2"><c:out value="${showing.getShowroom().getName() }"/></div>
			<div class="col-sm-2"><c:out value="${showing.getStartTime().toString() } - ${showing.getEndTime().toString() }"/></div>
			<div class="col-sm-2"><c:out value="${showing.getNumTicketsSold() }"/></div>
			<div class="col-sm-2"><c:out value="${showing.getCost() }"/></div>
			<div class="col-sm-2">
				<div class="row">
					<form name="item" method="POST" action="${pageContext.request.contextPath}/CancelShowingConfirmation">
						<input type="hidden" name="CSRFToken" value="${CSRFToken}">
		            	<input type="hidden" name="showingId" value="${showing.getID()}"/>
		            	<input type="submit" class="btn btn-danger" value="Confirm Cancellation">
		            </form>
					<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Discard Cancellation</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
