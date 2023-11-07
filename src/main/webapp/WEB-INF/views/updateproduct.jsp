<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product Page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<div class="text-center">
    	<h2 class="m-2">Update Product Details</h2>
	</div>
	<form class="container col-md-offset-3 col-md-6 mt-3" action="/updateProduct/${productDetails.id}" method="post" enctype="multipart/form-data">
			  <div class="form-group">
			    <label>Product Name</label>
			    <input type="text" class="form-control" name="pname" value="${productDetails.pname}" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Product Price</label>
			    <input type="text" class="form-control" name="price" value="${productDetails.price}" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Description</label>
			    <input type="text" class="form-control" name="description" value="${productDetails.description}" placeholder="">
			  </div>
			  <div class="form-group">
			    <label>Main Image</label><br>
			    <img alt="Old Image" src="${productDetails.image}" width="150" height="150" style="object-fit:contain;"><br>
			    <input type="file" class="" name="image" placeholder=""> 
			  </div>
			  <div class="form-group">
			  	
			    <label>Product Images</label>
			    <br>
			    <ul style="list-style: none; padding: 0; margin: 0;">
			        <c:forEach items="${productDetails.images}" var="image">
			            <li style="display: inline-block; margin-right: 10px;">
			                <img src="${image}" alt="Product Image" width="150" height="150" style="object-fit:contain;">
			            </li>
			        </c:forEach>
			    </ul>
			    <input type="file" class="" name="images" placeholder="" multiple="multiple">
			</div>

		  <button type="submit" class="btn btn-success">Submit</button>
		  <a href="/product" class="btn btn-danger">Back</a>
	</form>
</body>
</html>