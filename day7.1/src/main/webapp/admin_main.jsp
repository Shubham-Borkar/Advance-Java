<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--import JSTL coe tag lib --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5 align="center" style="color: green;">${sessionScope.user.message}</h5>

	<%-- hello , admin name --%>
	<%--session.getAttribute("user").getUserDetails().getFirstName() : sent to clnt --%>
	<h4>Hello , ${sessionScope.user.userDetails.firstName}</h4>

	<%-- list of top 2 candidates --%>

	<table style="background-color: lightgrey; margin: auto">
		<caption>Top 2 Candidates</caption>
		<tr>
			<th>Candidate ID</th>
			<th>Name</th>
			<th>Party Name</th>
			<th>Votes</th>
		</tr>
		<c:forEach var="c" items="${sessionScope.candidate.topCandidates}">
			<tr>
				<td>${c.candidateId}</td>
				<td>${c.name}</td>
				<td>${c.partyName}</td>
				<td>${c.votes}</td>
			</tr>
		</c:forEach>
	</table>
	<table style="background-color: lightgrey; margin: auto">
		<caption>Party wise Votes Analysis</caption>
		<tr>
			<th>Party Name</th>
			<th>Votes</th>
		</tr>
		<c:forEach var="entry" items="${sessionScope.candidate.partyVotes}">
			<tr>
				<td>${entry.key}</td>
				<td>${entry.value}</td>
			</tr>
		</c:forEach>
	</table>
	<%--clean up daos --%>
	${sessionScope.candidate.candidateDao.cleanUp()}
	${sessionScope.user.userDao.cleanUp()}
	<%--invalidate session --%>
	${pageContext.session.invalidate()}
	<h5>You have logged out....</h5>
	<a href="login.jsp">Visit Again</a>

</body>
</html>