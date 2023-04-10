<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>저장된 쿠키 목록</h2>
	<!-- 저장된 쿠키 목록 조회 -->
	<% 
		Cookie[] cookies = request.getCookies(); //배열로 꺼내짐
		
		for(int i=0;i<cookies.length;i++){
			%>
				<%=cookies[i].getName() %> / <%=cookies[i].getValue() %> <br> 
			<%
		}
	%>
	<hr>
	<script>
		//쿠키 확인
		document.write(document.cookie);
	</script>
	
</body>
</html>