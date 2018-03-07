<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Details</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, ${user.getFullName() }</div>
		    <a href="Jsp/Owner/OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
		    <a href="Jsp/Owner/AddMovie.jsp" class="btn btn-primary">Add Movie</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Movie Details</h1>
		<div class="row">
			<div class="col-sm-2"><h4>Movie Name</h4></div>
			<div class="col-sm-2"><h4>Genre</h4></div>
			<div class="col-sm-2"><h4>Thumbnail</h4></div>
			<div class="col-sm-2"><h4>Description</h4></div>
			<div class="col-sm-1"><h4>Runtime</h4></div>
			<div class="col-sm-1"><h4>Tickets Sold</h4></div>
			<div class="col-sm-1"><h4>Rating</h4></div>
			<div class="col-sm-1"><h4>Actions</h4></div>
		</div>
		<form action="${pageContext.request.contextPath}/MovieDetailsUpdate" name="updateMovieForm" method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="col-sm-2">
					<input type="text" class="form-control" id="movieName" name="movieName" value="${movie.getName()}">
				</div>
				<div class="col-sm-2">
					<select class="form-control" id="movieGenre" name="movieGenre">
						<option ${movie.getGenre() == "Horror" ? 'selected="selected"' : ''}>Horror</option>
						<option ${movie.getGenre() == "Action" ? 'selected="selected"' : ''}>Action</option>
						<option ${movie.getGenre() == "Comedy" ? 'selected="selected"' : ''}>Comedy</option>
						<option ${movie.getGenre() == "Adventure" ? 'selected="selected"' : ''}>Adventure</option>
						<option ${movie.getGenre() == "Romance" ? 'selected="selected"' : ''}>Romance</option>
						<option ${movie.getGenre() == "Sci-Fi" ? 'selected="selected"' : ''}>Sci-Fi</option>
					</select>
				</div>
				<div class="col-sm-2">
					<img class="img-fluid" src="data:image/jpeg;base64,${movie.renderImage()}" alt="${movie.getName()} Poster"/>
					<br/>
					<br/>
					Upload new thumbnail below
					<input type="file" class="form-control-file" id="movieThumbnail" name="movieThumbnail">
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="movieDescription" name="movieDescription" value="${movie.getDescription()}">
				</div>
				<div class="col-sm-1">
					<input type="text" class="form-control" id="movieRuntime" name="movieRuntime" value="${movie.getRuntime()}">
				</div>
				<div class="col-sm-1">
					${ticketsSold}
				</div>
				<div class="col-sm-1">
					<input type="text" class="form-control" id="movieRating" name="movieRating" value="${movie.getRating()}">
				</div>
				<div class="col-sm-1">
					<input type="hidden" id="movieId" name="movieId" value="${movieId}">
					<input type="submit" class="btn btn-primary" value="Update"/>
					<a href="Jsp/Owner/OwnerHomePage.jsp"><button type="button" class="btn btn-danger">Cancel</button></a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>

</body>
</html>
