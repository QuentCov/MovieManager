<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancel Movie Showing</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="${pageContext.request.contextPath}/ManageShowtimes" class="btn btn-primary"><button type="button" class="btn btn-primary">Manage Showtime</button></a>
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
			<div class="col-sm-2">${showing.getMovie().getName() }</div>
			<div class="col-sm-2">${showing.getShowroom().getName() }</div>
			<div class="col-sm-2">${showing.getStartTime().toString() } - ${showing.getEndTime().toString() }</div>
			<div class="col-sm-2">${showing.getNumTicketsSold() }</div>
			<div class="col-sm-2">${showing.getCost() }</div>
			<div class="col-sm-2">
				<div class="row">
					<form name="item" method="POST" action="${pageContext.request.contextPath}/CancelShowingConfirmation">
		            	<input type="hidden" name="showingId" value="${showing.getId()}"/>
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
