<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product Page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<center><h2 class="m-2">Add Brand </h2></center>
	<form class="container col-md-offset-3 col-md-6 mt-3" action="addBrand" method="post" enctype="multipart/form-data">
			  <div class="form-group">
			    <label>Brand Name</label>
			    <input type="text" class="form-control" name="bname" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Main Image</label><br>
			    <input type="file" class="" accept="image/*" name="image" placeholder="">
			  </div>
			<button type="submit" class="btn btn-success">Submit</button>
		  <a href="/brand" class="btn btn-danger">Back</a>
	</form>
</body>
</html>