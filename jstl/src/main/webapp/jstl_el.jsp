<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl / el</title>
</head>
<body>
	<h2>el 태그 테스트</h2>
	<p>request 영역에 저장된 내용은 requestScope를 사용해서 뽑음</p>
	<p>age : ${requestScope.age }</p>
	<p>session 영역에 저장된 내용은 sessionScope를 사용해서 뽑음</p>
	<p>msg : ${sessionScope.msg }</p> 	
	<p>session에 저장된 객체 dto를 뽑음</p>
	<p>${sessionScope.dto.id },${sessionScope.dto.passwd },${sessionScope.dto.name },
	${sessionScope.dto.nick },${sessionScope.dto.gradeNo }</p>
	<p>${sessionScope.dto.toString() }</p> <!-- 함수도 불러올수있음 -->
	<p>${sessionScope.dto.id == "A0001" }</p> 
	<h2>jstl 테스트</h2>
	<!-- set : 변수를 만들어서 데이터를 초기화 -->
	<p><c:set var="tnum" value="10000"/></p>
	<!-- out : 해당 데이터를 출력 -->
	<p><c:out value="${tnum }" />,${tnum }</p>
	<!-- if문 : test속성이 조건식(el로 표현), else, else if가 없음 -->
	<p>if문</p>
	<c:if test="${requestScope.age >= 20 }">
		<p>성인입니다.</p>
	</c:if>
	<c:if test="${requestScope.age < 20 }">
		<p>미성년자입니다.</p>
	</c:if>
	<p>여러개의 조건문이 필요할때</p>
	<c:choose>
		<c:when test="${requestScope.age >= 20 && requestScope.age < 30 }">
			<p>20대 입니다.</p>
		</c:when>
		<c:when test="${requestScope.age >= 30 && requestScope.age < 40 }">
			<p>30대 입니다.</p>
		</c:when>
		<c:otherwise>
			<p>40대 이상입니다.</p>
		</c:otherwise>
	</c:choose>
	<p>반복문 - 시작값, 종료값, 증감값을 속성값으로 설정해서 반복</p>
	<ul>
		<c:forEach begin="1" end="10" var="i">
			<li>${i } - 번째 생성</li>
		</c:forEach>
	</ul>
	<ul>
	<!-- step 속성은 증감값 -->
		<c:forEach begin="1" end="10" var="i" step="2">
			<li>${i } - 번째 생성</li>
		</c:forEach>
	</ul>
	<!-- 리스트나 배열같은 객체 있는 데이터를 전부 조회 -->
	<ul>
		<c:forEach items="${requestScope.list }" var="m" varStatus="s">
			<li>${m.toString() } - ${s.index }</li>
		</c:forEach>
	</ul>
	
</body>
</html>
	 <!-- el태그는 값을 읽어오는 용도, 여기다가 계산식도 쓸수있다. 값을 바꾸거나 저장하는건 아니고 결과값을 뿌리는 용도 
	prefix="c" 사용할 태그 c로 지정해둠
	단일태그는 뒤에 '/'를 넣어 마감한다. 
	:choose 는 여러개의 if문을 쓸때
	:when은 if문을 넣는곳
	:otherwise 는 else에 해당하는 것이다.
	test=""가 조건식 
	var=""는 지역변수
	-->