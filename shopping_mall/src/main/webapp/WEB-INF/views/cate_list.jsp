<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
    <section>    
<p>카테고리관리 페이지</p>
<div class="subpage_view">
    <span>등록된 카테고리 0건</span>
    <form>
    	<span>
        	<select class="p_select1">
            	<option>카테고리명</option>
           	 	<option>카테고리코드</option>
        	</select>
        	<input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        	<input type="submit" value="검색" title="카테고리 검색" class="p_submit">
    	</span>
	</form>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox"></li>
        <li>분류코드</li>
        <li>대메뉴 코드</li>
        <li>대메뉴명</li>
        <li>소메뉴 코드(사용안함)</li>
        <li>소메뉴명(사용안함)</li>
        <li>사용 유/무</li>
        <li>관리</li>
    </ul>
    <ul>
        <li><input type="checkbox"></li>
        <li style="text-align: left; text-indent: 5px;">분류코드</li>
        <li>대메뉴 코드</li>
        <li style="text-align: left; text-indent: 5px;">대메뉴명</li>
        <li>-</li>
        <li style="text-align: left; text-indent: 5px;">-</li>
        <li>Y</li>
        <li>[수정]</li>
    </ul>
    <ul>
        <li style="width: 100%;">등록된 카테고리가 없습니다.</li>
    </ul>
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="/resources/ico/double_left.svg"></li>
        <li><img src="/resources/ico/left.svg"></li>
        <li>1</li>
        <li><img src="/resources/ico/right.svg"></li>
        <li><img src="/resources/ico/double_right.svg"></li>
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
</body>
</html>