<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int a = 100;
	int b = 0;
	int result = a / b;//a, b , result => method local vars, within _jspService
	%>
	<h5>Result : <%= result %></h5>
</body>
</html>