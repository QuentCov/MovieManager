<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!-- If you know how this doesn't work, tell me, because this is ridiculous. -->
<jsp:include page="/MovieManager/src/servlets/TheatreLoad.java">
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Customer Home Page</title>
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
					<div id="theatres" class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item">Loading...</a>
					</div>
				</div>
			</div>
		</form>
	    <form>
			<div class="form-group">
				<input type="text" class="form-control" name="movieSearchString" placeholder="Search Movies">
				<span class="input-group-btn">
		        	<a href="TheatreAndMovieSearchQuery" class="btn btn-light" type="button">Go!</a>
		      	</span>
			</div>
		</form>
		<form>
			<div class="form-group">
				<input id="date" type="date">
			</div>
			<span class="input-group-btn">
				<!-- TODO: There has to be a better way to do this. -->
	        	<a href="SearchMovieByDate" class="btn btn-light" type="button">Search By Date</a>
	      	</span>
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
