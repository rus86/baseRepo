<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new user</title>
</head>
<body>
	<form:form modelAttribute="user" method="POST" action="/admin/userSave">
	<table>
		<tr>
			<td>
			First name: <form:input path="username"/>
			</td>
		</tr>
		<tr>
			<td>
			Password: <form:input path="password"/>
			</td>
		</tr> 
		<tr>
			<td>
			<input type="submit" value="Save"/>
			</td>
		</tr>
	</table>
	
	
	</form:form>
</body>
</html>