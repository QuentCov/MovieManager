<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movies Details Selection</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<form action="${pageContext.request.contextPath}/ViewOrders">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="View Orders">
			</form>
			<form action="${pageContext.request.contextPath}/UpdateShoppingCart">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="Checkout">
			</form>
			<a href="${pageContext.request.contextPath}/TheatreAndMovieSearchQueryServlet" class="btn btn-primary">Back to search</a>
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
	        	<input type='hidden' id='updateItem' value="${movie.getName()}">
	        	<input type='hidden' id='type' value='add'>
	        	<form>
	        		<input type='text' onkeypress="return isNumberKey(event)" id='ticketCount' placeholder="Ticket Count">
	        	</form>
	        	<button id="addItem" class="btn btn-primary" value="Add to Cart">Add to Cart</button>
	        	<div id="message" style="display: none;"><h6>Added to Cart</h6></div>
	        </div>
        </div>
		<h2>Viewer Reviews:</h2>
		<c:set var="data" value="${reviews}"/>
		<c:choose>
			<c:when test="${empty data}">
				<td>Sorry! No reviews available. You could be the first!</td>
			</c:when>
			<c:otherwise>
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
			</c:otherwise>
		</c:choose>
		<c:set var="data" value="${Review_Too_Long}"/>
		<c:choose>
			<c:when test="${empty data}">
				<div class="form-group">
				  <label for="review">Tell us what you thought:</label>
				  <textarea class="form-control" rows="5" id="review" name="review"></textarea>
				</div>
				<div class="row">
					<h5>Rating:</h5>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="1">
					</div>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="2">
					</div>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="3">
					</div>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="4">
					</div>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="5">
					</div>
				</div>
				<button id="submitReview" class="btn btn-primary" value="Submit Review">Submit Review</button>
			</c:when>
			<c:otherwise>
				<div class="form-group">
				  <label for="review">Please keep reviews under 1000 characters:</label>
				  <textarea class="form-control" rows="5" id="review"></textarea>
				</div>
				<div class="row">
					<h5>Rating:</h5>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="1">
					</div>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="2">
					</div>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="3">
					</div>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="4">
					</div>
					<div class="col-xs-1">
						<input type="radio" name="rating" value="5">
					</div>
				</div>
				<button id="submitReview" class="btn btn-primary" value="Submit Review">Submit Review</button>
			</c:otherwise>
		</c:choose>
		   			<div class="col-sm-3">${review.getReviewer().getFullName()}</div>
	</div>
    <%@ include file="/_partials/scripts.html" %>
    <script>
	    function isNumberKey(evt) {
	        var charCode = (evt.which) ? evt.which : event.keyCode
	        if (charCode > 31 && ((charCode < 48) || (charCode > 57)))
	            return false;
	        return true;
	    }
	    
	    function moreThanZero() {
	    	var tickets =  $("#ticketCount").val();
	    	if(tickets == null || tickets == 0) {
	    		return false;
	    	}
	    	return true;
	    }
	    
	    
		//AJAX for purchasing tickets.
		$('body').on('click', '#addItem', function() {
			if(moreThanZero()) {
				var obj = document.getElementById("message");
	            obj.style.display = 'none';
				$.post("/MovieManager/UpdateShoppingCart",
			    {
			        type: "add",
			        ticketCount: $("#ticketCount").val().toString()
			    },
			    function(data, status){
			    	var objAgain = document.getElementById("message");
		            if (objAgain.style.display == 'none') {
		                objAgain.style.display = 'block';
		            }
			    });
			}
	    	return false;
    	});
		
		//AJAX for submitting reviews.
		$('body').on('click', '#submitReview', function() {
			$.post("/MovieManager/CustomerReview",
		    {
		        rating: $('input[name=rating]:checked').val(),
		        review: $("#review").val()
		    },
		    function(data, status){
		    	location.reload(true);
		    });
		
	    	return false;
    	}); 
	</script>
</body>
</html>
