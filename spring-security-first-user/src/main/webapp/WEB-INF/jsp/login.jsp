<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>
<body>
	<h1>Custom Login Page</h1>
	
	<p style="color:red;">${SPRING_SECURITY_LAST_EXCEPTION.message}</p>

	<form action="j_spring_security_check" method="post">
		<label for="j_username">Username:</label>
		<input name="j_username" />
		<br />
		
		<label for="j_password">Password:</label>
		<input name="j_password" type="password" />
		<br />
		
		<input type="submit" value="Login" />
	</form>
	
</body>
</html>