<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>
	
	<h1>로그인</h1>
	<script type="text/javascript">
		var LoginForm_submitDone = false;	// 중복 제출 방지
		
		function LoginForm_submit(form) {
			if (LoginForm_submitDone) {
				alert('처리 중 입니다~');
				return;
			}
			
			var loginId = form.loginId.value.trim();
			var loginPw = form.loginPw.value.trim();
			
			if (loginId.length == 0) {
				alert('아이디를 입력해 주세요.');
				form.loginId.focus();
				return;
			}
			
			if (loginPw.length == 0) {
				alert('비밀번호를 입력해 주세요.');
				form.loginPw.focus();
				return;
			}
			
			LoginForm_submitDone = true;
			// 폼 태그에 return false; 가 있기 때문에 폼 제출시켜 주기~
			form.submit();
		}
	</script>
	
	<form method="post" action="doLogin" onsubmit="LoginForm_submit(this); return false;"> <!-- this : 폼 자기 자신 -->
		<div>
			아이디 : <input autocomplete="off" type="text" name="loginId" placeholder="아이디를 입력해 주세요."/>
		</div>
		<div>
			비밀번호 : <input autocomplete="off" type="text" name="loginPw" placeholder="비밀번호를 입력해 주세요."/>
		</div>
		<button type="submit">로그인</button>
	</form>
</body>
</html>