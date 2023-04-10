<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관광지 갤러리</title>
 <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        .container{
            width: 1200px;
            margin: 30px auto;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
        .search_form{
            width: 500px;
            height: 500px;
            background-color: #f1f1f1;
            padding: 20px;
        }
        .form{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;

        }
        .search_form input[type="text"]{
            width: 80%;
            height: 40px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 0 10px;
            font-size: 16px;
        }
        .search_form input[type="button"]{
            width: 20%;
            height: 40px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 0 10px;
            font-size: 16px;
            background-color: #fff;
        }
        .search_form hr{
            margin: 20px 0;
        }
        .search_form .keyword_list{
            list-style: none;
            height: 400px;
            overflow-y: auto;
        }
        .search_form .keyword_list li{
            width: 100%;
            margin-bottom: 10px;
        }
        .search_form .keyword_list li a{
            width: 100%;
            padding:10px !important;
            text-align: center;
            display: inline-block;
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            color: #333;
            text-decoration: none;
        }
        .search_form .keyword_list li a:hover{
            background-color: #333;
            color: #fff;
        }
        .gallery_list{
            width: 700px;
            height: 500px;
            background-color: #f1f1f1;
            padding: 20px;
            margin-left: 20px;
            display: flex;
            flex-wrap: wrap;
            flex-direction: row;
            justify-content: center;            
            overflow-y: scroll;
        }
        .gallery_list::-webkit-scrollbar{
            display: none;
        }
        .gallery_list img{
            width: 180px;
            margin: 10px;
        }
    </style>

<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<script>
$(function() {
	$('.btn_search').click(function() {
			$.ajax({
	        url : 'keyword',
	        data : `search=\${$('input[name=search]').val()}`,
	        type : 'get',
	        dataType : 'json',
	        success:function(r){
	            console.log(r);
	            let tag = ``;
	            for(i=0;i<r.length;i++){
					tag += `<li><a>\${r[i]}</a></li>`;
				}
				$('.keyword_list').html(tag);
				
				$('.keyword_list a').click(function() {
					console.log($(this).text());
						$.ajax({
				        url : 'search',
				        data : 'search='+$(this).text(), 
				        type : 'get',
				        dataType : 'json',
				        success:function(result){
				            console.log(result);
				            let tag = ``;
				            for(i=0;i<result.length;i++){
								tag += `<img src='\${result[i]}'>`;
							}
				            tag = tag.length == 0 ? '<p>해당 이미지 없음</p>' : tag;
				            $('.gallery_list').html(tag);
							
				        	}
				    	});
				});
	        }
	    });
	});
	
	
});
</script>

</head>
<body>
    <div class="container">
        <div class="search_form">
            <div class="form">

                <input type="text" name="search" placeholder="검색어를 입력하세요">
                <input type="submit" value="검색" class="btn_search">
            </div>
                <hr>
                <ul class="keyword_list">
                <li>검색 결과 없습니다.<br>검색어를 입력하세요</li>
                </ul>
        </div>
        <div class="gallery_list">
             <p>해당 이미지 없음</p>
        </div>

    </div>
</body>
</html>
