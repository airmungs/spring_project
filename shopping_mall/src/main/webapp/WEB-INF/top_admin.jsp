<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shopping_admin.dto.shopping_admin_dto" %>
<%
    shopping_admin_dto admin = (shopping_admin_dto) session.getAttribute("admin");
%>
<header class="headercss">
    <div class="header_div">
        <p><img src="/resources/img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p><%=admin.getAdname() %> <a href="#">[개인정보 수정]</a> <a onclick="alert('로그아웃 되셨습니다.')" href="./admin_logout">[로그아웃]</a></p>
    </div>
</header>
<nav class="navcss">
    <div class="nav_div">
        <ol>
        <%if(admin != null && admin.getAdname().equals("최고관리자")){ %>
            <li title="쇼핑몰 상품관리"><a href="./admin_list.do">쇼핑몰 관리자 리스트</a></li>
        <%} %>    
            <li title="쇼핑몰 회원관리"><a href="./shop_member_list.do">쇼핑몰 회원관리</a></li>
            <li title="쇼핑몰 상품관리"><a href="./product_list.do">쇼핑몰 상품관리</a></li>
            <li title="쇼핑몰 기본설정"><a href="./admin_siteinfo.do">쇼핑몰 기본설정</a></li>
            <li title="쇼핑몰 기본설정"><a href="./notice_list.do">쇼핑몰 공지사항</a></li>
        </ol>
    </div>
</nav>