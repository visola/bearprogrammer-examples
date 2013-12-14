<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>People</title>
</head>
<body>
	<a href="edit.do">New Person</a>
	<br />
	<ul>
		<c:forEach items="${personList}" var="person">
			<li>
				<a href="edit.do?personId=${person.id}">
					${person.firstName} ${person.lastName}
				</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>