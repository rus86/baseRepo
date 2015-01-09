<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact list</title>
</head>
<body>
	<h1>Contact list</h1>
	<br />

	<table>
		<thead>
			<tr>
				<th>Contact id</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Age</th>
				<th>Driver license</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="contactItem" items="${contactList}">
				<tr>
					<td><c:out value="${contactItem.id}" /></td>
					<td><c:out value="${contactItem.firstName}" /></td>
					<td><c:out value="${contactItem.lastName}" /></td>
					<td><c:out value="${contactItem.age}" /></td>
					<td><c:out value="${contactItem.driverLicense}" /></td>
					<td><a href="/contact/callUpdate/${contactItem.id}">Edit</a></td>
					<td><a href="/contact/delete/${contactItem.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="/contact/callCreate">Add new contact</a>

</body>
</html>