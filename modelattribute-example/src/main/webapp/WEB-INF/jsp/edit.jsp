<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${person.id == null ? 'New' : 'Edit' } Person</title>
</head>
<body>
	<form action="save.do" method="post">
		<input type="hidden" name="personId" value="${person.id}" />
		
		<label for="firstName">First name:</label>
		<input name="firstName" value="${person.firstName}" />
		<br />
		
		<label for="lastName">Last name:</label>
		<input name="lastName" value="${person.lastName}" />
		<br />
		
		<c:if test="${person.created != null}">
			Created: 
			<fmt:formatDate value="${person.created.time}" pattern="yyyy-MM-dd HH:mm:ss.SSS" />
			<br />
		</c:if>
		
		<input type="submit" value="Save" />
	</form>

</body>
</html>