<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%
	//임의로 다른 경로 접속 차단 alert
    String error = request.getParameter("error");
    if ("access_denied".equals(error)) {
%>
    <script>
        alert('접근권한 없음');
        window.location.href = "<%= request.getContextPath() %>/";
    </script>
<%
    }
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/mainlogin.css?<%= System.currentTimeMillis() %>">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
    <style>
        /* 고객 페이지로 이동 버튼 스타일 */
        .customer-button {
            display: block;
            background-color: #f5deb3; /* 베이지색 */
            color: #333;
            border: none;
            border-radius: 20px;
            padding: 15px 30px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            margin: 20px auto;
            width: 200px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 80px;
        }

        .customer-button:hover {
            background-color: #e3cba1; /* 마우스 오버 시 약간 진한 베이지색 */
        }

        .footer {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
    </style>
</head>
<body class="bodycss">
<form id="adminlogin_form">
    <header class="admin_title">
        <p><img src="/resources/img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
    </header>
    <section class="admin_bgcolor">
        <div class="admin_login">
            <span>
                <div class="left_div">
	                <ul>
	                <li><input type="text" class="input_text1" name="adid" placeholder="관리자 아이디를 입력하세요"></li>
	                <li><input type="password" class="input_text1" name="adpass" placeholder="관리자 패스워드를 입력하세요"></li>
	                </ul>
                </div>
                <div class="right_div">
                    <button type="submit" class="btn_submit" title="MASTER LOGIN" id="admin_login">MASTER LOGIN</button>
                </div>
                <br><br><br><br>
                <em class="alert_msg">※ 본 사이트는 관리자 외에는 접근을 금합니다.</em>
            </span>
            <span>
                <ol class="admin_info">
                    <li title="신규 관리자 등록요청"><a href="./add_master.do">신규 관리자 등록요청</a></li>
                    <!--<li title="아이디/패스워드 찾기">아이디/패스워드 찾기</li> -->
                </ol>                
            </span>
        </div>

        <!-- 고객 페이지로 이동 버튼 추가 (로그인 섹션 바로 아래) -->
        <button type="button" class="customer-button" onclick="location.href='sailmall/main'">고객 페이지로 이동</button>

    </section>
    <footer class="footer admin_copy_login">
        <div>
            Copyright ⓒ 2024 shopbag All rights reserved.
        </div>
    </footer>
</form>
</body>
<script src="/resources/js/admin/login_admin.js?<%= System.currentTimeMillis() %>"></script>

</html>
