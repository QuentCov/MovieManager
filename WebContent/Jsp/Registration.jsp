<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Registration</title>
</head>
<body>
	<div class="container">
		<a href="Login.jsp" class="btn btn-primary">Already registered? Click here</a>
	
		<h1>Registration</h1>
		<form name="userForm" action=${pageContext.request.contextPath}/Registration onsubmit="return validateForm()" method="post">
			<div class="form-group">
				<label for="userName">UserName:</label>
				<input type="text" class="form-control" id="userName" name="userName" placeholder="UserName">
			</div>
			<div class="form-check form-check-inline">
				<h6>User Type:</h6>
				<input class="form-check-input" type="radio" name="userType" id="customer" value="Customer">
				<label class="form-check-label" for="customer">Customer</label>
				<input class="form-check-input" type="radio" name="userType" id="owner" value="Owner">
				<label class="form-check-label" for="owner">Owner</label>
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Password">
			</div>
			<div class="form-group">
				<label for="passwordConfirm">Confirm Password:</label>
				<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Confirm Password">
			</div>
			<input type="submit" class="btn btn-primary" value=Register><br>
		</form>
	</div>
	<script>
		function validateForm() {
			var user = document.forms["userForm"]["userName"].value;
			var type = document.forms["userForm"]["userType"].value;
			var pass = document.forms["userForm"]["password"].value;
			var passConf = document.forms["userForm"]["passwordConfirm"].value;
			if(user === "") {
				alert("Username must be filled out.");
				return false;
			}
			
			if(type === "") {
				alert("Type must be filled out.");
				return false;
			}
	
			if(pass === "") {
				alert("Password must be filled out.");
				return false;
			}
	
			if(passConf === "") {
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
