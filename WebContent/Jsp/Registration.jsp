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
		<form name="userForm" action=${pageContext.request.contextPath}/Registration onsubmit="return validateForm()" method="post">
			<div class="form-group">
				<label for="userName">Email Address:</label>
				<input type="text" class="form-control" id="userName" name="userName" placeholder="User Name">
			</div>
			<div class="form-group">
				<label>User Type:</label>
			</div>
			<div class="form-group form-check-inline">
				<div>
					<input class="form-check-input" type="radio" name="userType" id="customer" value="Customer">
					<label class="form-check-label" for="customer">Customer</label>
					<input class="form-check-input" type="radio" name="userType" id="owner" value="Owner">
					<label class="form-check-label" for="owner">Owner</label>
				</div>
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Password">
			</div>
			<div class="form-group">
				<label for="passwordConfirm">Confirm Password:</label>
				<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Confirm Password">
			</div>
			
			<div class="form-group">
				<label for="fullName">Full Name:</label>
				<input type="text" class="form-control" id="fullName" name="fullName" placeholder="Full Name">
			</div>
			<div class="form-group">
				<label for="address1">Address 1:</label>
				<input type="text" class="form-control" id="address1" name="address1" placeholder="Address 1">
			</div>
			<div class="form-group">
				<label for="address2">Address 2:</label>
				<input type="text" class="form-control" id="address2" name="address2" placeholder="">
			</div>
			<div class="form-group">
				<label for="city">City:</label>
				<input type="text" class="form-control" id="city" name="city" placeholder="City">
			</div>
			<div class="form-group form-check-inline">
				<select id="stateAbbreviation" name="stateAbbreviation">
					<option value="">State:</option>
					<option value="AL">Alabama</option>
					<option value="AK">Alaska</option>
					<option value="AZ">Arizona</option>
					<option value="AR">Arkansas</option>
					<option value="CA">California</option>
					<option value="CO">Colorado</option>
					<option value="CT">Connecticut</option>
					<option value="DE">Delaware</option>
					<option value="DC">District Of Columbia</option>
					<option value="FL">Florida</option>
					<option value="GA">Georgia</option>
					<option value="HI">Hawaii</option>
					<option value="ID">Idaho</option>
					<option value="IL">Illinois</option>
					<option value="IN">Indiana</option>
					<option value="IA">Iowa</option>
					<option value="KS">Kansas</option>
					<option value="KY">Kentucky</option>
					<option value="LA">Louisiana</option>
					<option value="ME">Maine</option>
					<option value="MD">Maryland</option>
					<option value="MA">Massachusetts</option>
					<option value="MI">Michigan</option>
					<option value="MN">Minnesota</option>
					<option value="MS">Mississippi</option>
					<option value="MO">Missouri</option>
					<option value="MT">Montana</option>
					<option value="NE">Nebraska</option>
					<option value="NV">Nevada</option>
					<option value="NH">New Hampshire</option>
					<option value="NJ">New Jersey</option>
					<option value="NM">New Mexico</option>
					<option value="NY">New York</option>
					<option value="NC">North Carolina</option>
					<option value="ND">North Dakota</option>
					<option value="OH">Ohio</option>
					<option value="OK">Oklahoma</option>
					<option value="OR">Oregon</option>
					<option value="PA">Pennsylvania</option>
					<option value="RI">Rhode Island</option>
					<option value="SC">South Carolina</option>
					<option value="SD">South Dakota</option>
					<option value="TN">Tennessee</option>
					<option value="TX">Texas</option>
					<option value="UT">Utah</option>
					<option value="VT">Vermont</option>
					<option value="VA">Virginia</option>
					<option value="WA">Washington</option>
					<option value="WV">West Virginia</option>
					<option value="WI">Wisconsin</option>
					<option value="WY">Wyoming</option>
				</select>
			</div>
			<div class="form-group">
				<label for="zipCode">ZipCode:</label>
				<input type="text" class="form-control" id="zipCode" name="zipCode" placeholder="ZipCode">
			</div>

			<div class="row">
				<input type="submit" class="btn btn-primary col-sm-2" value=Register><br>
				<a href="Login.jsp" class="btn btn-link col-sm-4">Already registered? Log in here</a>
			</div>
		</form>
	</div>
	<script>
		function validateForm() {
			var user = document.forms["userForm"]["userName"].value;
			var type = document.forms["userForm"]["userType"].value;
			var pass = document.forms["userForm"]["password"].value;
			var passConf = document.forms["userForm"]["passwordConfirm"].value;
			var fullName = document.forms["userForm"]["fullName"].value;
			var address = document.forms["userForm"]["address1"].value;
			var city = document.forms["userForm"]["city"].value;
			var state = document.forms["userForm"]["stateAbbreviation"].value;
			var zip = document.forms["userForm"]["zipCode"].value;
			
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
			
			if(fullName === "") {
				alert("Please give us the name you want us to call you by.");
				return false;
			}
			
			if(address === "") {
				alert("Address 1 must be filled out.");
				return false;
			}
			
			if(city === "") {
				alert("City must be filled out.");
				return false;
			}
			
			if(state === "") {
				alert("Please select your state.");
				return false;
			}
			
			if(zip === "") {
				alert("ZipCode must be filled out.");
				return false;
			}
			
			return true;
		}
	</script>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
