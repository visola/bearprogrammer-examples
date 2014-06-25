<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<htmL>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
</head>
<body>
	<form:form action="${pageContext.servletContext.contextPath}/logout">
		<input type="submit" value="Logout" />
	</form:form>
	
	<jsp:doBody />
</body>
</htmL>