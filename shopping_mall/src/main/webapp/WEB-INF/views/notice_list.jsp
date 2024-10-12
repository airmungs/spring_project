<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 리스트 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?<%= System.currentTimeMillis() %>">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?<%= System.currentTimeMillis() %>">
    <link rel="stylesheet" type="text/css" href="/resources/css/notice.css?<%= System.currentTimeMillis() %>">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
    <style>
        /* 공지사항 강조 스타일 */
        .highlight {
            background-color: #f9f9f9;
            font-weight: bold;
            color: #d9534f;
        }
        /* 페이지네이션 스타일 */
        .pageing li {
            cursor: pointer;
            padding: 5px;
        }
        .pageing li.active {
            font-weight: bold;
            color: #000;
        }
    </style>
</head>
<body>
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
<section>
    <p>공지사항 관리페이지</p>
    <div class="subpage_view">
    <ul>
        <li><input type="checkbox"></li>
        <li>NO</li>
        <li>제목</li>
        <li>글쓴이</li>
        <li>날짜</li>
        <li>조회</li>
    </ul>
    <c:forEach var="notice" items="${noticeList}">
        <ol class="${notice.notify == 1 ? 'highlight' : ''}">
            <li><input type="checkbox"></li>
            <li>${notice.idx}</li>
            <li>${notice.noticeTitle}</li>
            <li>${notice.noticeWriter}</li>
            <li>${notice.createdDate}</li>
            <li>${notice.viewCount}</li>
        </ol>
    </c:forEach>
    <c:if test="${empty noticeList}">
        <ol class="none_text">
            <li>등록된 공지 내용이 없습니다.</li>
        </ol>
    </c:if>
    </div>
    <div class="border_page">
        <ul class="pageing">
            <li onclick="location.href='notice_list?page=1'"><img src="/resources/ico/double_left.svg"></li>
            <li onclick="location.href='notice_list?page=${currentPage - 1 > 0 ? currentPage - 1 : 1}'"><img src="/resources/ico/left.svg"></li>
            <c:forEach begin="1" end="${totalPages}" var="i">
                <li class="${i == currentPage ? 'active' : ''}" onclick="location.href='notice_list?page=${i}'">${i}</li>
            </c:forEach>
            <li onclick="location.href='notice_list?page=${currentPage + 1 <= totalPages ? currentPage + 1 : totalPages}'"><img src="/resources/ico/right.svg"></li>
            <li onclick="location.href='notice_list?page=${totalPages}'"><img src="/resources/ico/double_right.svg"></li>
        </ul>
    </div>
    <div class="board_btn">
        <button class="border_del">선택삭제</button>
        <span style="float: right;">
        <button class="border_add" onclick="location.href='./notice_write'">공지등록</button>
        </span>
    </div>
</section>
</main>
<%@include file="../copyright_admin.jsp"%>
</body>
</html>
