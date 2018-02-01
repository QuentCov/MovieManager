<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Search Results Page</title>
</head>
<body>
	<div class="container">
		<h1>Movie Search Results</h1>
		<div class="row">
			<div class="col-sm-2"><h4>Movie Name</h4></div>
			<div class="col-sm-4"><h4>Description</h4></div>
			<div class="col-sm-4"><h4>Thumbnail</h4></div>
			<div class="col-sm-2"><h4>Actions</h4></div>
		</div>
		<div class="row">
			<div class="col-sm-2"><p>Avatar</p></div>
			<div class="col-sm-4"><p>Blue people movie in outer space.</p></div>
			<div class="col-sm-4"><img class="img-fluid" src="https://upload.wikimedia.org/wikipedia/en/b/b0/Avatar-Teaser-Poster.jpg" alt="Avatar Movie Thumbnail"></div>
			<div class="col-sm-2">
				<a href="MovieDetails.jsp"><button type="button" class="btn btn-primary">View Details</button></a>
			</div>
		</div>
	</div>
    <%@ include file="/_partials/scripts.html" %>
</body>
</html>
