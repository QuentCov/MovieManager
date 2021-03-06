<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<title>Confirm Order</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-around">
			<div class="btn btn-secondary">Hello, <c:out value="${user.getFullName() }"/></div>
			<a href="Jsp/Customer/CustomerHomePage.jsp" class="btn btn-primary">Home</a>
			<form action="${pageContext.request.contextPath}/ViewOrders">
				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
            	<input type="submit" class="btn btn-primary" value="View Orders">
			</form>
		    <a href="${pageContext.request.contextPath}/Logout" class="btn btn-primary">Log Out</a>
		</div>
		<h1>Transaction Page</h1>
		<h3 style="color:blue;" id="resultsBox"></h3>
		<button id="printButton" class="btn btn-primary" style="display: none;">Print</button>
		<h3>Purchases</h3>
		<div class="container">
	        <div class="row">
		        <div class="col-sm-6">Movies</div>
				<div class="col-sm-2">Tickets Bought</div>
				<div class="col-sm-2">Total Price</div>
	        </div>
	        <c:forEach items="${cart}" var="order">
	        	<div class="row">
	        	<div class="col-sm-6">
	    			<c:forEach items="${order.getShowings()}" var="movie"  varStatus="loop">
		    			<p><c:out value="${movie.getMovie().getName()}"/></p>
		        	</c:forEach>
	        	</div>
	            <div class="col-sm-2"><c:out value="${order.getTicketCount()}"/></div>
	            <div class="col-sm-2"><c:out value="${order.getCost()}"/></div>
	            </div>
	        </c:forEach>
		</div>
		<h5>Total cost: $
			<span id="totalCost">${totalCost}</span>
		</h5>
		<br>
		<form name="creditForm" method="POST" onsubmit="confirm_function(event);">
			<input type="hidden" name="CSRFToken" value="${CSRFToken}">
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
					<option value="none">Select Your Card Type:</option>
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
					<option value="none">Select the card's expiration month:</option>
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
				<option value="none">Select the card's expiration year:</option>
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
 				<input type="hidden" name="CSRFToken" value="${CSRFToken}">
				<input type="submit" class="btn btn-primary" value="Confirm Payment">
			</div>
		</form>
		<div class="row">
			<form method="GET" action="${pageContext.request.contextPath}/UpdateShoppingCart">
				<div class="offset-md-8 col-md-2">
					<input type="hidden" name="CSRFToken" value="${CSRFToken}">
					<input type="submit" class="btn btn-primary" value="Cancel Payment">
				</div>
			</form>
		</div>
	</div>
	<c:set var="bankingUrl" value="${bankingUrl}" />
	<script>
		function confirm_function(event) {
			event.preventDefault();
			var fName = document.forms["creditForm"]["fName"].value;
			var lName = document.forms["creditForm"]["lName"].value;
			var cardNumber = document.forms["creditForm"]["cardNumber"].value;
			var cardType = document.forms["creditForm"]["cardType"].value;
			var cvv = document.forms["creditForm"]["cvv"].value;
			var month = document.forms["creditForm"]["month"].value;
			var year = document.forms["creditForm"]["year"].value;
			var bAddress = document.forms["creditForm"]["bAddress"].value;
			var sAddress = document.forms["creditForm"]["sAddress"].value;
			var CSRFToken = document.forms["creditForm"]["CSRFToken"][0].value;

			// check for valid data
			if(fName === "") {
				alert("First Name must be filled out.");
				return false;
			}

			if(lName === "") {
				alert("Last Name must be filled out.");
				return false;
			}

			if(cardNumber === "") {
				alert("Your card number must be filled out.");
				return false;
			}

			if(cardType === "none") {
				alert("Please select the card type.");
				return false;
			}

			if(cvv === "") {
				alert("Your cvv must be filled out. Your cvv is the 3-digit number on the back of your card.");
				return false;
			}

			if(month === "none") {
				alert("Please select the card's expiry month.");
				return false;
			}

			if(year === "none") {
				alert("Please select the card's expiry year.");
				return false;
			}

			if(bAddress === "") {
				alert("Billing Address must be filled out.");
				return false;
			}

			if(sAddress === "") {
				alert("Shipping Address must be filled out.");
				return false;
			}

			var cost = $("#totalCost").text();

			var payload = {
				cardType: cardType,
				cardNumber: cardNumber,
				cvv: cvv,
				month: month,
				year: year,
				totalCost: cost,
				CSRFToken: CSRFToken
			}
			var bankingUrl = "${bankingUrl}";
			$.post(bankingUrl + "/BankServlet", payload)
				.done(function(data) {
	                if(data.transactionResult === 1) {
	                    place_order_function(payload);
	                    $("#printButton").show();
		                $("#resultsBox").text(data.transactionMessage);
	                } else {
	 	               alert(data.transactionMessage);
	                }

	            })
	            .fail(function(data) {
	               alert(data.transactionMessage);
	            });
		}

		function place_order_function(payload){
			$.post("/MovieManager/PlaceOrder", payload)
				.done(function(data) {
					if(data.processingResult === 1) {
		                $("#resultsBox").text(data.processingMessage);
					} else {
						alert(data.processingMessage);
					}
	            });
		}

	</script>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>
