<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/_partials/headTags.html" %>
	<title>Search Results</title>
</head>
<body>
	<div class="container">
		<h1>Search Results for Spongebob Squarepants: The New Musical</h1>
		<div class="row">
			<div class="col-5">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Spongebob Squarepants: The New Musical" aria-describedby="basic-addon2">
					<span class="input-group-btn">
			        	<button class="btn btn-search" type="button"><i class="fa fa-search fa-fw"></i> Search</button>
			      	</span>
				</div>
			</div>
			<div class="col-3">
				<a href="CustomerHomePage.jsp" class="btn btn-default">Home</a>
				<a href="ViewOrders.jsp" class="btn btn-default">View Orders</a>
			</div>
			<div class="col-4">
			    <a href="/Login.jsp" class="btn btn-default">Sign Out</a>
			</div>
		</div>
		<br>
		<div class="row">
    		<div class="col-sm">
    			Madden Theatre
    		</div>
    		<div class="col-sm">
    			Theatre #89
    		</div>
    		<div class="col-sm">
    			08/16/18 6:39
    		</div>
    		<div class="col-sm">
    			30 Available Seats
    		</div>
    		<div class="col-sm">
    			$40.00
    		</div>
    		<div class="col-sm">
    			<img src="https://img.wennermedia.com/article-leads-horizontal/inside-spongebob-play-e579f90d-908e-4ffd-9674-4ae8a0861dbe.jpg">
    		</div>
    	</div>
	</div>
	<%@ include file="/_partials/scripts.html" %>
</body>
</html>