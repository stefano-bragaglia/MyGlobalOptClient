<%@ page language="java" import="java.util.*" session="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="description"
	content="Energy Scenario Evaluation for ePolicy project">
<meta name="author" content="Stefano Bragaglia">
<title>Test page</title>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
	function go() {
		alert("Going!");
		$.post("/Desperate/servlet", {
			number : $("#number").val(),
		}, function(result) {
		}, "html");
	}
</script>
</head>

<%!String timesS, timesR, number = null;%>
<%
	// timesS = (String) session.getAttribute("timesS");
	timesR = (String) request.getAttribute("timesR");
	number = (String) request.getAttribute("number");
%>

<body>
	<p>
		Times S:
		<%=timesS%></p>
	<p>
		Times R:
		<%=timesR%></p>
	<p>
		Number:
		<%=number%></p>
	<form method="post">
		<input id="number" value="str" />
		<button id="button" type="button" onclick="go()">GO!</button>
	</form>
</body>

</html>