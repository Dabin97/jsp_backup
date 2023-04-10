<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등급 관리 페이지</title>
<style>
	h2{
		width:1200px;
		margin:0 auto;
		text-align: center;;
	}
	.container{
		width:1200px;
		margin : 0 auto;
		display:flex;
	}
	.manage_container, .register_container{
		padding:20px;
	}
</style>
<script src="${pageContext.request.contextPath }/js/jquery-3.6.3.js"></script><!-- 프로젝트가 여러개면 이렇게 경로를 잡아준다. -->
<script>
function grade_search(search = ''){
	console.log(search);
	$.ajax({
		url : 'grade/search', 
		data : 'val='+search,
		type : 'post',
		dataType:'json',
		success: function(r){
			/* console.log(r); */
			let tag = '';
			for(i=0;i<r.length;i++){
				tag += '<p>';
				tag += `<input type='text' class='grade_no' value='\${r[i].GRADE_NO}'>`;  /*들어오는 value의 값을 대문자로 변경해야 적용된다.*/
				tag += `<input type='text' class='grade_name' value='\${r[i].GRADE_NAME}'>`;
				tag += `<button class='btn_update'>수정</button>`;
				tag += `<button class='btn_delete'>삭제</button>`;
				tag += '</p>';
			}
			$('.content').html(tag);
			$('.btn_update').click(grade_update);
			$('.btn_delete').click(grade_delete);
		}
	});
	}
	
	function grade_update(){
		let grade_no = $(this).siblings('.grade_no').attr('value');
		let grade_name = $(this).siblings('.grade_name').val();
		let param = `grade_no=\${grade_no}&grade_name=\${grade_name}`;
		
		$.ajax({
			url: 'grade/update',
			data:param,
			type:'post',
			dataType:'json',
			success:function(r){
				if(r.code == '1'){
					console.log('데이터 수정 완료');
				}else{
					console.log('데이터 수정 실패');
				}
				grade_search();
			}
		});
		
	}
	
	function grade_delete() {
		let grade_no = $(this).prevAll('.grade_no').val();//클릭한 버튼의 등급번호를 저장
		let obj = $(this);
		console.log(grade_no);
		$.ajax({
			url : 'grade/delete/'+grade_no, //데이터가 한개만 보내면 될 경우
			type:'post',
			dataType:'json',
			success:function(r){
				console.log(r.message);
				if(r.code == '1'){
					//삭제 성공 - 해당 라인 삭제
					$(obj).parent().remove();
					grade_search();
				}else{
					alert('삭제 실패, 데이터를 확인하세요');
				}
			}
		});
	}
	
	$(function() {
		$('.btn_update').click(grade_update);
		$('.btn_delete').click(grade_delete);
		$('#search').keyup(function(){
			//검색어를 받아옴
			let val = $(this).val();
			grade_search(val);//검색하는 함수 실행
		});
		$('#register_grade > button').click(function(){
			let param =$('#register_grade').serialize(); 
			console.log(param);
			$.ajax({
				url : 'grade/append',
				data : param,
				type : 'post',
				dataType : 'json',
				success : function(r){
					if(r.code == '1'){
						console.log('등급 추가 완료');
						grade_search();
					}else{
						console.log('등급 추가 실패');
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<h2>회원 등급 관리 페이지</h2>
	<div class="container">
		<div class="manage_container">
			<h3>등급 관리</h3>
			<hr>
			<p>
				<input type="text" id="search" placeholder="검색할 등급명 일부">
				<button type="button">검색</button>
			</p>
			<div class="content">
				<c:forEach var="item" items="${requestScope.list }">
					<p>
						<input type='text' class='grade_no' value='${item['GRADE_NO'] }' readonly>
						<input type='text' class='grade_name' value='${item['GRADE_NAME']  }'>
						<button class='btn_update'>수정</button>
						<button class='btn_delete'>삭제</button>
					</p>
				</c:forEach>
			</div>
		</div>
		<div class="register_container">	
			<h3>등급 추가 폼</h3>
			<hr>
			<form id="register_grade">
				<input type="text" name="grade_no" placeholder="등급번호">
				<input type="text" name="grade_name" placeholder="등급명">
				<button type="button">등급 추가</button>
			</form>
		</div>
	</div>	

</body>
</html>






