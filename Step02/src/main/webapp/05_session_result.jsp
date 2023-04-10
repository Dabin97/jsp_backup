<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Session 결과 페이지</h2>
	<p>이름 : <%=session.getAttribute("name") %></p>
	<p>나이 : <%=session.getAttribute("age") %></p>
</body>
</html>