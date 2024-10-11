<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 관리자 리스트</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?<%= System.currentTimeMillis() %>">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?<%= System.currentTimeMillis() %>">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
    
    <!-- 관리자가 없을 경우 -->
    <c:choose>
        <c:when test="${empty adminList}">
            <ol class="new_admin_none">
                <li>신규 등록된 관리자가 없습니다.</li>
            </ol>
        </c:when>
        <c:otherwise>
            <!-- 관리자가 있을 경우 -->
            <c:forEach var="admin" items="${adminList}">
                <ol class="new_admin_lists2">
                    <li>${admin.adidx}</li>
                    <li>${admin.adname}</li>
                    <li>${admin.adid}</li>
                    <li>${admin.adtel}</li>
                    <li>${admin.ademail}</li>
                    <li>${admin.adpart}</li>
                    <li>${admin.adposition}</li>
                    <li>
 					<fmt:formatDate value="${admin.addate}" pattern="yyyy-MM-dd"/>
					</li>
                    <li>
						<c:choose>
						    <c:when test="${admin.login == 'Y'}">
						        <input type="button" value="미승인" class="new_addbtn2" title="미승인" onclick="updateApprovalStatus('${admin.adid}', 'N', '관리자 승인을 해제하시겠습니까?')">
						    </c:when>
						    <c:otherwise>
						        <input type="button" value="승인" class="new_addbtn1" title="승인" onclick="updateApprovalStatus('${admin.adid}', 'Y', '관리자 승인을 진행하시겠습니까?')">
						    </c:otherwise>
						</c:choose>
                    </li>
                </ol>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</section>
<section></section>
<section></section>
</main>
<%@ include file="../copyright_admin.jsp" %>
</body>
<script src="/resources/js/admin/admin_list.js?<%= System.currentTimeMillis() %>"></script>
</html>
