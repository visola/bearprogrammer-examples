<%@ taglib tagdir="/WEB-INF/tags" prefix="template" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<template:base>
	<h2>To Do</h2>
	
	<form:form action="${pageContext.servletContext.contextPath}/" modelAttribute="toDo">
		<form:hidden path="id" />
	
		<label>Action</label>
		<form:input path="action" />
		<form:errors path="action" />
		<br />
		
		<label>Assigned To</label>
		<form:select path="assignedTo.username">
			<form:option value="">--- Select One ---</form:option>
			<form:options items="${usernames }" />
		</form:select>
		<form:errors path="assignedTo" />
		<br />
		
		<input type="submit" value="Save" />
		<a href="${pageContext.servletContext.contextPath}/">Cancel</a>
	</form:form>
</template:base>