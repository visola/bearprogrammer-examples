<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create First User</title>
</head>
<body>
	<h1>Welcome!</h1>
	
	<h2>Create first user</h2>
	
	<form action="save.do" method="post">
		<label for="username">Username:</label>
		<input name="username" />
		<br />
		
		<label for="password">Password:</label>
		<input name="password" type="password" />
		<br />
		
		<input type="submit" value="Create" />
	</form>
</body>
</html>