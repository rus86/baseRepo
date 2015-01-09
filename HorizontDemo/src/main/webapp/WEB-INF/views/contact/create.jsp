<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add contact</title>
<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}
</style>
</head>
<body>
	<h1>Add contact</h1>	
	<form:form action="/contact/create" method="POST"
		modelAttribute="contact">
		<form:errors path="age" cssClass="error"/>
		<br/>
		<spring:message code="contact.firstName"/> <form:input path="firstName" />
		<form:errors path="firstName" cssClass="error"/>
		<br/>
		<spring:message code="contact.lastName"/> <form:input path="lastName" />
		<form:errors path="lastName" cssClass="error"/>
		<br/>
		<spring:message code="contact.age"/> <form:input path="age" />
		<br/>
		<spring:message code="contact.driverLicense"/> <form:input path="driverLicense" />
		<br/>
		<input type="submit" value="Create contact">
	</form:form>
</body>
</html>