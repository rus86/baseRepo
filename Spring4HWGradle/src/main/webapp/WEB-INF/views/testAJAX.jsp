<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<script type="text/javascript">
	//Ecvivalent $("document").ready()
	$(function() {

		console.log('Hello rus!');
		$("h1").css("color", "blue");

		//Click
		$("h1").click(function(event) {
			alert(event);
		});

		$('#cityList').click(function() {
			console.log('Initiate call');
			var countryList = $('#countryItems > li');
			countryList.each(function(index) {
				console.log('Index=' + index + ' item=' + this.innerHTML)			
			})
			var data = {isTest:true};
			var parameter = 'test';
			//$.getJSON('/api/contact/list/', data, success);
			$.getJSON('/api/contact/list/' + parameter, success);
		})

		function success(resultData) {
			console.log('Result data');
			
			$.each(resultData, function() {
				console.log('Element=' + this.name);
				$('#countryItems').after(this.name + '<br/>');
			});
			
			

		}

	});

	//Create my own plugin
	(function($) {
		$.fn.displayCityList = function() {

			console.log('Display city list')

		};
	})(jQuery);
</script>
</head>
<body>

	<h1>Test AJAX</h1>
	<br />
	<h2 id='cityList'>Display city list</h2>
	<ul id='countryItems'>
		<li>Ukraine</li>
		<li>Russia</li>
	</ul>
</body>
</html>