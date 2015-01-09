<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add contact</title>
</head>
<body>
	<h1>Add contact</h1>
	<form:form action="/contact/update" method="POST"
		modelAttribute="contact">
		
		<form:hidden path="id"/>

		First name <form:input path="firstName" />
		<br/>
		Last name <form:input path="lastName" />
		<br/>
		Age <form:input path="age" />
		<br/>
		Driver license <form:input path="driverLicense" />
		<br/>
		<input type="submit" value="Update contact">
	</form:form>
</body>
</html>