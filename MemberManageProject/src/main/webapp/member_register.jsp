<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<div class="container">
		<form action="register" method="post">
			아이디 : <input type="text" name="id"> <br>
			암호 : <input type="password" name="passwd"><br>
			이름 : <input type="text" name="name"><br>
			닉네임 : <input type="text" name="nick"><br>
			<select name="grade">
				<option value="1">Bronze</option>
				<option value="2">Silver</option>
				<option value="3">Gold</option>
				<option value="4">VIP</option>
				<option value="5">VVIP</option>
				<option value="6">Master</option>
			</select> 
			<br>
			<button>추가</button>
		</form>
	</div>
</body>
</html>