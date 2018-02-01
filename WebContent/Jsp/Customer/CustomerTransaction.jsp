<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Movie Manager - Transaction</title>
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
		</div>
		<div class="row">
			<div class="offset-md-3 col-sm-3">
				Total: $154.73
			</div>
		</div>
		<br>
		<form>
			<div class="form-group">
				<label for="fName">First Name: </label>
    			<input type="text" class="form-control" id="fName" placeholder="First Name">
   			</div>
   			<div class="form-group">
				<label for="lName">Last Name: </label>
    			<input type="text" class="form-control" id="lName" placeholder="Last Name">
   			</div>
   			<div class="form-group">
				<label for="cardType">Card Type: </label>
				<select class="form-control" id="cardType">
					<option>Visa</option>
					<option>Master</option>
					<option>Discover</option>
				</select>
			</div>
			<div class="form-group">
				<label for="cardNumber">Credit Card Number: </label>
    			<input type="text" class="form-control" id="cardNumber" placeholder="Valid Card Number">
   			</div>
   			<br>
   			<div class="form-group">
				<label for="month">Month: </label>
				<select class="form-control" id="month">
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
				<label for="year">Year: </label>
				<select class="form-control" id="year">
					<option>18</option>
					<option>19</option>
					<option>20</option>
					<option>21</option>
					<option>22</option>
					<option>23</option>
					<option>24</option>
					<option>25</option>
				</select>
   				<label for="cvv">CVV: </label>
    			<input type="text" class="form-control" id="cvv" placeholder="***">
   			</div>
   			<br>
   			<div class="form-group">
   				<label for="bAddress">Billing Address: </label>
    			<input type="text" class="form-control" id="bAddress" placeholder="">
   			</div>
   			<br>
   			<div class="form-group">
   				<label for="sAddress">Shiping Address: </label>
    			<input type="text" class="form-control" id="sAddress" placeholder="">
   			</div>
		</form>
		<div class="row">
			<div class="offset-md-7 col-md-2">
				<a href="CustomerTransactionConfimration.jsp" class="btn btn-default">Confirm Payment</a>
			</div>
			<div class="offset-md-1 col-md-2">
			    <a href="ViewAndCheckoutShoppingCart.jsp" class="btn btn-default">Cancel Payment</a>
			</div>
		</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>