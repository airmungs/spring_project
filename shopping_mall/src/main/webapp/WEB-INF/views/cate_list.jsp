<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/category.css?v=6">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<body>
<div id="cateList">
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
<section>    
<p>카테고리관리 페이지</p>
<div class="subpage_view">
    <span>등록된 카테고리 ${cate_list.size()}건</span>
	<form id="searchForm" onsubmit="return searchCategories(event);">
	    <span>
	        <select name="searchType" class="p_select1">
	            <option value="name">대메뉴명</option>
	            <option value="code">대메뉴코드</option>
	        </select>
	        <input type="text" name="searchKeyword" class="p_input1" placeholder="검색어를 입력해 주세요">
	        <input type="submit" value="검색" title="상품검색" class="p_submit">
	    </span>
	</form>    
</div>
<div class="subpage_view2">
	<ul>
	    <li><input type="checkbox" id="selectAll" onclick="selectAllItems(this)"></li>
	    <li>분류코드</li>
	    <li>대메뉴 코드</li>
	    <li>대메뉴명</li>
	    <li>소메뉴 코드(사용안함)</li>
	    <li>소메뉴명(사용안함)</li>
	    <li>사용 유/무</li>
	    <li>관리</li>
	</ul>
	<c:forEach var="cate_list" items="${cate_list}">
	    <ul>
	        <li><input type="checkbox" class="cateCheckbox" onchange="updateSelectAllCheckbox()"></li>
	        <li style="text-align: left; text-indent: 5px;">${cate_list.category_code}</li>
	        <li>${cate_list.lg_menu_code}</li>
	        <li style="text-align: left; text-indent: 5px;">${cate_list.cate_name}</li>
	        <li>-</li>
	        <li style="text-align: left; text-indent: 5px;">-</li>
	        <li>${cate_list.use_yn}</li>
	        <li><a href="cate_edit.do?idx=${cate_list.idx}">[수정]</a></li>
	    </ul>
	</c:forEach>
    <c:if test="${empty cate_list}">
        <ul>
            <li style="width: 100%;">등록된 카테고리가 없습니다.</li>
        </ul>
    </c:if>
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="/resources/ico/double_left.svg" onclick="goToPage(1)"></li>
        <li><img src="/resources/ico/left.svg" onclick="goToPage(currentPage - 1)"></li>
        <li>1</li>
        <li><img src="/resources/ico/right.svg" onclick="goToPage(currentPage + 1)"></li>
        <li><img src="/resources/ico/double_right.svg" onclick="goToPage(totalPages)"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="카테고리 삭제" title="선택한 카테고리를 삭제합니다" class="p_button">
    <span style="float: right;">
    <input type="button" value="상품 리스트" title="상품 관리 페이지로 이동합니다" class="p_button p_button_color1" onclick="location.href='product_list.do'">
    <input type="button" value="카테고리 등록" title="카테고리 등록 페이지로 이동합니다" class="p_button p_button_color2" onclick="location.href='cate_write.do'">
    </span>
</div>
</section>
</main>
<%@include file="../copyright_admin.jsp"%>
</div>
</body>
<script src="/resources/js/admin/cate_list.js?v=5"></script>
</html>
