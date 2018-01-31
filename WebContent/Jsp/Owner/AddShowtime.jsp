<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Showtime Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="../../css/GeneralStyle.css">
</head>
<body class="container">

	<div class="row">
		<a href="/Owner/OwnerHomePage.jsp" class="btn btn-default">Owner Homepage</a>
	   	<a href="/Owner/ManageShowtimes.jsp" class="btn btn-default">Manage Showtimes</a>
	   	<button type="button" class="btn btn-default">Log Out</button>
	</div>

	<h2>Add Showtime</h2>

	<form>
		<div class="form-group">
			<label for="movieGenre">Select Movie:</label>
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Select Movie
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="#">Die Hard</a>
					<a class="dropdown-item" href="#">Avatar</a>
					<a class="dropdown-item" href="#">Romeo/Juliet</a>
					<a class="dropdown-item" href="#">Star Wars</a>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="showtimeStart">Starting Time:</label>
			<input type="time" class="form-control" id="showtimeStart" placeholder="1:00">
		</div>
		<div class="form-group">
			<label for="showtimeEnd">Ending Time</label>
			<input type="time" class="form-control" id="showtimeEnd" placeholder="2:30">
		</div>
		<!-- TODO Hook up action to add showtime -->
		<button type="submit" class="btn btn-primary">Add Showtime</button>
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

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