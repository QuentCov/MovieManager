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
			<a href="${pageContext.request.contextPath}/ViewAndCheckoutShoppingCart.jsp" class="btn btn-primary">Checkout</a>
			<a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<form>
			<div class="form-group">
				<label for="movieGenre">Available Theatres:</label>
				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Select Theatre
					</button>
					<div id="theatres" class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<c:forEach items="${theatres}" var="current">
							<input type="hidden" id="theatre" value="${ current.getName() }"/>
							<a href="${pageContext.request.contextPath}/TheatreAndMovieSearchQueryServlet" class="dropdown-item">${ current.getName() }</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</form>
		<form name="item" action="${pageContext.request.contextPath}/TheatreAndMovieSearchQueryServlet" >
			<input type="text" class="form-control" id="movieSearchString" name="movieSearchString" placeholder="Search Movies">
        	<input type="submit" class="btn btn-light" type="button" value="Go!"/>
      	</form>
		<form>
			<div class="form-group">
				<input id="date" type="date">
			</div>
			<span class="input-group-btn">
				<!-- TODO: There has to be a better way to do this. -->
	        	<a href="SearchMovieByDate" class="btn btn-light" type="button">Search By Date</a>
	      	</span>
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
