<%@page import="pay.pay_dto"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    pay_dto dto=(pay_dto) request.getAttribute("data");

    String mid = "INIpayTest";
    String signKey = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";
    String goodcode = dto.getGoodcode(); 
    String mKey = SignatureUtil.hash(signKey, "SHA-256");
    String timestamp = SignatureUtil.getTimestamp();
    String orderNumber = mid+"_"+goodcode;	
    String price = dto.getPrice();

    Map<String, String> signParam = new HashMap<String, String>();
    signParam.put("oid", orderNumber);
    signParam.put("price", price);
    signParam.put("timestamp", timestamp);

    String signature = SignatureUtil.makeSignature(signParam);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 및 결제 최종 정보 확정, 결제 API연동</title>
</head>
<body>
<form id="frm">
		<!-- API version -->
        <input type="hidden" name="version" value="1.0">
        <!-- 결제방식 -->
        <input type="hidden" name="gopaymethod" value="<%=dto.getGopaymethod() %>">
        <!-- PG가입 아이디 -->
        <input type="hidden" name="mid" value="<%=mid %>">
        <!-- 결제 사항에 대한 정보를 SHA-256로 암호화 된 코드 -->
        <input type="hidden" name="signature" value="<%=signature %>">
        <!-- 결제를 진행하는 시간 -->
        <input type="hidden" name="timestamp" value="<%=timestamp %>">
        <!--  -->
        <input type="hidden" name="oid" value="<%= orderNumber %>">
        <!-- 결제 비밀번호 키 -->
        <input type="hidden" name="mKey" value="<%= mKey %>">
        <!-- 결제 단위 -->
        <input type="hidden" name="currency" value="WON">
        <!-- 상품명 -->
        <input type="hidden" name="goodname" value="<%= dto.getGoodname() %>">
        <!-- 구매자이름 -->
        <input type="hidden" name="buyername" value="<%= dto.getBuyername() %>">
        <!-- 구매자 연락처 -->
        <input type="hidden" name="buyertel" value="<%= dto.getBuyertel() %>">
        <!-- 구매자이메일 -->
        <input type="hidden" name="buyeremail" value="<%= dto.getBuyeremail() %>">
        <!-- 구매자 우편번호 -->
        <input type="hidden" name="rec_post" value="<%= dto.getRec_post() %>">
        <!-- 구매자 도로명주소 -->
        <input type="hidden" name="rec_way" value="<%= dto.getRec_way() %>">
        <!-- 구매자 상세주소 -->
        <input type="hidden" name="rec_addr" value="<%= dto.getRec_addr() %>">
        <!-- 구매가격 -->
        <input type="hidden" name="price" value="<%=price %>">
        <!-- 결제 성공 및 결제 실패에 대한 값을 return 정보 -->
        <input type="hidden" name="returnUrl" value="http://localhost:8080/pay/return_url.jsp">
        <!-- 결제 취소시 사용되는 페이지 -->
        <input type="hidden" name="closeUrl" value="http://localhost:8080/pay/close.jsp">
<p>-------상품정보--------</p>
상품코드 : <%=dto.getGoodcode() %><br>
상품명 : <%=dto.getGoodname() %><br>
상품 갯수 : <%=dto.getGoodea() %> <br>
<p>-------결제자정보--------</p>
결제자명 : <%=dto.getBuyername() %><br>
연락처 : <%=dto.getBuyertel() %><br>
이메일 : <%=dto.getBuyeremail() %><br>
<p>-------배송정보--------</p>
수령 우편번호 : <%=dto.getRec_post() %> <br>
도로명 주소 : <%=dto.getRec_way() %><br>
상세 주소 : <%=dto.getRec_addr() %><br>
<p>-------결제금액 및 결제수단--------</p>
최종 결제금액 : <%=dto.getPrice() %><br>
결제 수단 : <%=dto.getGopaymethod() %><br>
<input type="button" value="결제하기" onclick="payok()">
</form>
</body>
<script src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js"></script>
<script>
function payok(){
	INIStdPay.pay("frm");
}
</script>
</html>