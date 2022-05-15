<%@ page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
	<html>
		<head>
		<meta charset="ISO-8859-1">
		<title>Customer Management</title>
		
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/Customer.js"></script>
		
		
		</head>
		
	 <body style="background: #CCCCCC;">
	<div class="row">
<div class="col">
  <div class="container">
  
  <div class="card mt-5">
      <div class="card-header">
	
	<center>
	<h1>Customer Management</h1>
	</center>
	
				<form id="formCustomer" name="formCustomer" method="post" action="Customer.jsp">
			Customer Name:
					<input id="customerName" name="customerName" type="text"
					class="form-control form-control-sm">
					<br> Customer Address:
					<input id="customerAddress" name="customerAddress" type="text"
					class="form-control form-control-sm">
					<br> Customer NIC:
					<input id="customerNIC" name="customerNIC" type="text"
					class="form-control form-control-sm">
					<br> Customer Email:
					<input id="customerEmail" name="customerEmail" type="text"
					class="form-control form-control-sm">
					<br>
					
					<br> Customer Phone Number:
					<input id="customerPNO" name="customerPNO" type="text"
					class="form-control form-control-sm">
					<br>
					
					<input id="btnSave" name="btnSave" type="button" value="Save"
					class="btn btn-primary btn-lg">
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				
			</div>
		</div>
	</div>
	<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
	<br>
		
		<div class="table table-dark" id="divItemsGrid">
			 <%
				 Customer customerObj = new Customer(); 
				 out.print(customerObj.readcustomer()); 
			 %>
		</div>

		
		
	</body>
	</html>