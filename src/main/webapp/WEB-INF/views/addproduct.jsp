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
<center><h2 class="m-2">Add Product</h2></center>
	<form class="container col-md-offset-3 col-md-6 mt-3" action="addProduct" method="post" enctype="multipart/form-data">
			  <div class="form-group">
			    <label>Product Name</label>
			    <input type="text" class="form-control" name="pname" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Product Price</label>
			    <input type="text" class="form-control" name="price" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Description</label>
			    <input type="text" class="form-control" name="description" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Main Image</label><br> 
			    <input type="file" class="" name="image" accept="image/*" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Product Images</label><br>
			    <input type="file" class="" name="images" accept="image/*" multiple="multiple">
			</div>
		  <button type="submit" class="btn btn-success">Submit</button>
		  <a href="/product" class="btn btn-danger">Back</a>
	</form>
</body>
</html>