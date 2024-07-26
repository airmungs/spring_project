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
			method : "post",
			data : { num : $data }
		});
	});
	
});