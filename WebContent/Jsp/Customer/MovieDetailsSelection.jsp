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
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Customer/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="${pageContext.request.contextPath}/UpdateShoppingCart" class="btn btn-primary">Checkout</a>
			<a href="Jsp/Customer/MovieSearchResults.jsp" class="btn btn-primary">Back to search</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Details:</h1>
		<br>
		<div class="row">
		    <div class="col-sm-1">Movie Title</div>
		    <div class="col-sm-1">Description</div>
		    <div class="col-sm-1">Rating</div>
		    <div class="col-sm-3">Poster</div>
		    <div class="col-sm-1">Theatre</div>
		    <div class="col-sm-1">Start Time</div>
			<div class="col-sm-1">Cost per Ticket</div>
			<div class="col-sm-1">Tickets Available</div>
			<div class="col-sm-2">Actions</div>
		</div>
       	<div class="row">
   			<div class="col-sm-1"><c:out value="${movie.getName()}"/></div>
            <div class="col-sm-1"><c:out value="${movie.getDescription()}"/></div>
            <div class="col-sm-1"><c:out value="${movie.getRating()}"/></div>
            <div class="col-sm-3">
            	<c:set var="data" value="${movie.getThumbnailData()}"/>
				<c:choose>
					<c:when test="${empty data}">
						<td>Sorry! No thumbnail available</td>
					</c:when>
					<c:otherwise>
						<td><img class="img-fluid" src="data:image/jpeg;base64,${movie.renderImage()}" alt="${movie.getName()} Poster"/></td>
					</c:otherwise>
				</c:choose>
            </div>
            <div class="col-sm-1"><c:out value="${showing.getShowroom().getTheatre().getName()}"/></div>
            <div class="col-sm-1"><c:out value="${showing.getStartTime()}"/></div>
            <div class="col-sm-1"><c:out value="${showing.getCost()}"/></div>
            <div class="col-sm-1"><c:out value="${showing.getShowroom().getCapacity() - showing.getNumTicketsSold()}"/></div>
	    	<div class="col-sm-2">
	    		<form name="addItem" action=${pageContext.request.contextPath}/UpdateShoppingCart onsubmit="return moreThanZero()" onkeypress="return isNumberKey(event)" method="POST" >
		        	<input type='hidden' name='updateItem' value="${movie.getName()}">
		        	<input type='hidden' name='type' value='add'>
		        	<input type='text' name='ticketCount' placeholder="Ticket Count">
		        	<input type="submit" class="btn btn-primary" value="Add to Cart">
		        </form>
		    	<form name="addReview" action="${pageContext.request.contextPath}/CustomerReviewSetup">
		        	<input type='hidden' name='reviewMovie' value="${movie.getName()}">
		        	<input type="submit" class="btn btn-primary" value="Add Review">
		        </form>
	        </div>
        </div>
		<h2>Viewer Reviews:</h2>
		<div class="row">Total Rating: <c:out value="${movie.getAverageRating()}"/></div>
		<div class="row">
			<div class="col-sm-3">Reviewer</div>
			<div class="col-sm-1">Rating</div>
			<div class="col-sm-6">Review</div>
		</div>
   		<c:forEach items="${reviews}" var="review">
   		<div class="row">
   			<div class="col-sm-3"><c:out value="${review.getReviewer().getFullName()}"/></div>
		    <div class="col-sm-1"><c:out value="${review.getRating()}"/></div>
		    <div class="col-sm-6"><c:out value="${review.getReview()}"/></div>
   		</div>
        </c:forEach>
	</div>
    <%@ include file="/_partials/scripts.html" %>
    <script>
	    function isNumberKey(evt){
	        var charCode = (evt.which) ? evt.which : event.keyCode
	        if (charCode > 31 && (charCode < 48 || charCode > 57))
	            return false;
	        return true;
	    }
	    
	    function moreThanZero() {
	    	var tickets =  document.forms["addItem"]["ticketCount"].value;
	    	if(tickets == null || tickets == 0) {
	    		return false;
	    	}
	    	return true;
	    }
	</script>
</body>
</html>
