<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 align="center">
		Session ID :
		<%=session.getId()%></h3>
	<h4>
		Welcome 2 JSP @
		<%=LocalDateTime.now()%></h4>
	<h5>
		<a href="comments.jsp">Test Comments</a>
	</h5>
	<h5>
		<a href="login.jsp">Test Scripting Elements</a>
	</h5>
	<h5>
		<a href="login2.jsp">User Login</a>
	</h5>
	<h5>
		<a href="test1.jsp">Centralized Error Handling in JSP</a>
	</h5>
	<h5>
		<a href="test2.jsp">include directive in JSP</a>
	</h5>
	<h5>
		<a href="test4.jsp?pid=100&name=bread&price=50">JSP forward action</a>
	</h5>
</body>
</html>