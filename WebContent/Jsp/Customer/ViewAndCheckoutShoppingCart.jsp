<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Shopping Cart</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="offset-md-7 col-md-2">
				<a href="CustomerHomePage.jsp" class="btn btn-default">Home</a>
			</div>
			<div class="offset-md-1 col-md-2">
			    <a href="/Login.jsp" class="btn btn-default">Sign Out</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2">
				The Bee Movie
			</div>
			<div class="col-sm-1">
				13 Tickets
			</div>
			<div class="col-sm-1">
				$142.36
			</div>
			<div class="col-sm-1">
				De Marco Theatre # 2
			</div>
			<div class="col-sm-2">
				08/16/2018, 6:00 PM
			</div>
			<div class="col-lg-3">
				<img src="https://vignette.wikia.nocookie.net/beemovie/images/f/f7/Bee-movie-disneyscreencaps_com-2147.jpg/revision/latest?cb=20150208074457">
			</div>
			<div class="col-lg-1">
				<a href="ViewAndCheckoutShoppingCart.jsp" class="btn btn-default">Remove</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2">
				Get Out
			</div>
			<div class="col-sm-1">
				1 Ticket
			</div>
			<div class="col-sm-1">
				$12.37
			</div>
			<div class="col-sm-1">
				De Marco Theatre # 6
			</div>
			<div class="col-sm-2">
				08/30/2018, 2:00 PM
			</div>
			<div class="col-lg-3">
				<img src="http://www.litemovies.com/wp-content/uploads/2017/02/get-out-thumbnail-57f54b46b71d6-1.png">
			</div>
			<div class="col-lg-1">
				<a href="ViewAndCheckoutShoppingCart.jsp" class="btn btn-default">Remove</a>
			</div>
		</div>
		<div class="row">
			<div class="offset-md-3 col-sm-3">
				Total: $154.73
			</div>
			<div class="offset-md-3 col-sm-3">
				<a href="CustomerTransaction.jsp" class="btn btn-default">Checkout</a>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>