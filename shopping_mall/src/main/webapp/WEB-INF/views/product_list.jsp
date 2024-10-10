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
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/product.css?v=9">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<style>
.product-table {
    width: 100%;
    border-collapse: collapse;
    font-size: 14px;
    margin-bottom: 20px;
}

.product-table th, .product-table td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
}

.product-table th {
    background-color: #333;
    color: white;
    font-weight: bold;
    text-transform: uppercase;
}

.product-table td {
    background-color: #f9f9f9;
}

.product-table tr:nth-child(even) td {
    background-color: #f2f2f2;
}

.product-table tr:hover td {
    background-color: #ddd;
}

.manage-btn {
    padding: 5px 10px;
    background-color: #555;
    color: white;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
}

.manage-btn:hover {
    background-color: #333;
}

.p_button {
    padding: 10px 20px;
    background-color: #444;
    color: white;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
}

.p_button:hover {
    background-color: #666;
}

</style>
<body>
<input type="hidden" id="currentPage" value="${currentPage}">
<input type="hidden" id="totalPages" value="${totalPages}">
<div id="productList">
<%@ include file="../top_admin.jsp" %>
<main class="maincss">
<section>
<p>상품관리 페이지</p>
<div class="subpage_view">
    <span>등록된 상품 ${totalProducts }건</span>
	<form id="searchForm" onsubmit="return searchProducts(event);">
	    <span>
	        <select name="searchType" class="p_select1">
	            <option value="name">상품명</option>
	            <option value="code">카테고리</option>
	        </select>
	        <input type="text" name="searchKeyword" class="p_input1" placeholder="검색어를 입력해 주세요">
	        <input type="submit" value="검색" title="상품검색" class="p_submit">
	    </span>
	</form>
</div>

<!-- 테이블 구조 시작 -->
<div class="subpage_view2">
    <table class="product-table">
        <thead>
            <tr>
                <th><input type="checkbox" name="selectAll" onclick="selectAllItems(this)"></th>
                <th>코드</th>
                <th>이미지</th>
                <th>카테고리 분류</th>
                <th>상품명</th>
                <th>판매가격</th>
                <th>할인가격</th>
                <th>할인율</th>
                <th>재고현황</th>
                <th>판매유/무</th>
                <th>품절</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${productList }">
            <tr>
                <td><input type="checkbox" name="productCheckbox" value="${product.idx }"></td>
                <td>${product.productCode }</td>
                <td><a href="javascript:void(0);" onclick="openImagePopup('${product.mainImagePath }')">첨부파일</a></td>
                <td>${product.mainCategory }</td>
                <td>${product.productName }</td>
                <td><fmt:formatNumber value="${product.salePrice}" type="number" maxFractionDigits="0" />원</td>
                <td><fmt:formatNumber value="${product.discountedPrice}" type="number" maxFractionDigits="0" />원</td>
                <td>${product.discountRate }%</td>
                <c:choose>
                    <c:when test="${product.stockQuantity == 0}">
                        <td>품절</td>
                    </c:when>
                    <c:otherwise>
                        <td>${product.stockQuantity } EA</td>
                    </c:otherwise>
                </c:choose>
                <td>${product.saleStatus }</td>
                <td>${product.earlySoldOut }</td>
                <td><button onclick="editProduct(${product.idx })">관리</button></td>
            </tr>
            </c:forEach>
            <c:if test="${empty productList}">
                <tr>
                    <td colspan="12" style="text-align: center;">등록된 상품이 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>
<!-- 테이블 구조 끝 -->

<div class="subpage_view3">
<ul id="pagination" class="pageing">
    <li id="goFirstPage"><img src="/resources/ico/double_left.svg" onclick="goToPage(1)" /></li>
    <li id="leftArrow"><img src="/resources/ico/left.svg" onclick="goToPage(${currentPage - 1})" /></li>
	<div id="pageButtons"></div>
    <li id="rightArrow"><img src="/resources/ico/right.svg" onclick="goToPage(${currentPage + 1})" /></li>
    <li id="goLastPage"><img src="/resources/ico/double_right.svg" onclick="goToPage(${totalPages})" /></li>
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
<script src="/resources/js/admin/product_list.js?v=8"></script>
</html>
