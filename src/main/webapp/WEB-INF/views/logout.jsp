<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Logout</title>
<script>
    // Check if the user is logged out and disable the back button
    if (sessionStorage.getItem('isLoggedIn')) 
    {
        window.location.replace('/login');
    }
    sessionStorage.setItem('isLoggedIn', 'false');
</script>

</head>
<body>
    <div class="text-center mt-3">
        <h1>Thank You.</h1>
        <a href="/login">Login Again</a>
    </div>
</body>
</html>