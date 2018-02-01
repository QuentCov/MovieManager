<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager</title>
</head>
<body>
	<div class="container">
		<h1>Movie Manager! Your Ticket to the Silver Screen!</h1>
		<div class="row">
			<div class="col-md-5">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search" aria-describedby="basic-addon2">
					<span class="input-group-btn">
			        	<a href="MovieSearchResults.jsp" class="btn btn-search" type="button"><i class="fa fa-search fa-fw"></i> Go!</a>
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
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
