<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿으로 데이터 전송</title>
</head>
<body>
	<h2>로그인 폼</h2>
	<form action="login" method="get">
		아이디 : <input type="text" name="id"><br>
		암호 : <input type="password" name="passwd"><br>
		<button>로그인</button> 
	</form>
</body>
</html>
