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
		<div class="row justify-content-around">
			<a href="ViewOrders.jsp" class="btn btn-primary">View Orders</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<form>
			<div class="form-group">
				<label for="movieGenre">Available Theatres:</label>
				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Select Theatre
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="#">Theatre 1</a>
						<a class="dropdown-item" href="#">Theatre 2</a>
						<a class="dropdown-item" href="#">Theatre 3</a>
						<a class="dropdown-item" href="#">Theatre 4</a>
						<a class="dropdown-item" href="#">Theatre 5</a>
						<a class="dropdown-item" href="#">Theatre 6</a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search Movies">
				<span class="input-group-btn">
		        	<a href="MovieSearchResults.jsp" class="btn btn-light" type="button">Go!</a>
		      	</span>
			</div>
		</form>
	</div>
	
	<%@ include file="/_partials/scripts.html" %>
	<script>
		$(document).ready(function(){
			$(".dropdown-menu a").click(function(){
				  $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
				  $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
			});
		});
	</script>
	
</body>
</html>
