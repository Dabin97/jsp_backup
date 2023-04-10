<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
	table{
		border-collapse: collapse;
	}
	tr,td{
		border: 1px solid black;
	}
</style>
<body>
	<h2>구매한 로또 번호</h2>
	<table>
		<%
			ArrayList<HashSet<Integer>> list = (ArrayList<HashSet<Integer>>)request.getAttribute("lotto");
			
			for(int i = 0;i < list.size(); i++){
		%>
					<tr>
				<%
				Iterator<Integer> it = list.get(i).iterator();
				while(it.hasNext()){
				%>
					<td><%=it.next() %></td>
				<%
				}
				%>
					</tr>
				<%
			}
				%>
				
				<!-- <퍼센트% 의 안쪽은 doGet의 영역 --!>
				<!--따라서 위에서 doGet부분만 빼면 간단하게 <tr> <td><(%)=it.next() %></td> </tr> 이런 형식을 취하고있다.-->
	</table>	

</body>
</html>