<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Login Here</title>
<script>
    // Check if the user is logged out and disable the back button
    window.addEventListener('popstate', function (event) {
        if (sessionStorage.getItem('isLoggedIn')) {
            window.location.href = '/home'; // Redirect to the login page
        }
    });

    // Set a session flag to indicate that the user is logged out
    sessionStorage.setItem('isLoggedIn', 'true');
</script>
</head>
<body>
	<div class="container col-md-offset-3 col-md-5 mt-5">
		<form action="loginaccess" method="post">
			  <div class="form-group">
			  
			    <label>Username</label>
			    <input type="email" class="form-control" name="username" placeholder="Enter username">
			  </div>
			  <div class="form-group">
			    <label>Password</label>
			    <input type="password" class="form-control" name="password" placeholder="Enter password">
			  </div>
		  <button type="submit" class="btn btn-success">Submit</button>
		</form>
	</div>	
</body>
</html>