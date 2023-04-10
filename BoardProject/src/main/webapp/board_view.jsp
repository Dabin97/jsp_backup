<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btn_delete').click(function(){
			location.href = 'boardDelete.do?bno=${board.bno}';
		});
		$('#btn_update').click(function(){
			location.href = 'boardUpdateView.do?bno=${board.bno}';
		});
		$('#btn_like').click(function(){
			$.ajax({
				url: 'boardContentLike.do',
				data: 'bno=${board.bno }',
				type: 'post',
				dataType: 'json',
				success:function(r){
					console.log(r);
					alert(r.msg);
					$('#like_count').text(r.blike);
				}
			});
		});
		
		$('#btn_hate').click(function(){
			$.ajax({
				url: 'boardContentHate.do',
				data: 'bno=${board.bno }',
				type: 'post',
				dataType: 'json',
				success:function(r){
					console.log(r);
					alert(r.msg);
					$('#hate_count').text(r.bhate);
				}
			});
		});
		
		$('.btn_comment_like,.btn_comment_hate').click(function(){
			let class_name = $(this).attr('class');
			let cno = $(this).parent().parent().find('input[name=cno]').val();
			//alert(class_name + " - " + cno);
			$.ajax({
				url : 'commentLikeHate.do',
				data : `cno=\${cno}&mode=\${class_name}`,
				type:'post',
				dataType:'json',
				success:function(r){
					//경고창 띄움 - Controller에서 보낸 메세지
					alert(r.msg);
					//현재 페이지 새로고침
					location.reload();
				}
			});
		});
	});


</script>
<style type="text/css">
	#container{
		/* background-color: lime; */
		width:1200px;
		margin:0 auto;
	}
	#container table{
		width:600px;
		border-collapse: collapse;
		margin:0 auto;
	}
	#container th{
		width:150px !important;
		text-align: right;
		padding:5px;
	}
	#container td{
		width: 500px;
		padding:5px;
		height: 40px;
	}
	#container input{
		width: 100%;
		height:40px;
		box-sizing:border-box;
		border-width : 1px;
		border-radius: 5px;
	}
	h2{
		text-align: center;
	}
	.btn{
		text-decoration: none;
		background-color: #e8e8e8;
		min-width: 80px;
		display: inline-block;
		padding:5px 10px;
		font-weight:normal;
		border : 1px solid #585858;
		text-align:center;
		color : black;
		font-size:14px;
		box-sizing: border-box;
	}
	.btn:hover{
		background-color: #282828;
		color:#FFFFFF
	}
	.hate{
		transform: rotate(0.5turn);
	}
	.text_center{
		text-align: center;
	}
	.btn_like{
		width:64px;
		display: inline-block;
		color:black;
		text-decoration: none;
	}
	.comment_form{
		width:100%;
		border :1px solid black;
	}
	.writer{
		width: 100%;
		display: inline-block;
		font-weight: bold;
		padding-left: 30px;
	}
	.comment_form textarea{
		width:100%;
		height: 80px;
		margin-top:10px;
		font-size: 18px;
		padding-left: 30px;
		padding-right: 30px;
		outline:none;
		border: none;
		resize: none;
		box-sizing: border-box;

	}
	.comment_form button{
		width: 100px;
		height: 40px;
		font-weight: bold;
		background-color: #ffff00;
		outline:none;
		border: none;
	}
	.comment_form button:hover{
		background-color: #dfdf25;
	}
	.length{
		text-align: right;
		padding:10px 30px;
	}
	hr{
	margin:0;
	}
	.comment_form p {
		margin:0;
	}
</style>
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<div id="container">
  <h2>글조회 페이지</h2>
	<table>
		<tr>
			<th>글번호</th>
			<td>${board.bno }</td>
		</tr>
		<tr>
			<th>제목 : </th>
			<td>${board.title}</td>
		</tr>
		<tr>
			<th>작성자 : </th>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<th>조회수 : </th>
			<td>${board.bcount }</td>
		</tr>
		<tr>
			<td colspan="2"> 
				${board.content }
			</td>
		</tr>
		<!-- 첨부파일 목록 출력 -->
		<tr>
			<td colspan="2">
				<h3>첨부파일 목록</h3>
				<c:forEach var="file" items="${fList }">
					<p><a href="fileDown.do?bno=${file.bno }&fno=${file.fno}">${file.fileName }</a></p>
					<c:if test="${file.type == 'image' }">
						<p><img src="fileDown.do?bno=${file.bno }&fno=${file.fno}" style="width:500px;"></p>
					</c:if>
					<!-- ${file.bno } - ${file.fno } - ${file.path }<br> -->
				</c:forEach>
			</td>
		</tr>
		<!-- 좋아요 싫어요는 따로 테이블을 각각 만들어서 처리 -->
		<tr>
			<td colspan="2"> 
				<a href="#" id="btn_like">좋아요 : <span id="like_count">${board.blike }</span> </a>
				<a href="#" id="btn_hate">싫어요 : <span id="hate_count">${board.bhate }</span> </a>
			</td>
		</tr>
		<!-- 로그인한 사용자와 글쓴이가 같을때만 수정 삭제 버튼이 나오게끔 처리  -->
		<c:if test="${sessionScope.dto.id == board.writer }">
		<tr>
			<td colspan="2"> 
				<button id="btn_update">수정</button>
				<button id="btn_delete">삭제</button>
			</td>
		</tr>		
		</c:if>
		<tr>
        <td colspan="2">
         <div class="comment_form">
			<form action="commentWrite.do" method="post"> <!-- 댓글은 폼으로 할것 -->
				<input type="hidden" name="bno" value="${board.bno }">
				<textarea name="comment" placeholder="댓글을 입력하세요"></textarea> <!-- 작성자는 세션으로 넣을것임 -->
				<button>댓글작성</button>
		</form>
		</div>
        </td>
        </tr>
	</table>
	<hr>
	<!-- 
		댓글 내용을 출력, 댓글 번호, 작성일, 좋아요, 싫어요, 작성자, 댓글 내용
		댓글 삭제 버튼
	 -->
<c:forEach var="comment" items="${list }">
			<div class="comment">
				<p>
					<input type="hidden" name="cno" value="${comment.cno }">
					<span>${comment.writer }</span>
					<span>작성일 : ${comment.cdate }</span>
					<span><a href="#" class="btn_comment_like">좋아요 : 
										<span>${comment.clike }</span></a></span>
					<span><a href="#" class="btn_comment_hate">싫어요 : 
										<span>${comment.chate }</span></a></span>
				</p>
				<p>${comment.content }</p>
				<c:if test="${comment.writer == sessionScope.dto.id }">
					<a href="boardCommentDelete.do?cno=${comment.cno }&bno=${board.bno}">댓글 삭제</a>
				</c:if>
			</div>
		</c:forEach>

	</div>
</body>
</html>

