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
			<div class="btn btn-secondary">Hello, ${user.getFullName() }</div>
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
   			<div class="col-sm-1">${movie.getName()}</div>
            <div class="col-sm-1">${movie.getDescription()}</div>
            <div class="col-sm-1">${movie.getRating()}</div>
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
            <div class="col-sm-1">${showing.getShowroom().getTheatre().getName()}</div>
            <div class="col-sm-1">${showing.getStartTime()}</div>
            <div class="col-sm-1">${showing.getCost()}</div>
            <div class="col-sm-1">${showing.getShowroom().getCapacity() - showing.getNumTicketsSold()}</div>
	    	<div class="col-sm-2">
	    		<form id="addItem" name="addItem" onsubmit="return moreThanZero(); return addToCart();" onkeypress="return isNumberKey(event)">
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
		<div class="row">Total Rating: ${movie.getAverageRating()}</div>
		<div class="row">
			<div class="col-sm-3">Reviewer</div>
			<div class="col-sm-1">Rating</div>
			<div class="col-sm-6">Review</div>
		</div>
   		<c:forEach items="${reviews}" var="review">
   		<div class="row">
   			<div class="col-sm-3">${review.getReviewer().getFullName()}</div>
		    <div class="col-sm-1">${review.getRating()}</div>
		    <div class="col-sm-6">${review.getReview()}</div>
   		</div>
        </c:forEach>
	</div>
    <%@ include file="/_partials/scripts.html" %>
    <script>
	    function isNumberKey(evt){
	        var charCode = (evt.which) ? evt.which : event.keyCode
	        if (charCode > 31 && ((charCode < 48) || (charCode > 57)))
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
	    
	    function addToCart(){
	    	var xmlhttp;
	    	if (window.XMLHttpRequest) {
	    		// code for IE7+, Firefox, Chrome, Opera, Safari
	    		xmlhttp=new XMLHttpRequest();
	    	} else {
	    		// code for IE6, IE5
	    		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); 
	    	}            
	    	xmlhttp.onreadystatechange=function() {            
	    		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
	    			evt.target.append("<p>Added to Cart</p>");
	    		} 
	    	}       
    		xmlhttp.open("POST","ajax_info.txt",true);            
    		xmlhttp.send($("#addItem"));
	    }
	</script>
</body>
</html>
