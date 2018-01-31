<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/GeneralStyle.css">
<title>Movie Manager - Registration</title>
</head>
<body>
	<h1>Registration</h1>

	<a href="Login.jsp" class="btn btn-default">Login</a>
	<form name="userForm" action=${pageContext.request.contextPath}/Registration onsubmit="return validateForm()" method="post">
		UserName: <input type=text name=userName><br>
		Password: <input type=password name=password><br>
		Confirm Password: <input type=password name=passwordConfirm><br>
		<input type=submit value=Register><br>
	</form>

</body>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</html>
