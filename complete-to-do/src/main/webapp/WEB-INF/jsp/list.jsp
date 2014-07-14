<%@ taglib tagdir="/WEB-INF/tags" prefix="template" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<template:base>
	<h2>To Do List</h2>
	
	<a href="create">Create</a>

	<c:choose>
		<c:when test="${not empty toDos}">
			<table>
				<thead>
					<tr>
						<th>Action</th>
						<th>Assigned To</th>
						<th>Created</th>
						<th>Created By</th>
						<th>Updated</th>
						<th>Updated By</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${toDos}" var="toDo">
						<tr>
							<td><a href="${toDo.id}">${toDo.action}</a></td>
							<td>${toDo.assignedTo.username}</td>
							<td><fmt:formatDate value="${toDo.created.time}" pattern="yyyy-MM-dd" /></td>
							<td>${toDo.createdBy.username}</td>
							<td><fmt:formatDate value="${toDo.updated.time}" pattern="yyyy-MM-dd" /></td>
							<td>${toDo.updatedBy.username}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		
		<c:otherwise>
			<p>Nothing To Do!</p>
		</c:otherwise>
	</c:choose>
</template:base>