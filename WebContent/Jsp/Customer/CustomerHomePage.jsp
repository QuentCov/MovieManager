<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Movie Manager</title>
</head>
<body>
	<h1>Movie Manager! Your Ticket to the Silver Screen!</h1>
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search" aria-describedby="basic-addon2">
					<span class="input-group-btn">
			        	<a href="/MovieSearchResults.jsp" class="btn btn-search" type="button"><i class="fa fa-search fa-fw"></i> Go!</a>
			      	</span>
				</div>
			</div>
			<div class="offset-md-5 col-md-2">
				<a href="/Login.jsp" class="btn btn-default">Sign In</a>
			</div>
		</div>
		<br>
		<div class="row">
    		<div class="col-sm">
    			Theater 1
    		</div>
    		<div class="col-sm">
    			Theater 2
    		</div>
    		<div class="col-sm">
    			Theater 3
			</div>
		</div>
		<br>
		<div class="row">
    		<div class="col-sm">
    			Theater 4
    		</div>
    		<div class="col-sm">
    			Theater 5
    		</div>
    		<div class="col-sm">
    			Theater 6
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</html>