<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main page</title>
</head>
<body>
Application main page! ${rusParam}
<c:out value="${rusParam}"></c:out>
<c:if test="false">Hi RuS!</c:if>

</body>
</html>