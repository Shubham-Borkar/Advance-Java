<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table style="background-color: lightgrey; margin: auto">
		<caption>Emp List from Dept ID : ${param.deptId}</caption>
		<tr>
			<th>Emp ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Join Date</th>
			<th>Salary</th>
			<th>Update</th>
			<th>Delete</th>
			<th>Show Address</th>
		</tr>
		<c:forEach var="emp" items="${requestScope.emp_list}">
			<tr>
				<td>${emp.id}</td>
				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td>${emp.joinDate}</td>
				<td>${emp.salary}</td>
				<spring:url var="url" value="/emps/update?empId=${emp.id}"></spring:url>
				<td><a href="${url}">Update</a></td>
				<spring:url var="url" value="/emps/delete?empId=${emp.id}"></spring:url>
				<td><a href="${url}">Delete</a></td>
				<spring:url var="url" value="/emps/show_adr?empId=${emp.id}"></spring:url>
				<td><a href="${url}">Show Address</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>