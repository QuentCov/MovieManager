<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - View Orders</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Filter by...
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="#">Order Number</a>
				    	<a class="dropdown-item" href="#">Order Total</a>
				    	<a class="dropdown-item" href="#">Ordered Date</a>
					</div>
				</div>
			</div>
			<div class="offset-md-4 col-md-2">
				<a href="CustomerHomePage.jsp" class="btn btn-default">Home</a>
			</div>
			<div class="offset-md-1 col-md-2">
			    <a href="/Login.jsp" class="btn btn-default">Log Out</a>
			</div>
		</div>
		<div class="row">
    		<div class="col-sm">
    			Order 1 
    		<div class="col-lg-1">
				<a href="ManageOrder.jsp" class="btn btn-default">Manage Order 1</a>
			</div>
    	</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>