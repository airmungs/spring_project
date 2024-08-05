<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 페이징 사용법</title>
</head>
<body>
<p>쿠폰리스트</p>
    <c:if test="${not empty coupons}">
        <table border="1">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>쿠폰명</th>
                    <th>할인율</th>
                    <th>사용 유/무</th>
                    <th>만료기한</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="coupon" items="${coupons}">
                    <tr>
                        <td><c:out value="${coupon.cidx}" /></td>
                        <td><c:out value="${coupon.cpname}" /></td>
                        <td><c:out value="${coupon.cprate}" /></td>
                        <td><c:out value="${coupon.cpuse}" /></td>
                        <td><c:out value="${coupon.cpdate}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty coupons}">
        <p>현재 쿠폰이 없습니다.</p>
    </c:if>
</body>
</html>