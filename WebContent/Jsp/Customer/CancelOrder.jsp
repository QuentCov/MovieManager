<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Cancel Order</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Do you want to cancel your tickets for this movie?</h1>
		<div class="row">
		    <div class="col-sm-1">Movie Title</div>
			<div class="col-sm-2">Actions</div>
		</div>
       	<div class="row">
       		<div class="col-sm-9">
    			<div class="row">
	    			<div class="col-sm-1">${cancelShowingItem.getMovie().getName()}</div>
    			</div>
			</div>
        </div>
		<div class="offset-md-3 col-sm-3">
			<a href="CancelOrderTransaction" class="btn btn-success">Confirm Cancellation</a>
		</div>
		<div class="offset-md-3 col-sm-3">
			<a href="ViewAndCheckoutShoppingCart.jsp" class="btn btn-success">Discard Cancellation</a>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
