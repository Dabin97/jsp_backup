<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>file form</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var count = 3;
    $('#plus').click(function(){
    	if(count==5) return;
       	count++;
       	$('form').append(`<p><input type="file" name="file\${count}"></p>`);
     });  
    $('#minus').click(function(){
   	 	if(count==1) return;
    		$('form').children("p").last().remove();
       	count--;
     }); 
 });
</script>
</head>
<body>
	<!-- 파일 길이를 모르니까 무제한으로 보낼수있는 post로만 한다. -->
	<form action="upload.do" method="post" enctype="multipart/form-data">
		<p><input type="text" name="writer" placeholder="작성자"></p>
		<p><button>업로드</button></p>
		<p><input type="file" name="file1">
			<button type="button" id="plus">+</button>
			<button type="button" id="minus">-</button>
		</p>		
		<p><input type="file" name="file2"></p>
		<p><input type="file" name="file3"></p>
	</form>
</body>
</html>