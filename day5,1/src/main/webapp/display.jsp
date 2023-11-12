<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!//JSP declaration block
	String mesg = "hello";//implicitly private : within page

	void test() {
		System.out.println("in testt");

	}

	//override jspInit
	public void jspInit() {
		System.out.println("in jspInit");
	}%>
<body>
	<h3 align="center">
		Session ID :
		<%=session.getId()%></h3>
	<%--display session id using EL syntax pageContext.getSession().getId()--%>
	<h3 align="center">Session ID via EL syntax using pageContext:
		${pageContext.session.id}</h3>
	<h3 align="center">Session ID via EL syntax using cookie:
		${cookie.JSESSIONID.value}</h3>
	<h4>Displaying user details using a scriptlet</h4>
	<%
	String email = request.getParameter("em");
	String pwd = request.getParameter("pass");
	out.print("Email : " + email + "<br/>");
	out.print("Password : " + pwd + "<br/>");
	%>
	<hr />
	<h4>Displaying user details using a JSP expression</h4>
	<h5>
		Email :
		<%=request.getParameter("em")%></h5>
	<h5>
		Password :
		<%=request.getParameter("pass")%></h5>
	<hr />
	<h4>Displaying user details using a EL Syntax</h4>
	<h5>Email : ${param.em}</h5>
	<h5>Password : ${param.pass}</h5>
	<h5>param : ${param}</h5>
	<h5>JSP variable : <%= mesg %></h5>
</body>
<%!
//override jspDestroy
	public void jspDestroy() {
		System.out.println("in jspDestroy");
	}
%>
</html>