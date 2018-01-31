<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Movie Search Results Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/GeneralStyle.css">
</head>
<body class="container">

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
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>