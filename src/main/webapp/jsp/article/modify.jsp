<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>
	
	<h1><%=articleRow.get("id")%>번 게시물 수정</h1>
	
	<form method="post" action="doModify">
		<div>
			번호 :
			<input type="hidden" name="id" value="<%=articleRow.get("id")%>"/>
			<%=articleRow.get("id")%></div>
			
		<div>
			날짜 :
			<%=articleRow.get("regDate")%></div>
		<div>
			제목 : <input autocomplete="off" value="<%=articleRow.get("title")%>" type="text" name="title" placeholder="제목을 입력해 주세요"/>
		</div>
		<div>
			내용 : <textarea type="text" name="body" placeholder="내용을 입력해 주세요"/><%=articleRow.get("body")%></textarea>
		</div>
		<button type="submit">수정하기</button>
		
		<div>
			<a href="list" style="color: green">리스트로 돌아가기</a>
		</div>
	</form>
</body>
</html>