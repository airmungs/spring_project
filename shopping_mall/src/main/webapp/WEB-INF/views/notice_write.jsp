<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?<%= System.currentTimeMillis() %>">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?<%= System.currentTimeMillis() %>">
    <link rel="stylesheet" type="text/css" href="/resources/css/notice.css?<%= System.currentTimeMillis() %>">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
	<script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
</head>
<body>
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
<section>
    <p>공지사항 등록페이지</p>
<div class="write_view">
<ul>
    <li>공지사항 여부</li>
    <li>
        <label class="label_notice"><em class="cbox"><input type="checkbox" name="notify"></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
    </li>
</ul>
<ul>
    <li>공지사항 제목</li>
    <li>
        <input type="text" class="notice_input1" name="noticeTitle"> ※ 최대 150자까지 입력이 가능
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
        <input type="text" class="notice_input2" value="<%=admin.getAdname() %>" name="noticeWriter" readonly> ※ 관리자 이름이 노출 됩니다.       
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <textarea class="notice_input3" id="editor" placeholder="공지내용을 입력하세요!" name="noticeText"></textarea>
    </li>
</ul>
</div>
<div class="board_btn">
    <button class="border_del">공지목록</button>
    <button class="border_add">공지등록</button>
</div>
</section>
</main>
<%@include file="../copyright_admin.jsp"%>
</body>
<script>
CKEDITOR.replace('editor', {
    removePlugins: 'uploadimage,uploadfile,filebrowser'
});

// CKEditor 데이터 반영
document.querySelector('form').addEventListener('submit', function(event) {
    // CKEditor 데이터를 textarea에 반영
    for (var instance in CKEDITOR.instances) {
        CKEDITOR.instances[instance].updateElement();
    }
});
</script>
</html>
