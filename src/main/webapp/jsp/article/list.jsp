<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
@SuppressWarnings("unchecked")
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<%@ include file ="../part/topbar.jspf"%>
	
	<h1>게시물 리스트</h1>
	
	<table style="border-collapse: collapse; border-color: green; width: 1000px;" border="1px"">
		<tr>
			<th>번호</th>
			<th>작성날짜</th>
			<th>제목</th>
			<th>작성자</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<tr style="text-align: center;">
			<td><%=articleRow.get("id")%></td>
			<td><%=articleRow.get("regDate")%></td>
			<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
			<td><%=articleRow.get("name")%></td>
			<td><a href="modify?id=<%=articleRow.get("id")%>">수정</a></td>
			<td><a href="doDelete?id=<%=articleRow.get("id")%>">삭제</a></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<style type="text/css">
	.page > a {
		color: black;
		text-decoration: none;
	}
	.page > a.red {
		color: red;
	}
	</style>
	
	<div class="page">
		<%
		// 페이지 노출 개수 제한
		if (cPage > 1) {
		%>
			<a href="list?page=1">◀</a>
		<%
		}
		int pageSize = 5;	// 양 옆으로 몇개씩 보여줄 것인지
		int from = cPage - pageSize;
		
		if (from < 1) {
			from = 1;
		}
		
		int end = cPage + pageSize;
		
		if (end > totalPage) {
			end = totalPage;
		}
		
		for (int i = from; i <= end; i++) {
		%>
		<a class="<%=cPage == i ? "red" : ""%>" href="list?page=<%=i%>"><%=i%></a>
		<%
		}
		if (cPage < totalPage) {
		%>
			<a href="list?page=<%=totalPage%>">▶</a>
		<%
		}
		%>
	</div>
</body>
</html>