<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 회원관리</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?v=2">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
    <script src="/resources/js/admin/shop_member_list.js?v=3"></script>
</head>
<body>
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
<section>
    <p>회원 리스트</p>
    <ol class="new_admin_title">
        <li>NO</li>
        <li>고객명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>이메일 수신</li>
        <li>SMS 수신</li>
        <li>가입일자</li>
        <li>상태</li>
        <li>정지여부</li>
    </ol>

    <c:choose>
        <c:when test="${not empty clientList}">
            <c:forEach var="client" items="${clientList}" varStatus="status">
                <ol class="new_admin_lists">
                    <li>${status.index + 1}</li> 
                    <li>${client.name}</li> 
                    <li>${client.userId}</li> 
                    <li>${client.phone}</li>
                    <li>${client.email}</li>
                    <li><c:out value="${client.eventMail ? 'Y' : 'N'}"/></li> 
                    <li><c:out value="${client.eventSms ? 'Y' : 'N'}"/></li> 
                    <li>
                        <fmt:formatDate value="${client.createdAt}" pattern="yyyy-MM-dd"/>
                    </li>
                    <li>
                        <c:choose>
                            <c:when test="${client.login == 'Y'}">정상</c:when>
                            <c:otherwise>휴면</c:otherwise>
                        </c:choose>
                    </li>
                    <li>
                        <input type="button" value="정지" class="new_addbtn1 <c:if test='${client.login == "N"}'>disabled-btn</c:if>" title="정지" onclick="updateLoginStatus('${client.userId}', 'N')" <c:if test="${client.login == 'N'}">disabled</c:if>>
						<input type="button" value="해제" class="new_addbtn2 <c:if test='${client.login == "Y"}'>disabled-btn</c:if>" title="해제" onclick="updateLoginStatus('${client.userId}', 'Y')" <c:if test="${client.login == 'Y'}">disabled</c:if>>
                    </li>
                </ol>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <ol class="new_admin_none">
                <li>가입된 회원이 없습니다.</li>
            </ol>
        </c:otherwise>
    </c:choose>
</section>

<section style="width: 1100px; height: auto; margin: 0 auto; margin-top: 30px;">
    <p style="font-size: 15px; font-weight: bolder; margin-bottom: 10px;">■ 이용 약관</p>
    <textarea placeholder="이용약관에 대한 내용을 입력하세요" style="width: 100%; height: 100px; resize: none;" name="useAgree">${useAgree }</textarea>
    <input type="button" value="이용약관 수정" title="이용약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;" onclick="useAgree()">
</section>

<section style="width: 1100px; height: auto; margin: 0 auto; margin-top: 30px;">
    <p style="font-size: 15px; font-weight: bolder; margin-bottom: 10px;">■ 개인정보 수집 및 이용</p>
    <textarea placeholder="개인정보 수집 및 이용" style="width: 100%; height: 100px; resize: none;" name="infoAgree">${infoAgree }</textarea>
    <input type="button" value="개인정보 약관 수정" title="개인정보 약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;" onclick="infoAgree()">
</section>
</main>
<%@include file="../copyright_admin.jsp"%>
</body>
</html>
