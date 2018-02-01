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
	
	<form name="userForm" action=Login method="get">
		UserName: <input type=text name=userName><br>
		Password: <input type=password name=password><br>
		<input type=submit value=Login><br>
	</form>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>