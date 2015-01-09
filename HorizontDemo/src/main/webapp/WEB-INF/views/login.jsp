<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>
	<h1>Login form</h1>
	
	<c:url value="/login" var="loginUrl" />

	<form action="${loginUrl}" method="post">
		
		<c:if test="${not empty error}">
			<div class="error">Invalid login data</div>
		</c:if>

		<spring:message code="login.username" />
		<input id="j_username" name="j_username" size="20" maxlength="50"
			type="text" /> <br />

		<spring:message code="login.password" />
		<input id="j_username" name="j_username" size="20" maxlength="50"
			type="password" /> <br /> <input type="submit" value="Login">

		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>