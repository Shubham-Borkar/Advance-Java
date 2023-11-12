<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!String mesg = "some mesg !";//instance var%>
<body>
	<%
	String anotherMesg = "some other mesg !";//local var
	pageContext.setAttribute("attr1", 12345);//attr1 : page scoped attr
	%>
	<%@ include file="test3.jsp"%>
</body>
</html>