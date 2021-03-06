<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Login</title>
</head>
<body>
	<div class="container">
	<h1>Login to Movie Manager</h1>

	<form name="userForm" action="${pageContext.request.contextPath}/Login" method="post">
		<div class="form-group">
			<label for="userName">UserName:</label>
			<input type="text" class="form-control" id="userName" name="userName" placeholder="UserName">
		</div>
		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Password">
		</div>
		<div class="row">
			<input type="submit" class="btn btn-primary col-sm-2" value=Login><br>
			<a href="Update.jsp" class="btn btn-link col-sm-4">Update Password</a>
			<a href="Registration.jsp" class="btn btn-link col-sm-4">Not a user yet? Register here</a>
		</div>
	</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
