<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product Page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="text-center">
    	<h2 class="m-2">Update Brand Details</h2>
	</div>
	<form class="container col-md-offset-3 col-md-6 mt-3" action="/updateBrand/${brandDetails.id}" method="post" enctype="multipart/form-data">
		  <div class="form-group">
		    <label>Brand Name</label>
		    <input type="text" class="form-control" name="bname" value="${brandDetails.bname}" placeholder="">
		  </div>
		  <div class="form-group">
		    <label>Main Image</label><br>
		    <img alt="Old Image" src="${brandDetails.image}" width="150" height="150" style="object-fit:contain;"><br>
		    <input type="file" class="" name="image" accept="image/*" placeholder="">
		  </div>
		<button type="submit" class="btn btn-success">Submit</button>
	  	<a href="/brand" class="btn btn-danger">Back</a>
	</form>
</body>
</html>