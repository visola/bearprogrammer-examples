<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Everybody</title>
	<style>
		td {
			min-width: 150px;
			text-align: center;
		}
	</style>
</head>
<body>
	<h2>Everybody</h2>
	
	<p>
		<a href="edit.do">New Person</a>
	</p>
	
	<c:if test="${fn:length(people) == 0}">
		<p>No one in your database.</p>
	</c:if>
	
	<c:if test="${fn:length(people) > 0}">
		<table>
			<thead><tr><th>Name</th><th>Birthday</th></tr></thead>
			<tbody>
				<c:forEach items="${people}" var="entry">
					<tr>
						<td>
							<a href="edit.do?personId=${entry.key}">${entry.value.firstName} ${entry.value.lastName}</a>
						</td>
						<td><fmt:formatDate value="${entry.value.birthday}" />
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>