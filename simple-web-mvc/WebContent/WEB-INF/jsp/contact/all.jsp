<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All Contacts</title>
<link href="${pageContext.servletContext.contextPath}/css/all.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/WEB-INF/include/menu.jsp" />

	<h1>All Contacts</h1>
	
	<c:if test="${contacts == null}">
		<p>No contacts in the database.</p>
	</c:if>
	
	<c:if test="${contacts != null}">
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>E-mail</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contacts}" var="contact">
					<tr>
						<td><a href="edit.html?id=${contact.id}">${contact.name}</a></td>
						<td>${contact.email}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>