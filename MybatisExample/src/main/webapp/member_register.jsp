<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.container{
		width:1200px;
		margin: 50px auto;	
		font-size: 0px;
	}
	input, select, button{
		width: 200px;
		height : 50px;
		font-size: 1.5rem;
		box-sizing: border-box;
	}
	button{
		display : inline-block;
		box-sizing: border-box;
		font-size: 1.5rem;	
		height : 50px;	
	}
</style>
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<div class="container">
	<form action="register" method="post">
		<input type="text" name="id" placeholder="아이디 입력"><br>		
		<input type="password" name="passwd" placeholder="암호 입력"><br>		
		<input type="text" name="name" placeholder="이름 입력"><br>		
		<input type="text" name="nick" placeholder="닉네임 입력"><br>
		<select name="grade">
			<option value="1">Bronze</option>
			<option value="2">Silver</option>
			<option value="3">Gold</option>
			<option value="4">VIP</option>
			<option value="5">VVIP</option>
			<option value="6">Master</option>
		</select><br><button>추가</button>		
	</form>
</div>
</body>
</html>


