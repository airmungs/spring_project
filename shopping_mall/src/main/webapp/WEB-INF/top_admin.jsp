<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shopping_admin.model.shopping_admin_dto" %>
<%
    shopping_admin_dto admin = (shopping_admin_dto) session.getAttribute("admin");
%>
<header class="headercss">
    <div class="header_div">
        <p><img src="/resources/img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p><%=admin.getAdname() %> <a href="#">[개인정보 수정]</a> <a href="./admin_logout">[로그아웃]</a></p>
    </div>
</header>
<nav class="navcss">
    <div class="nav_div">
        <ol>
            <li title="쇼핑몰 상품관리">쇼핑몰 관리자 리스트</li>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정">쇼핑몰 기본설정</li>
        </ol>
    </div>
</nav>