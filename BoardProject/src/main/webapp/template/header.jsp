<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
    	nav{
		width: 1200px;
		margin:0 auto;
	}
	nav > ul{
		display: flex;
		flex-direction: row;
		justify-content: center;
		list-style-type: none;
	}
	nav > ul > li{
		width: 200px;
	}
    </style>
   	<c:if test="${sessionScope.dto == null}">
    	<script>
    		location.href="${pageContext.request.contextPath}/index.jsp";
    	</script>
	</c:if>

	<!-- 회원등급이 0인지 확인을 한 후에 0이면 '권한이 없습니다' 경고창을 띄운후 이전페이지로 이동   -->
	
	<!-- 회원등급이 6이면 관리자이므로 홈 게시판 회원관리 메뉴를 출력   -->
	<c:if test="${sessionScope.dto.gradeNo == 6 }">
		<nav>
			<ul>
				<li><a href="main.do">홈</a></li>
				<li><a href="#">게시판 관리</a></li>
				<li><a href="#">회원관리</a></li>
				<li><a href="logout.do">로그아웃</a></li>
			</ul>
		</nav>

	</c:if>