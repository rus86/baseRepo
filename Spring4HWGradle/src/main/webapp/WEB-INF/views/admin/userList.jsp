<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User list</title>
</head>
<body>
User list
<br/>
<display:table name="userList" export="true" pagesize="25" id="users">
	<display:column property="username" paramId="username">User name</display:column>
	<display:column property="password" >User password</display:column>
	<display:column property="enabled" >Active</display:column>
</display:table>
<!-- 
<table>
	<thead>
		<tr>
			<th>
				User name
			</th>
			<th>
				User password
			</th>
			<th>
				Active
			</th>
			<th>
				Edit
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="userItem" items="${userList}">
			<tr>
				<td>
					<c:out value="${userItem.username}"/>
				</td>
				<td>
					<c:out value="${userItem.password}"/>
				</td>
				<td>
					<c:out value="${userItem.enabled}"/>
				</td>
				<td>
					Link
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
 -->
 
 <a href="/admin/userCallAdd">Add new user</a>
 
</body>
</html>