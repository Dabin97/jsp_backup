<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	section{
		width: 1200px;
		margin:0 auto;
	}
	section table{
		width: 90%;
		border-collapse: collapse;
	}
	section table td,section table th{
		border:1px solid black;
		text-align: center;
		padding:10px 0px;
	}
	section tfoot{
		font-size: 0px;
	}
	section tfoot a:link,section tfoot a:visited{
		display: inline-block;
		padding:0px 10px;
		text-decoration: none;
		color:black;
		font-size: 16px !important;
	}
	
	.current_page{
		display: inline-block;
		padding:0px 10px;
		font-size: 16px !important;
		color:red;
		font-weight: bold;
	}
	tfoot > tr > td{
		position: relative;
	}
	.btn_write{
		display: inline-block;
		padding:0px 20px !important;
		position: absolute;
		right:0px;
	}
</style>
</head>
<body>
		<jsp:include page="template/header.jsp"></jsp:include>
		<section>
			<table>
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>직네임</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>
						<th>싫어요</th>
					</tr>
				</thead>
				<tbody>
				<!-- 게시글 목록  -->
					<c:forEach var="board" items="${requestScope.list }">
						<tr>
							<td>${board.bno }</td>
							<td><a href="boardView.do?bno=${board.bno }">${board.title }</a></td>
							<td>${board.writer }</td>
							<td>${board.bdate }</td>
							<td>${board.bcount }</td>
							<td>${board.blike }</td>
							<td>${board.bhate }</td>

						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<!-- 이전 페이지 그룹으로 이동 -->
							<c:if test="${pagging.priviousPageGroup }">
									<a href="main.do?pageNo=${pagging.startPageOfPageGroup - 1}">◀</a>
								</c:if>
								
							<!-- 페이징 결과 출력 
								PaggingVO에 있는 getStartPageOfPageGroup -> startPageOfPageGroup로 변경
												getEndPageOfPageGroup
								이용해서 jstl의 forEach를 이용해서 게시판 페이지 번호를 출력
								-->
							<c:forEach var="i" begin="${pagging.startPageOfPageGroup }" end="${pagging.endPageOfPageGroup }">
							 	<c:choose>
							 		<c:when test="${pagging.currentPageNo == i }">
									 	<span class="current_page">${i }</span>							 		
							 		</c:when>
								 	<c:otherwise>
										<a href="main.do?pageNo=${i }">${i }</a>
								 	</c:otherwise>
							 	</c:choose>
								
							 </c:forEach>

								<!-- 다음 페이지 그룹으로 이동 : >>를 클릭시 다음 그룹의 첫번째 페이지로 이동-->
								<c:if test="${pagging.nextPageGroup }">
									<a href="main.do?pageNo=${pagging.endPageOfPageGroup + 1}">▶</a>
								</c:if>

							
							<a href="boardWriteView.do" class="btn_write">글쓰기</a>
						</td>
					</tr>
				</tfoot>
			</table>		
		</section>
</body>
</html>



