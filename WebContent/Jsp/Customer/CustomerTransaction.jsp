<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Transaction</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, ${user.getFullName() }</div>
			<a href="${pageContext.request.contextPath}/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<a href="${pageContext.request.contextPath}/ViewOrders" class="btn btn-primary">View Orders</a>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Transaction Page</h1>
		<h3>Purchases</h3>
		<div class="container">
	        <div class="row">
		        <div class="col-sm-2">Movies</div>
				<div class="col-sm-2">Tickets Bought</div>
				<div class="col-sm-2">Total Price</div>
	        </div>
	        <div class="row">
    			<c:forEach items="${order.getShowings()}" var="movie">
	    			<div class="col-sm-2">${movie.getMovie().getName()}</div>
	        	</c:forEach>
	            <div class="col-sm-2">${order.getTicketCount()}</div>
	            <div class="col-sm-2">${order.getTotalCost}</div>
	    	</div>
		</div>
		<br>
		<form method="POST" action="${pageContext.request.contextPath}/CustomerTransactionConfirmation">
			<div class="form-group">
				<label for="fName">First Name: </label>
    			<input type="text" class="form-control" id="fName" name="fName" placeholder="First Name">
   			</div>
   			<div class="form-group">
				<label for="lName">Last Name: </label>
    			<input type="text" class="form-control" id="lName" name="lName" placeholder="Last Name">
   			</div>
   			<div class="form-group">
				<label for="cardType">Card Type: </label>
				<select class="form-control" id="cardType" name="cardType">
					<option>Visa</option>
					<option>Master</option>
					<option>Discover</option>
				</select>
			</div>
			<div class="form-group">
				<label for="cardNumber">Credit Card Number: </label>
    			<input type="number" class="form-control" id="cardNumber" name="cardNumber" placeholder="Valid Card Number">
   			</div>
   			<div class="form-group">
				<label for="cvv">CVV: </label>
    			<input type="number" class="form-control" id="cvv" name="cvv" placeholder="***">
   			</div>
   			<br>
   			<div class="form-group">
				<label for="month">Expiration Month: </label>
				<select class="form-control" id="month" name="month">
					<option>01</option>
					<option>02</option>
					<option>03</option>
					<option>04</option>
					<option>05</option>
					<option>06</option>
					<option>07</option>
					<option>08</option>
					<option>09</option>
					<option>10</option>
					<option>11</option>
					<option>12</option>
				</select>
				<label for="year">Expiration Year: </label>
				<select class="form-control" id="year" name="year">
					<option>18</option>
					<option>19</option>
					<option>20</option>
					<option>21</option>
					<option>22</option>
					<option>23</option>
					<option>24</option>
					<option>25</option>
				</select>
   			</div>
   			<br>
   			<div class="form-group">
   				<label for="bAddress">Billing Address: </label>
    			<input type="text" class="form-control" id="bAddress" name="bAddress" placeholder="">
   			</div>
   			<br>
   			<div class="form-group">
   				<label for="sAddress">Shipping Address: </label>
    			<input type="text" class="form-control" id="sAddress" name="sAddress" placeholder="">
   			</div>
   			<div class="col-md-2">
				<input type="submit" class="btn btn-primary" value="Confirm Payment">
			</div>
		</form>
		<div class="row">
			<form method="POST" action="${pageContext.request.contextPath}/UpdateShoppingCart">
				<div class="offset-md-8 col-md-2">
					<input type="submit" class="btn btn-primary" value="Cancel Payment">
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
