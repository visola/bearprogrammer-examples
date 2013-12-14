<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Person</title>
	<style>
		#errors {
			color: red;
		}
	</style>
</head>
<body>
	<c:if test="${errors != null}">
		<ul id="errors">
			<c:forEach items="${errors}" var="error">
				<li>${error.field}: ${error.defaultMessage}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<form action="save.do" method="post">
		<input type="hidden" name="id" value="${person.id}" />
		
		<label for="firstName">First name:</label>
		<input name="firstName" value="${person.firstName}" />
		<br />
		
		<label for="lastName">Last name:</label>
		<input name="lastName" value="${person.lastName}" />
		<br />
		
		<label for="birthday">Birthday:</label>
		<input name="birthday" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${person.birthday}"/>" />
		<br />
		
		<input type="submit" />
	</form>
</body>
</html>