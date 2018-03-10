<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Customer Home Page</title>
</head>
<body>
	<div class="container">
		<h1>Movie Manager! Your Ticket to the Silver Screen!</h1>
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, ${user.getFullName() }</div>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
			<a href="${pageContext.request.contextPath}/UpdateShoppingCart" class="btn btn-primary">Checkout</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<form name="item" method="POST" action="${pageContext.request.contextPath}/TheatreAndMovieSearchQueryServlet" >
			<label for="movieGenre">Search For Movies</label>
			<input type="text" class="form-control" id="movieSearchString" name="movieSearchString" placeholder="Search Movies">
        	<input type="submit" class="btn btn-light" type="button" value="Go!"/>
      	</form>
	</div>

	<%@ include file="/_partials/scripts.html" %>
	<script>
		$(document).ready(function(){
			$(".dropdown-menu a").click(function(){
				  $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
				  $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
			});
		});
	</script>

</body>
</html>
