<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%--import JSTL core tag lib --%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:setProperty property="*" name="user" />
<body>
<!-- client pull , response.sr(resp.encodeRedirectURL(sess.getAtt("")) -->
	<c:redirect url="${sessionScope.user.validateUser()}.jsp" />
</body>
</html>