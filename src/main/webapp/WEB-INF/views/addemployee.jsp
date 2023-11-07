<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<center><h2>Add Employee</h2></center>
	<form class="container col-md-offset-3 col-md-6 mt-3" action="addform" method="post">
			  <div class="form-group">
			    <label>First Name</label>
			    <input type="text" class="form-control" name="fname" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Last Name</label>
			    <input type="text" class="form-control" name="lname" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Phone Number</label>
			    <input type="text" class="form-control" name="phone" placeholder="" pattern="[0-9]{10}" maxlength="10" required>
			  </div>
			  <div class="form-group">
			    <label>Address</label>
			    <input type="text" class="form-control" name="address" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Email ID</label>
			    <input type="email" class="form-control" name="email" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Password</label>
			    <input type="password" class="form-control" name="password" placeholder="">
			</div>
		  <button type="submit" class="btn btn-success">Submit</button>
		  <a href="/home" class="btn btn-danger">Back</a>
	</form>
</body>
</html>