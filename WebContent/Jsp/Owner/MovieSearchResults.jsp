<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Search Results</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<a href="OwnerHomePage.jsp" class="btn btn-primary">Owner Homepage</a>
			<a href="../Login.jsp" class="btn btn-primary">Log Out</a>
		</div>
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
