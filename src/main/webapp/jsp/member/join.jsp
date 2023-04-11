<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>
	
	<h1>회원가입</h1>
	<script type="text/javascript">
		var JoinForm_submitDone = false;	// 중복 제출 방지
		
		function JoinForm_submit(form) {
			if (JoinForm_submitDone) {
				alert('처리 중 입니다~');
				return;
			}
			
			var loginId = form.loginId.value.trim();
			var loginPw = form.loginPw.value.trim();
			var loginPwConfirm = form.loginPwConfirm.value.trim();
			var name = form.name.value.trim();
			
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
			
			if (loginPwConfirm.length == 0) {
				alert('비밀번호 확인을 입력해 주세요.');
				form.loginPwConfirm.focus();
				return;
			}
			
			if (loginPw != loginPwConfirm) {
				alert('비밀번호가 일치하지 않습니다!');
				form.loginPw.focus();
				return;
			}
			
			if (name.length == 0) {
				alert('이름을 입력해 주세요.');
				form.name.focus();
				return;
			}
			
			JoinForm_submitDone = true;
			// 폼 태그에 return false; 가 있기 때문에 폼 제출시켜 주기~
			form.submit();
			
		}
	</script>
	
	<form method="post" action="doJoin" onsubmit="JoinForm_submit(this); return false;"> <!-- this : 폼 자기 자신 -->
		<div>
			아이디 : <input autocomplete="off" type="text" name="loginId" placeholder="아이디를 입력해 주세요."/>
		</div>
		<div>
			비밀번호 : <input autocomplete="off" type="text" name="loginPw" placeholder="비밀번호를 입력해 주세요."/>
		</div>
		<div>
			비밀번호 확인 : <input autocomplete="off" type="text" name="loginPwConfirm" placeholder="비밀번호 확인을 입력해 주세요."/>
		</div>
		<div>
			이름 : <input autocomplete="off" type="text" name="name" placeholder="이름을 입력해 주세요."/>
		</div>
		<button type="submit">가입하기</button>
	</form>
</body>
</html>