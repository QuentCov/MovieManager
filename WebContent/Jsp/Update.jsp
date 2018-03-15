<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Update Password</title>
</head>
<body>
	<div class="container">
	<h1>Update Password</h1>

	<form name="updateForm" action="${pageContext.request.contextPath}/Update" method="post">
		<div class="form-group">
			<label for="userName">UserName:</label>
			<input type="text" class="form-control" id="userName" name="userName" placeholder="UserName">
		</div>
		<div class="form-group">
			<label for="password">Old Password:</label>
			<input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="Old Password">
		</div>
		<div class="form-group">
			<label for="password">New Password:</label>
			<input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="New Password">
		</div>
		<div class="row">
			<input type="submit" class="btn btn-primary col-sm-2" value=Update><br>
			<a href="Login.jsp" class="btn btn-link col-sm-4">Cancel Changes</a>
		</div>
	</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
