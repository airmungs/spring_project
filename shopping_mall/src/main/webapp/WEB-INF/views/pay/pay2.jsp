<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pay - 고객정보 입력 및 결제금액 정보셋팅</title>
</head>
<body>
<form id="frm" method="post" action="./pay3.do">
<input type="hidden" name="goodcode" value="${product_code}">
<input type="hidden" name="goodname" value="${product_nm }">
<input type="hidden" name="goodea" value="${product_ea }">
<input type="hidden" name="goodprice" value="${product_money }">
<input type="hidden" name="price" value="${price }">
상품코드 : ${product_code} <br>
상품명 : ${product_nm }<br>
상품가격 : ${product_money }원 <br>
상품갯수 : ${product_ea }EA <br>
총결제금액 : ${price }원 <br>

<p>고객정보입력</p>
고객명 : <input type="text" name="buyername"> <br>
연락처 : <input type="text" name="buyertel" maxlength="11"> *"-" 숫자만 입력<br>
이메일 : <input type="text" name="buyeremail">* 실제 이메일 주소<br>
주소 : <input type="text" name="rec_post" maxlength="5" value="04063" readonly="readonly"><br>
<input type="text" name="rec_way" value="서울특별시 마포구 광성로 17" placeholder="도로명주소"><br>
<input type="text" name="rec_addr" value="신촌숲아이파크" placeholder="상세주소"><br>
<p>결제방식</p>
<input type="radio" name="gopaymethod" value="Card"> 신용카드
<input type="radio" name="gopaymethod" value="DirectBank"> 계좌이체
<input type="radio" name="gopaymethod" value="VBank"> 무통장입금
<br>
<input type="button" value="결제 선택완료" onclick="gopay()">
</form>
</body>
<script>
function gopay(){
frm.submit();
}
</script>
</html>