<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css?<%= System.currentTimeMillis() %>">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?<%= System.currentTimeMillis() %>">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?<%= System.currentTimeMillis() %>">
    <link rel="stylesheet" type="text/css" href="/resources/css/notice.css?<%= System.currentTimeMillis() %>">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
	<script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
</head>
<style>
.cke_notifications_area{
	    visibility: hidden;
}
/* 공통 스타일 */
.write_view {
    width: 100%;
    margin-bottom: 20px;
}

.write_view ul {
    display: flex;
    align-items: center;
    margin-bottom: 15px;
    padding: 0;
    list-style-type: none;
}

.write_view ul li:first-child {
    width: 200px; /* 제목 영역의 넓이 */
    font-weight: bold;
    text-align: right;
    padding-right: 20px;
    box-sizing: border-box;
}

.write_view ul li:last-child {
    flex: 1;
    text-align: left;
}

.write_view input[type="text"],
.write_view textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid rgb(81, 61, 61);
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 14px;
}

/* 공지사항 내용 스타일 */
.notice_input1 {
    width: 600px; /* 제목 입력란의 크기 */
    height: 35px;
    border: 1px solid rgb(81, 61, 61);
    padding-left: 10px;
}

.notice_input2 {
    width: 200px; /* 글쓴이 입력란의 크기 */
    height: 35px;
    border: 1px solid rgb(81, 61, 61);
    padding-left: 10px;
}

.notice_input3 {
    width: 100%;
    height: 400px;
    padding: 10px;
    border: 1px solid rgb(81, 61, 61);
    resize: none;
    box-sizing: border-box;
}

.label_notice {
    font-size: 14px;
    margin-left: 5px;
    color: #333;
    cursor: pointer;
}
.cbox {
    vertical-align: middle;
}
.board_btn {
    width: 100%; /* 버튼이 담길 공간을 꽉 채우도록 */
    display: flex;
    justify-content: center; /* 버튼들을 가운데 정렬 */
    gap: 20px; /* 버튼들 사이 간격 */
    margin-top: 20px; /* 상단 여백 추가 */
}

.board_btn button {
    font-size: 14px;
    width: 120px;
    height: 40px;
    border: none;
    cursor: pointer;
    border-radius: 5px; /* 버튼 테두리 둥글게 */
    transition: background-color 0.3s ease; /* 마우스 오버 시 부드러운 색 변화 */
}

.board_btn button[type="submit"] {
	background-color: rgb(27, 25, 25);
    color: white;
}
.board_btn button[type="button"] {
    background-color: rgb(81, 61, 61);
    color: white;
}

</style>
<body>
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
<section>
    <p>공지사항 등록페이지</p>
<form action="insertNotice" method="post">
<input type="hidden" name="notify" value="false">
<div class="write_view">
    <ul>
        <li>공지사항 여부</li>
        <li>
            <label class="label_notice">
                <em class="cbox"><input type="checkbox" name="notify" value="true"></em> 공지 출력
            </label> 
        </li>
    </ul>
    <ul>
        <li>공지사항 제목</li>
        <li>
            <input type="text" class="notice_input1" name="noticeTitle">
        </li>
    </ul>
    <ul>
        <li>글쓴이</li>
        <li>
            <input type="text" class="notice_input2" value="<%=admin.getAdname() %>" name="noticeWriter" readonly>
        </li>
    </ul>
    <ul class="ul_height">
        <li>공지내용</li>
        <li>
            <textarea class="notice_input3" id="editor" placeholder="공지내용을 입력하세요!" name="noticeText"></textarea>
    <div class="board_btn">
        <button type="button" onclick="location.href='notice_list'">공지목록</button>
        <button type="submit">공지등록</button>
    </div>
        </li>
    </ul>
    </div>
</form>
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
