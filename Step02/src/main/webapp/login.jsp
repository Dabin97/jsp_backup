<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 페이지</h2>
	<form action='login' method="post">
		아이디 : <input type="text" name="id" placeholder="아이디 입력"><br>
		비밀번호 : <input type="password" name="passwd" placeholder="암호 입력"><br>
		<button>로그인</button>
	</form>
</body>
</html>
<!-- 아이디와 비밀번호 입력받는 폼, 전송할 서블릿은 LoginServlet, url 경로: login -->
<!-- LoginServlet.java/ 전송받은 아이디와 비밀번호가 admin / 123456이면 세션에 로그인 성공 메세지와 아이디를 저장
	 로그인 실패시 실패 메세지를 저장, 로그인 처리 후 이동할 페이지 login_result.jsp로 이동 -->
<!-- login_result.jsp / 저장한 메세지만 출력 -->