<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="/resources/css/product.css?v=6">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="../top_admin.jsp" %>
<form id="submitProductForm">
<main class="maincss">
<section>
<p>상품 등록 페이지</p>
<div class="product_insert">
    <ul>
        <li>대메뉴 카테고리</li>
        <li>
            <select name="mainCategory" id="mainCategory" class="product_input1">
            	<c:forEach var="code" items="${codes }">
                <option value="${code.lg_menu_code }">${code.lg_menu_code } (${code.cate_name })</option>
                </c:forEach>
                <c:if test="${empty codes }">
                <option value="">카테고리를 등록해주세요</option>
                </c:if>
            </select>
            <input type="button" value="카테고리 관리" title="카테고리 관리 페이지로 이동합니다" class="product_btn" onclick="location.href='cate_list.do'">
            <span class="help_text">※ 해당 카테고리가 없을 경우 신규 등록하시길 바랍니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품코드</li>
        <li>
            <input type="text" name="productCode" id="productCode" class="product_input1" readonly> 
            <input type="button" value="중복확인" title="중복확인" class="product_btn" onclick="checkProductCode()">
            <span class="help_text">※ 상품코드는 절대 중복되지 않도록 합니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품명</li>
        <li>
            <input type="text" name="productName" id="productName" class="product_input2" maxlength="100"> 
            <span class="help_text">※ 상품명은 최대 100자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품 부가설명</li>
        <li>
            <input type="text" name="productDescription" id="productDescription" class="product_input4" maxlength="200"> 
            <span class="help_text">※ 상품명은 최대 200자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매가격</li>
        <li>
            <input type="text" name="salePrice" id="salePrice" class="product_input3" maxlength="7" placeholder="0"> 
            <span class="help_text">※ , 없이 숫자만 입력하세요 최대 7자리</span>
        </li>
    </ul>
    <ul>
        <li>할인율</li>
        <li>
            <input type="text" name="discountRate" id="discountRate" class="product_input3" maxlength="2" placeholder="0"> % 
            <span class="help_text">※ 숫자만 입력하세요</span>
        </li>
    </ul>
    <ul>
        <li>할인가격</li>
        <li>
            <input type="text" name="discountedPrice" id="discountedPrice" class="product_input3" maxlength="7" value="0" readonly> 
            <span class="help_text">※ 할인율이 0%일 경우 할인가격은 0으로 처리 합니다. (소수점 이하 반올림)</span>
        </li>
    </ul>
    <ul>
        <li>상품재고</li>
        <li>
            <input type="text" name="stockQuantity" id="stockQuantity" class="product_input3" maxlength="4" value="0"> EA 
            <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매 유/무</li>
        <li>
            <label class="product_label">
                <input type="radio" name="saleStatus" value="Y" checked> 판매시작
            </label>
            <label class="product_label">
                <input type="radio" name="saleStatus" value="N"> 판매종료
            </label> 
            <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>조기품절</li>
        <li>
            <label class="product_label">
                <input type="radio" name="earlySoldOut" value="Y"> 사용
            </label>
            <label class="product_label">
                <input type="radio" name="earlySoldOut" value="N" checked> 미사용
            </label>
        </li>
    </ul>
    <ul style="height: 160px;">
        <li>상품 대표이미지</li>
        <li>
            <ol style="width:100%; height: auto;">
                <li style="width:100%; height:45px;">
                    <input type="file" name="mainImage" id="mainImage" required>
                    <span class="help_text">※ 상품 대표이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
                </li>
                <li style="height:45px;">
                    <input type="file" name="additionalImage1" id="additionalImage1">
                    <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
                </li>
                <li style="height:45px;">
                    <input type="file" name="additionalImage2" id="additionalImage2">
                    <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
                </li>
            </ol>
        </li>
    </ul>
    <ul style="height: 400px;">
        <li>상품 상세설명</li>
        <li>
            <textarea name="productDetails" id="productDetails" class="product_text1"></textarea>
        </li>
    </ul>
</div>
<div class="subpage_view4" style="text-align:center; margin-bottom: 100px;">
    <span>
        <input type="button" value="상품 리스트" title="상품 관리 페이지로 이동합니다" class="p_button p_button_color1" style="margin-right: 5px;" onclick="location.href='product_list.do'">
        <input type="button" value="상품 등록" title="상품 등록" class="p_button p_button_color2" onclick="submitProduct()">
    </span>
</div>
</section>
</main>
</form>
<%@include file="../copyright_admin.jsp"%>
</body>
<script src="/resources/js/admin/product_write.js?v=3"></script>
</html>
