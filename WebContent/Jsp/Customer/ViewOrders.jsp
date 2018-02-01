<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - View Orders</title>
</head>
<body>
	<div class="container">
		<a href="CustomerHomePage.jsp" class="btn btn-primary">Home</a>
	    <a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		<h1>View All Orders</h1>
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
		</div>
		<div class="row">
			<div class="col-sm-3"><h5>Order Number</h5></div>
			<div class="col-sm-3"><h5>Order Total</h5></div>
			<div class="col-sm-3"><h5>Ordered Date</h5></div>
			<div class="col-sm-3"><h5>Actions</h5></div>
		</div>
		<div class="row">
    		<div class="col-sm-3">
    			Order 1 
    		</div>
    		<div class="col-sm-3">
    			$154.73
    		</div>
    		<div class="col-sm-3">
    			08/15/2018, 2:00 PM
    		</div>
    		<div class="col-sm-3">
				<a href="ManageOrder.jsp" class="btn btn-primary">Manage Order 1</a>
			</div>
    	</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>