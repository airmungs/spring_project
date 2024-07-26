$(function(){
	
	$("#homework").click(function(){
	var basket = [
		{"seq":"1","product":"냉장고","price":"1950000"},
		{"seq":"2","product":"세탁기","price":"20005000"},
		{"seq":"10","product":"에어프라이어","price":"3480000000"},
	];
	
	
});
	
	$("#btn3").click(function(){
		var $arr=[ 
			["10%","20%","30%"],
			["30","40","50"]
		];
		$.ajax({
			url : "ajaxok4.do",
			type : "post",
			cache : false,
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify({data : $arr}),
            success: function($result){
				if($result.success){
                	console.log("ok");
				}else{
					console.log($result.message)
				}
            },
            error: function($error){
                console.log($error);
            }            
			
			
		});
		
	});
	
	
	
	
	
	$("#btn2").click(function(){
	var datas=[];
	datas.push("홍길동");
	datas.push("유관순");
	datas.push("강감찬");
	datas.push("이순신");
	console.log(datas);
		$.ajax({
			url : "./ajaxok2.do",
			type : "post",
			cache : false,
			dataType : "json",
			contentType : "application/json",
			data:JSON.stringify({data : datas}),
			success : function(result){
				if(result.success){
				console.log("모두 통과");
				}else{
					console.log(result.message);
				}
			},error : function(error){
				console.log(error);
			}			
		});
		
	});
	
	
	
/*
	$("#btn").click(function(){
		var $data = [];
		$data.push(20);
		$data.push(40);
		$data.push(50);
		$data.push(60);
		console.log($data);
		$.ajax({
			url : "./ajaxok.do",
			type : 'post',
			contentType : "application/json",
			data : JSON.stringify({ alldata : $data }),
			success : function ($result){
				
				console.log($result)
			},error : function(e){
				console.log(e);
			}
		});
	});
*/
});