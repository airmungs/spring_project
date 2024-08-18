<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Shop Bag</title>
    <meta charset="utf-8" />
    <link href="/resources/client/index.css" rel="stylesheet" />
    <link href="/resources/client/subpage.css" rel="stylesheet" />
    <link href="/resources/client/agree.css?v=1" rel="stylesheet" />
    <link href="/resources/client/join.css?v=1" rel="stylesheet" />
    <script src="/resources/js/client/join.js?v=1"></script>
</head>
<body>
    <div class="navbar">
        <a href="#" id="logo">
            <img src="/resources/client/images/logo.jpg" width="149">
        </a>
        <ul id="menu">
            <li><a href="./agree.do">LOGIN</a></li>
            <li><a href="#">MEMBER SHIP</a></li>
            <li><a href="#">CART</a></li>
            <li><a href="#">CUSTOMER CENTER</a></li>
        </ul>
    </div>
    <main>
        <div class="products">
            <h3>MEMBER_JOIN</h3>
            <div class="sub_view">
                <div class="joinview">
                    <form id="joinForm">
                        <h3>회원가입</h3>
                        <span class="join_info">
                            <ol>
                                <li>기본정보 </li>
                                <li>※ 표시는 반드시 입력하셔야 하는 항목 입니다.</li>
                            </ol>
                        </span>
                        <ul class="join_ul">
                            <li>※ 아이디</li>
                            <li>
                                <input type="text" name="userId" class="join_in1"> 
                                <input type="button" id="idCheck" value="중복확인" class="join_btn1">
                            </li>
                            <li>※ 비밀번호</li>
                            <li>
                                <input type="password" name="password" class="join_in1 join_in2"> 
                                ※ 최소 6자 이상 입력하셔야 합니다.
                            </li>
                            <li>※ 비밀번호 확인</li>
                            <li>
                                <input type="password" name="passwordConfirm" class="join_in1 join_in2"> 
                                ※ 동일한 패스워드를 입력하세요.
                            </li>
                            <li>※ 이름</li>
                            <li>
                                <input type="text" name="name" class="join_in1">
                            </li>
                            <li>※ 이메일</li>
                            <li>
                                <input type="text" name="email" class="join_in1"> 
                                <input type="button" value="이메일 인증" class="join_btn1"> 
                                ※ 입력하신 이메일을 확인해 주세요.
                            </li>
                            <li>※ 인증번호</li>
                            <li>
                                <input type="text" name="authCode" class="join_in1 join_in3" maxlength="8"> 
                                ※ 8자리 인증번호를 입력하세요.
                            </li>
                            <li>※ 전화번호</li>
                            <li>
                                <input type="text" name="phone" class="join_in1 join_in2" maxlength="11"> 
                                ※ 숫자만 입력하세요
                            </li>
                            <li>※ 이벤트 메일 수신</li>
                            <li>
                                <label><input type="checkbox" name="eventMail" class="join_ck1"> 
                                정보/이벤트 메일 수신에 동의합니다.</label>
                            </li>
                            <li>※ 이벤트 SMS 수신</li>
                            <li>
                                <label><input type="checkbox" name="eventSms" class="join_ck1"> 
                                정보/이벤트 SMS 수신에 동의합니다.</label>
                            </li>
                        </ul>
                        <div class="btn_center_box">
                            <input type="button" id="clientJoin" class="btn_join" value="회원가입">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <%@include file="./copyright_client.jsp" %>
</body>
</html>