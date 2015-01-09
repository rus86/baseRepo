<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<script type="text/javascript">
	//Ecvivalent $("document").ready()
	$(function() {
		
		console.log('Hello rus!');
		$("h1").css("color", "blue");
		
		//Click
		$("h1").click(function(event){
			alert(event);
		});
	});
</script>
</head>
<body>

	<h1>Test AJAX</h1>
	<br /> List of cities
	<select id="ajax-select"></select>
</body>
</html>