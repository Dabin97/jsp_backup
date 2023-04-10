<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 결과</h2>
	<p><%=session.getAttribute("msg") %></p>
	<%
		String id = (String)session.getAttribute("id");
		if(id != null){
	%>
		<!-- 로그인 헀을 때에만 출력하는 부분 -->
		<p><%=id %>님 로그인 하셨습니다.</p> 
		<a href="logout">로그아웃</a>
	<%
		}else{
			//response.sendRedirect("06_login.jsp"); 이 방식은 클라이언트쪽에 자바가 깔려있어야한다는 전제가 있어야한다. 추천 X
			//한글 처리 필요
			//로그인 안했을때는 아래 2개중 하나만 처리
			//로그인 안했을때 출력하는 부분 - 1
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append("<script>alert('로그인 하셔야 이용할 수 있습니다.');location.href = 'login.jsp';</script>");
			%>
			<!-- 로그인 안했을때에만 출력하는 부분 - 2 -->
			<script>
				alert('로그인 하셔야 이용할 수 있습니다.');
				location.href = 'login.jsp';
			</script>
			<%
		}
	%>
</body>
</html>
	<!-- <퍼센트(%)를 중간에 끊어서 안에 내용을 넣을수있다. / <(%)(%)>안에는 doGet의 영역이다.-->
	<!-- String id = (String)session.getAttribute("id"); 은 object 형태로 저장되기 때문에 str로 형변환 필요-->
	<!-- 로그아웃을 누르면 세션이 초기화 되어서 설정해둔 메세지가 날아간다. -->