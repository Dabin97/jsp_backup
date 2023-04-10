<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로또 구매 페이지</title>
</head>
<body>
	<h2>로또 구매 페이지</h2>
	<form action='lotto' method="get">
		구매 개수 : <input type="number" name="num" min="1" max="10"><br>
		<button>전송</button>
	</form>
</body>
</html>
<!-- 
lotto_sell_page.jsp
	자동으로 발급 받을 로또 번호 세트를 폼으로 입력받아서
	LottoServlet으로 전송, 경로 lotto로 설정

LottoServlet
	전송받은 번호를 기준으로 로또번호를 생성해서
	session이나 request 영역에 저장한 후 lotto_result.jsp로 이동
	
lotto_result.jsp
	저장된 로또 번호 목록을 table로 출력
 -->