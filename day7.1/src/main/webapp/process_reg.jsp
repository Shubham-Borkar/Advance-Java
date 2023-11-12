<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:setProperty property="*" name="user" />
<body>
	<h5>Voter Registration Status :
		${sessionScope.user.registerNewVoter()}</h5>
	<%--discard session upon registration --%>
	${pageContext.session.invalidate()}
	<h5>You have been logged out ....</h5>
	<h5>
		<a href="login.jsp">SignIn</a>
	</h5>
</body>
</html>