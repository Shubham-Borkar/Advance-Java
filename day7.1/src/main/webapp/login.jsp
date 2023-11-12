<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--centralized bean creation --%>
<%--session.getAttribute("user") : null : session.setAttribute
("user",new UserBean()) --%>
<jsp:useBean id="user" class="com.sunbeaminfo.beans.UserBean"
	scope="session" />
<%--session.getAttribute("candidate") : null : session.setAttribute
("candidate",new CandidateBean()) --%>
<jsp:useBean id="candidate" class="com.sunbeaminfo.beans.CandidateBean"
	scope="session" />
<body>
	<h5 align="center" style="color: red;">${sessionScope.user.message}</h5>
	<form action="validate.jsp" method="post">
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Enter User Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="password" name="password" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Login" /></td>
				<td><a href="register.jsp">SignUp</a></td>
			</tr>
		</table>
	</form>
</body>
</html>