<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 상품관리</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/product.css?v=5">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<body>
<div id="productList">
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
<section>
<p>상품관리 페이지</p>
<div class="subpage_view">
    <span>등록된 상품 ${product_list.size() }건</span>
	<form id="searchForm" onsubmit="return searchProducts(event);">
	    <span>
	        <select name="searchType" class="p_select1">
	            <option value="name">상품명</option>
	            <option value="code">상품코드</option>
	        </select>
	        <input type="text" name="searchKeyword" class="p_input1" placeholder="검색어를 입력해 주세요">
	        <input type="submit" value="검색" title="상품검색" class="p_submit">
	    </span>
	</form>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox" name="selectAll" onclick="selectAllItems(this)"></li>
        <li>코드</li>
        <li>이미지</li>
        <li>카테고리 분류</li>
        <li>상품명</li>
        <li>판매가격</li>
        <li>할인가격</li>
        <li>할인율</li>
        <li>재고현황</li>
        <li>판매유/무</li>
        <li>품절</li>
        <li>관리</li>
    </ul>
    <c:forEach var="product" items="${product_list }">
    <ul>
        <li><input type="checkbox" name="productCheckbox" value="${product.idx }"></li>
        <li>${product.productCode }</li>
        <li><a href="javascript:void(0);" onclick="openImagePopup('${product.mainImagePath }')">첨부파일</a></li>
        <li>${product.mainCategory }</li>
        <li>${product.productName }</li>
		<li><fmt:formatNumber value="${product.salePrice}" type="number" maxFractionDigits="0" />원</li>
		<li><fmt:formatNumber value="${product.discountedPrice}" type="number" maxFractionDigits="0" />원</li>
        <li>${product.discountRate }%</li>
        <c:choose>
            <c:when test="${product.stockQuantity == 0}">
                <li>품절</li>
            </c:when>
            <c:otherwise>
                <li>${product.stockQuantity } EA</li>
            </c:otherwise>
        </c:choose>
        <li>${product.saleStatus }</li>
        <li>${product.earlySoldOut }</li>
        <li><button onclick="editProduct(${product.idx })">관리</button></li>
    </ul>
    </c:forEach>
    <c:if test="${empty product_list}">
        <ul>
            <li style="width: 100%;">등록된 상품이 없습니다.</li>
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
    <input type="button" value="선택상품 삭제" title="선택한 상품을 삭제합니다" class="p_button" onclick="deleteSelectedItems()">
    <span style="float: right;">
    <input type="button" value="신규상품 등록" title="신규상품 등록 페이지로 이동합니다" class="p_button p_button_color1" onclick="location.href='product_write.do'">
    <input type="button" value="카테고리 목록" title="카테고리 관리 페이지로 이동합니다" class="p_button p_button_color2" onclick="location.href='cate_list.do'">
    </span>
</div>
</section>
</main>
<%@include file="../copyright_admin.jsp"%>
</div>
</body>
<script src="/resources/js/admin/product_list.js?v=4"></script>
</html>
