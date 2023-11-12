<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--import spring supplied JSP tag lib --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>All Departments :</h4>
	<spring:url var="abc" value="/emps/list"/>
	<form action="${abc}" method="get">
	<h1>url value : ${abc}</h1>
		<table style="background-color: lightgrey; margin: auto">
			<caption>Choose Department Form</caption>
			<tr>
				<td>Select Department</td>
				<td><select name="deptId">
						<c:forEach var="dept" items="${requestScope.dept_list}">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Choose Department" /></td>
			</tr>
		</table>
	</form>
</body>
</html>