<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Movie Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/GeneralStyle.css">
</head>
<body class="container">
	
	<h2>Add Movie Page</h2>
	
	<form>
		<div class="form-group">
			<label for="movieName">Movie Name:</label>
			<input type="email" class="form-control" id="movieName" name="movieName" placeholder="Movie Name">
		</div>
		<div class="form-group">
			<label for="movieGenre">Movie Genre:</label>
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Select Movie Genre
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="#">Horror</a>
					<a class="dropdown-item" href="#">Action</a>
					<a class="dropdown-item" href="#">Comedy</a>
					<a class="dropdown-item" href="#">Adventure</a>
					<a class="dropdown-item" href="#">Romance</a>
					<a class="dropdown-item" href="#">Sci-Fi</a>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="movieThumbnail">Movie Thumbnail:</label>
			<input type="file" class="form-control" id="movieThumbnail" name="movieThumbnail" placeholder="Movie Thumbnail">
		</div>
		<div class="form-group">
			<label for="movieDescription">Movie Description:</label>
			<input type="text" class="form-control" id="movieDescription" name="movieDescription" placeholder="Movie Description">
		</div>
		<div class="form-group">
			<label for="movieRuntime">Movie Runtime:</label>
			<input type="text" class="form-control" id="movieRuntime" name="movieRuntime" placeholder="Movie Runtime">
		</div>
		<div class="form-group">
			<label for="movieRating">Movie Rating:</label>
			<input type="email" class="form-control" id="movieRating" name="movieRating" placeholder="Movie Rating">
		</div>
		
		<!-- TODO create servlet to add movie and cancel/redirect -->
		<button type="submit" class="btn btn-primary" value="addMovie">Add Movie</button>
		<a href="/Owner/OwnerHomePage.jsp" class="btn btn-default">Cancel</a>
		<!-- <button type="reset" class="btn btn-primary" value="cancelAdd">Cancel</button> -->
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