<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>${sessionScope.user.message}</h5>
	<%-- hello , admin name --%>
	<!-- sess.getAtr("user").getUserDet().getFName() -->
	<h4>Hello , ${sessionScope.user.userDetails.firstName}</h4>
	
	<%-- list of top 2 candidates --%>
	<h3>Top 2 Candidates</h3>
	<h4>${sessionScope.candidate.topCandidates}</h4>
	
</body>
</html>