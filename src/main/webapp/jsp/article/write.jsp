<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>
	
	<h1>게시물 작성</h1>
	
	<form method="post" action="doWrite">
		<div>
			제목 : <input autocomplete="off" type="text" name="title" placeholder="제목을 입력해 주세요"/>
		</div>
		<div>
			내용 : <textarea type="text" name="body" placeholder="내용을 입력해 주세요"/></textarea>
		</div>
		<button type="submit">글쓰기</button>
	</form>
</body>
</html>