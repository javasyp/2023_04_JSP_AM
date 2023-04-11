<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 메인 서블릿에서 저장한 세션 속성값 가져오기 (getAttribute)
boolean isLogined = (boolean) request.getAttribute("isLogined");			// 로그인 상태
int loginedMemberId = (int) request.getAttribute("loginedMemberId");		// 회원 번호
String loginedMemberName = (String) request.getAttribute("loginedMemberName");		// 회원 이름
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h1>MAIN</h1>
	
	<%
	if (isLogined) {
	%>
	<div>
		현재 <%=loginedMemberId%>번 회원 "<%=loginedMemberName %>"님이 로그인 중입니다.
		▶ <a href="../member/doLogout">로그아웃</a>
	</div>
	<%
	} else {
	%>
	<div>
		▶ <a href="../member/login">로그인</a>
	</div>
	<%
	}
	%>
	
	<ul>
		<li><a href="../article/list">게시물 리스트</a></li>
		<li><a href="../member/join">회원가입</a></li>
	</ul>
</body>
</html>