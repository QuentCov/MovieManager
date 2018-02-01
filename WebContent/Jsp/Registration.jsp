<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Registration</title>
</head>
<body>
	<div class="container">
		<h1>Registration</h1>
	
		<a href="Login.jsp" class="btn btn-default">Login</a>
		<form name="userForm" action=${pageContext.request.contextPath}/Registration onsubmit="return validateForm()" method="post">
			UserName: <input type=text name=userName><br>
			Password: <input type=password name=password><br>
			Confirm Password: <input type=password name=passwordConfirm><br>
			<input type=submit value=Register><br>
		</form>
	</div>
	<script>
		function validateForm() {
			var user = document.forms["userForm"]["userName"].value;
			var pass = document.forms["userForm"]["password"].value;
			var passConf = document.forms["userForm"]["passwordConfirm"].value;
			if(user == "") {
				alert("Username must be filled out.");
				return false;
			}
	
			if(pass == "") {
				alert("Password must be filled out.");
				return false;
			}
	
			if(passConf == "") {
				alert("Please confirm your password");
				return false;
			}
	
			if(!pass.equals(passConf)) {
				alert("Your password and password confirmation must match.");
				return false;
			}
		}
	</script>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
