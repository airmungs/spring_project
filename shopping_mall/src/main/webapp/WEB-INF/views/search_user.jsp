<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 찾기</title>
</head>
<body>
<form id="frm">
<p>아이디 찾기</p>
고객명 : <input type="text" name="mname"><br>
이메일 : <input type="text" name="memail"><br>
<input type="button" value="아이디 찾기" onclick="btn1()">
<p>패스워드 찾기</p>
아이디 : <input type="text" name="mid"><br>
고객명 : <input type="text" name="mname"><br>
<input type="button" value="패스워드 변경" onclick="btn2()">
</form>
</body>
<script>
function btn1(){
	frm.method="POST";
	frm.action="./search_mid.do";
	frm.submit();
}
function btn2(){
	frm.method="POST";
	frm.action="./modify_mpass.do";
	frm.submit();	
}
</script>
</html>