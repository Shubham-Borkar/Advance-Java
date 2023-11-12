<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--print category name --%>
	<h4 align="center">${param.category}</h4>
	<%--print product details  --%>
	<h5>Product Details : ${requestScope.product_dtls}</h5>
	<h5>param : ${param}</h5>
</body>
</html>