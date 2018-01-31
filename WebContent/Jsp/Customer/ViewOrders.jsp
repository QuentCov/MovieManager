<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/GeneralStyle.css">
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
			    <a href="/Login.jsp" class="btn btn-default">Sign Out</a>
			</div>
		</div>
		<div class="row">
    		<div class="col-sm">
    			Order 1
    		</div>
    		<div class="col-sm">
    			Order 2
    		</div>
    		<div class="col-sm">
    			Order 3
			</div>
		</div>
		<br>
		<div class="row">
    		<div class="col-sm">
    			Order 4
    		</div>
    		<div class="col-sm">
    			Order 5
    		</div>
    		<div class="col-sm">
    			Order 6
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</html>