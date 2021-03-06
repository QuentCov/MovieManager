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
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<form action="${pageContext.request.contextPath}/ViewOrders">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="View Orders">
			</form>
			<form action="${pageContext.request.contextPath}/UpdateShoppingCart">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="Checkout">
			</form>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<form name="item" action="${pageContext.request.contextPath}/TheatreAndMovieSearchQueryServlet" >
			<input type="hidden" name="CSRFToken" value="${CSRFToken}">
			<label for="theatre">Search For Theatres Near You!: </label>
			<select name="theatre" id="theatre" onchange="this.form.submit()">
				<option value="none">Select a Theatre:</option>
				<c:forEach items="${theatres}" var="theatre">
				    <option value="${theatre.getName()}"><c:out value="${theatre.getName()}"/></option>
				</c:forEach>
			</select>
      	</form>
		<form name="item" action="${pageContext.request.contextPath}/TheatreAndMovieSearchQueryServlet" >
			<input type="hidden" name="CSRFToken" value="${CSRFToken}">
			<label for="movieSearchString">Search For Movies:</label>
			<input type="text" class="form-control" id="movieSearchString" name="movieSearchString" placeholder="Search Movies">
        	<input type="submit" class="btn btn-light" type="button" value="Go!"/>
      	</form>
      	<form name="item" action="${pageContext.request.contextPath}/TheatreAndMovieSearchQueryServlet" >
      		<input type="hidden" name="CSRFToken" value="${CSRFToken}">
			<label for="date">Search For Movies by Date:</label>
			<input id="date" name="date" type="date" value="--/--/--"/>
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
