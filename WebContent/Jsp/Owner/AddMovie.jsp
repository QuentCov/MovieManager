<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Add Movie</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
		<h2>Add Movie</h2>
		<form action="${pageContext.request.contextPath}/AddMovie" name="addMovieForm" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="movieName">Movie Name:</label>
				<input type="text" class="form-control" id="movieName" name="movieName" placeholder="Movie Name" required>
			</div>
			<div class="form-group">
				<label for="movieGenre">Movie Genre:</label>
				<select class="form-control" id="movieGenre" name="movieGenre" required>
					<option>Horror</option>
					<option>Action</option>
					<option>Comedy</option>
					<option>Adventure</option>
					<option>Romance</option>
					<option>Sci-Fi</option>
				</select>
			</div>
			<div class="form-group">
				<label for="movieThumbnail">Movie Thumbnail:</label>
				<input type="file" class="form-control-file" id="movieThumbnail" name="movieThumbnail" placeholder="Movie Thumbnail" required>
			</div>
			<div class="form-group">
				<label for="movieDescription">Movie Description:</label>
				<input type="text" class="form-control" id="movieDescription" name="movieDescription" placeholder="Movie Description" required>
			</div>
			<div class="form-group">
				<label for="movieRuntime">Movie Runtime:</label>
				<input type="text" class="form-control" id="movieRuntime" name="movieRuntime" placeholder="Movie Runtime" required>
			</div>
			<div class="form-group">
				<label for="movieRating">Movie Rating:</label>
				<input type="text" class="form-control" id="movieRating" name="movieRating" placeholder="Movie Rating" required>
			</div>

			<input type="submit" class="btn btn-primary" value="Add Movie">
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
