<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<input type="button" value="ajax" id="btn">
</body>
<script>
$(function(){
	$("#btn").click(function(){
        var datas = [];
        datas.push("홍길동");
        datas.push("유관순");
        datas.push("강감찬");
        datas.push("이순신");
		 $.ajax({
			url : "ajax123.do",
			type : "post",
			cache : false,
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify({ data : datas}),
            success: function(result){
                console.log(result);
            },
            error: function(error){
                console.log(error);
            }            
			
			
		});
	});
});
</script>
</html>