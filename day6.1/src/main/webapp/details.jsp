<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>User Details : ${sessionScope.user_dtls}</h4>
	<%
	 String url=response.encodeURL("logout.jsp");
	%>
	<h5>
		<a href="<%= url%>">Log Me Out</a>
	</h5>
</body>
</html>