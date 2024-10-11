<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shopping_admin.dto.shopping_admin_dto" %>
<%
    shopping_admin_dto admin = (shopping_admin_dto) session.getAttribute("admin");
%>
<style>
/* 상단바 크기 및 버튼 스타일 조정 */
.headercss {
    background-color: #333;
    color: white;
    padding: 6px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.header_div p {
    margin: 0;
    font-size: 12px;
}

.header_div img {
    vertical-align: middle;
}

/* 네비게이션 바 스타일 */
.navcss {
	height: 90px;
    background-color: #222; /* 어두운 배경 색상 */
    padding: 10px 0;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.5); /* 네비게이션 바에 그림자 추가 */
}

.nav_div {
    max-width: 1200px;
    margin: 0 auto; /* 중앙 정렬 */
}

.nav_div ol {
    list-style: none;
    margin: 0;
    display: flex;
    align-items: center;
    height: 30px;
}

.nav_div ol li {
    flex: 1;
}

.nav_div ol li a {
    display: block;
    text-decoration: none;
    color: white;
    text-align: center;
    font-size: 14px;
}

.nav_div ol li a:hover {
    background-color: #444;
}

.nav_div ol li.active a {
    background-color: rgb(81, 61, 61);
        color: wheat;
    font-weight: bold;
}

/* 로고 크기 조정 */
.logo_sm {
    width: 30px;
    height: auto;
}
</style>

<header class="headercss">
    <div class="header_div">
        <p><img src="/resources/img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p><%=admin.getAdname() %> 님 안녕하세요.  <a onclick="alert('로그아웃 되셨습니다.')" href="./admin_logout">[로그아웃]</a></p>
    </div>
</header>

<nav class="navcss">
    <div class="nav_div">
        <ol>
            <%if(admin != null && admin.getAdname().equals("최고관리자")){ %>
            <li title="쇼핑몰 관리자 리스트" class="<%= (request.getRequestURI().contains("admin_list") ? "active" : "") %>">
                <a href="./admin_list.do">쇼핑몰 관리자 리스트</a>
            </li>
            <%} %>
            <li title="쇼핑몰 회원관리" class="<%= (request.getRequestURI().contains("shop_member_list") ? "active" : "") %>">
                <a href="./shop_member_list.do">쇼핑몰 회원관리</a>
            </li>
            <li title="쇼핑몰 상품관리" class="<%= (request.getRequestURI().contains("product_list") ? "active" : "") %>">
                <a href="./product_list.do">쇼핑몰 상품관리</a>
            </li>
            <li title="쇼핑몰 기본설정" class="<%= (request.getRequestURI().contains("admin_siteinfo") ? "active" : "") %>">
                <a href="./admin_siteinfo.do">쇼핑몰 기본설정</a>
            </li>
            <li title="쇼핑몰 공지사항" class="<%= (request.getRequestURI().contains("notice_list") ? "active" : "") %>">
                <a href="./notice_list.do">쇼핑몰 공지사항</a>
            </li>
        </ol>
    </div>
</nav>
