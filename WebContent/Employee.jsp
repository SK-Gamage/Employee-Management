<%@page import="com.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery.min.js"></script> 
<script src="Components/employee.js"></script> 
</head>
<body>
	<div class="container">
	<div class="row">
		<div class="col">
			<h1 class="font-weight-bold">EMPLOYEE DETAILS</h1>
				<form class="row g-3" id="formEmployee" name="formEmployee" method="post" action="Employee.jsp">  
					<div class="col-md-6">
						<label class="form-label">Name:</label>  
	 	 				<input id="name" name="name" type="text"  class="form-control form-control-sm" placeholder="Enter Name" required>
					</div>
					
					<div class="col-md-6">
						<label class="form-label">Age:</label>
						<input id="age" name="age" type="text" class="form-control form-control-sm" placeholder="Enter Age" required>
					</div>    
  					
  					<div class="col-md-6">
	  					<label class="form-label">Address:</label>
	  					<input id="address" name="address" class="form-control datepicker" type="text" placeholder="Enter Address" required>
  					</div>
					 
					<div class="col-md-6">
						<label class="form-label">Email:</label>
					  	<input id="email" name="email" type="text" class="form-control form-control-sm" placeholder="Enter Email" required>
					</div>    
					
					<div class="col-md-6">
						<label class="form-label">Tel No:</label>
						<input id="telno" name="telno" type="text" class="form-control form-control-sm" placeholder="Enter Telephone No" required>
					</div>   
  					
					<div class="col-md-6">
						<label class="form-label">Type:</label>
						<input id="type" name="type" type="text" class="form-control form-control-sm" placeholder="Enter Type" required>
					</div>
					
  					<div class="col-12">
	  					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary" required>  
						<input type="hidden" id="hidEmployeeIDSave" name="hidEmployeeIDSave" value="">
  					</div>
					 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divEmployeeGrid">
					<%
						Employee empObj = new Employee();
						out.print(empObj.readEmployee());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>