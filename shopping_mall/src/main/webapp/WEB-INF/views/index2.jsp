<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Practice</title>
</head>
<body>
<form id="frm">
아이디 : <input type="text" name="mid">
<input type="button" value="login" id="btn">
</form>
</body>
<script>
document.getElementById("btn").addEventListener("click",function(){
	frm.method="post";
	frm.action="./loginok.do";
	frm.submit();
});
</script>
</html>