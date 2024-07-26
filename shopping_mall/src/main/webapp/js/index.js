$(function(){
	$("#btn").click(function(){
		var $data = new Array();
		$data[0]=20;
		$data[1]=40;
		$data[2]=50;
		$data[3]=60;
		console.log($data);
		$.ajax({
			url : "./ajaxok.do",
			cache : false,
			dataType : "json",
			contentType : "application/json",
			data : { alldata : $data },
			success : function ($result){
				console.log($result)
			},error : function(){
				console.log(error);
			}
		});
	});
	
});