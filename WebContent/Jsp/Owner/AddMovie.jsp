<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Add Movie Page</title>
</head>
<body>	
	<div class="container">
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
			<button type="submit" class="btn btn-primary" value="addMovie">Save</button>
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Cancel</a>
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