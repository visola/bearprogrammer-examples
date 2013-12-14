<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${contact == null ? 'Add' : 'Edit'} Contact</title>
<link href="${pageContext.servletContext.contextPath}/css/all.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/include/menu.jsp" />

	<h1>${contact == null ? 'Add' : 'Edit'} Contact</h1>
	
	<form method="post" action="save.html">
		<input type="hidden" name="id" value="${contact.id}" />
		
		<label for="name">Name:</label>
		<input name="name" value="${contact.name}" />
		<br />
		
		<label for="email">E-mail:</label>
		<input name="email" value="${contact.email}" />
		<br />
	
		<input type="submit" value="Save" />
	</form>
	
</body>
</html>