<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movies Details Selection</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="MovieSearchResults.jsp" class="btn btn-primary">Back to search</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Details:</h1>
		<br>
		<table border="1">
			<tr>
				<td>Movie Name</td>
				<td>Description</td>
				<td>Rating</td>
				<td>Poster</td>
				<td>Theatre Name/Number</td>
				<td>Showtime</td>
				<td>Price per Seat</td>
				<td>Seats Available</td>
				<td>Actions</td>
			</tr>
			<tr>
	    		<td>${movieShowing.getMovie().getName()}</td>
	            <td>${movieShowing.getMovie().getDescription()}</td>
	            <td>${movieShowing.getMovie().getRating}</td>
	            <td>${movieShowing.getMovie().getThumbnailFile()}</td>
	            <td>${movieShowing.getShowroom().getTheatre().getName()}</td>
	            <td>${movieShwoing.getStartTime()}</td>
	            <td>${movie.getCost()}</td>
	            <td>${movie.getShowroom().getCapacity()}</td>
	         </tr>
		</table>
		<form name="item" action="UpdateCart">
        	<input type='hidden' name='itemIndex' value='<c:out value="${movie.getName()}"/>'>
        	<input type="submit" name="action" value="Add to Cart">
        </form>
        <form name="item" action="CustomerReview">
        	<input type='hidden' name='itemIndex' value='<c:out value="${movie.getName()}"/>'>
        	<input type="submit" name="action" value="Add Review">
        </form>
		<h2>Viewer Reviews:</h2>
		<table border="1">
			<tr>
				<td>Total Rating: ${movieShowing.getMovie().getAverageRating()}</td>
			</tr>
			<tr>
				<td>Reviewer</td>
				<td>Date</td>
				<td>Rating</td>
				<td>Review</td>
			</tr>
    		<c:forEach items="${reviews}" var="review">
    		<tr>
	    		<td>${review.getUser().getFullName()}</td>
	            <td>${review.getDate()}</td>
	            <td>${review.getRating()}</td>
	            <td>${review.getReview()}</td>
	        <tr>
	        </c:forEach>
		</table>
	</div>
    <%@ include file="/_partials/scripts.html" %>
</body>
</html>
