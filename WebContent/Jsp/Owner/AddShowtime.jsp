<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Add Showtime</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		   	<a href="ManageShowtimes.jsp" class="btn btn-primary">Manage Showtimes</a>
		   	<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>

		<h1>Add Showtime</h1>
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
			<a href="AddShowtimeConfirmation.jsp" class="btn btn-primary">Add Showtime</a>
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
