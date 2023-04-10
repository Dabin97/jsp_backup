<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>forward 이동 결과</h2>
	<p>msg : <%=request.getAttribute("msg") %> </p>
	<a href="forward_servlet_2">두번째 이동 테스트</a><br>
	<a href="01_page_move.jsp">이동 테스트 페이지</a>
</body>
</html>
