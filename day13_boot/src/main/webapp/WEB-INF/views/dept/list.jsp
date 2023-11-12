<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>All Departments :</h4>
	<form action="emps/list" method="get">
		<table style="background-color: lightgrey; margin: auto">
			<caption>Choose Department Form</caption>
			<tr>
				<td>Select Department</td>
				<td><select name="dept_id">
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