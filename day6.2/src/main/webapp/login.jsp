<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--centralized bean creation --%>
<jsp:useBean id="user" class="com.sunbeaminfo.beans.UserBean"
	scope="session" />
<jsp:useBean id="candidate" class="com.sunbeaminfo.beans.CandidateBean"
	scope="session" />
<body>

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
			</tr>
			 <tr>
          <td><a href="signin.jsp">New User? Register Here</a></td>
        </tr>
		</table>
	</form>
</body>
</html>