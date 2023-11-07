<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <style>
        * {
			padding: 0;
			margin: 0;
			box-sizing: border-box;
			font-family: arial, sans-serif;
		}
		.header {
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding: 15px 30px;
			background: light;
			color: #fff;
		}
		.u-name {
			font-size: 20px;
			padding-left: 0px;
		}
		.u-name b {
			color: green;
		}
		.header i {
			font-size: 30px;
			cursor: pointer;
			color: #fff;
		}
		.header i:hover {
			color: #127b8e;
		}
		.user-p {
			text-align: center;
			padding-left: 10px;
			padding-top: 25px;
		}
		.user-p img {
			width: 100px;
			border-radius: 60%;
		}
		.user-p h4 {
			color: #ccc;
			padding: 5px 0;

		}
		.side-bar {
			width: 250px;
			background: #262931;
			min-height: 95vh;
			transition: 500ms width;
            
		}
		.body {
			display: flex;
		}
		.section-1 {
			width: 100%;
			background-size: cover;
			background-position: center;
			display: flex;
			align-items: center;
			flex-direction: column;
			background-co4lor:;
		}
		.section-1 h1 {
			color: #fff;
			font-size: 60px;
		}
		.section-1 p {
			color: #127b8e;
			font-size: 20px;
			background: #fff;
			padding: 7px;
			border-radius: 5px;
		}
		.side-bar ul {
			margin-top: 20px;
			list-style: none;
		}
		.side-bar ul li {
			font-size: 16px;
			padding: 15px 0px;
			padding-left: 20px;
			transition: 500ms;
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
		}
		.side-bar ul li:hover 
		{
			background: #127b8e;
		}
		.side-bar ul li a 
		{
			text-decoration: none;
			color: #eee;
			cursor: pointer;
			letter-spacing: 1px;
		}
		
		.side-bar ul li a i
		 {
			display: inline-block;
			padding-right: 10px;
			font-size: 23px;
		}
		#checkbox {
			display: none;
		}
		#checkbox:checked ~ .body .side-bar 
		{
			width: 60px;
		}
		#checkbox:checked ~ .body .side-bar .user-p
		{
			visibility: hidden;
		}
		#checkbox:checked ~ .body .side-bar a span
		{
			display: none;
		}
    </style>
</head>
	<script>
        window.addEventListener('pageshow', function (event)
        {
            if (event.persisted) 
            {
                location.reload(); // Reload the page when the user returns
            }
        });
    </script>
<body>
	<header class="header" style="background-color:#ADD8E6">
		<h2 class="u-name"><b>ADMIN DASHBOARD</b></h2>
		<a href="/logout" class="btn btn-secondary">Logout</a>
	</header> 
	
	
	<div class="body">
		<nav class="side-bar">
			<div class="user-p">
	         	<img  src="/image/admin.png" alt="Admin Image">
				<h4>Admin</h4>
			</div>
			<ul>
				<li>
					<a href="/home">
						<i class="fa fa-" aria-hidden="true"></i>
						<span>Employee</span>
					</a>
				</li>
				<li>
					<a href="/product">
						<i class="fa fa-" aria-hidden="true"></i>
						<span>Product</span>
					</a>
				</li>
				<li>
					<a href="/brand">
						<i class="fa fa-" aria-hidden="true"></i>
						<span>Brand</span>
					</a>
				</li>
				
			</ul>
		</nav>
		<section class="col-md-9">
		<h2 class=" offset-md-5 mt-2">Welcome Admin</h2>
		<a href="/add" class="btn btn-primary mt-2 col-md-offset-3">Add</a>
			<table class="table mt-2 table-bordered">
				  <thead>
				    <tr >
				      <th>ID</th>
				      <th>First Name</th>
				      <th>Last Name</th>
				      <th scope="col">Address</th>
				       <th scope="col">Email</th>
				        <th scope="col">Phone</th>
				      <th scope="col">Action</th>
				    </tr>
				  </thead>
				  
				  <tbody>
						<h:forEach items="${employees}" var="p">
						    <tr>
						      <th scope="row">${p.id}</th>
						      <td>${p.fname}</td>
						      <td>${p.lname}</td>
						      <td>${p.address}</td>
						      <td>${p.email}</td>
						      <td>${p.phone}</td>
						      <td><a href="delete/${p.id}" class="btn btn-danger">Delete</a>
						      <a href="update/${p.id }" class="btn btn-success">Edit</a>
						      </td>
						    </tr>
						  </h:forEach>
				  </tbody>
			</table>	
		</section>
	</div>
	

</body>
</html>