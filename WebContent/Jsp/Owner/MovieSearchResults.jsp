<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Search Results Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-2"><h2>Movie Name</h2></div>
			<div class="col-sm-4"><h2>Description</h2></div>
			<div class="col-sm-4"><h2>Thumbnail</h2></div>
			<div class="col-sm-2"><h2>Actions</h2></div>
		</div>
		<div class="row">
			<div class="col-sm-2"><p>Avatar</p></div>
			<div class="col-sm-4"><p>Blue people movie in outer space.</p></div>
			<div class="col-sm-4"> <img src="https://upload.wikimedia.org/wikipedia/en/b/b0/Avatar-Teaser-Poster.jpg" alt="Avatar Movie Thumbnail"></div>
			<div class="col-sm-2"><button type="button" class="btn btn-primary">View Details</button></div>
		</div>
	</div>
    <%@ include file="/_partials/scripts.html" %>
</body>
</html>
