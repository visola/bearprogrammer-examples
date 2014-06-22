<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<htmL>
<body>
	<h2>To Do List</h2>

	<c:choose>
		<c:when test="${not empty toDos}">
			<table>
				<thead>
					<tr>
						<th>Action</th>
						<th>Created</th>
						<th>Updated</th>
						<th>Assigned To</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${toDos}" var="toDo">
						<tr>
							<td>${toDo.action}</td>
							<td><fmt:formatDate value="${toDo.created.time}" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatDate value="${toDo.updated.time}" pattern="yyyy-MM-dd" /></td>
							<td>${toDo.assignedTo}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		
		<c:otherwise>
			<p>Nothing To Do!</p>
		</c:otherwise>
	</c:choose>

</body>
</htmL>