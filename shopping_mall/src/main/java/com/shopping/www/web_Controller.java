package com.shopping.www;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class web_Controller {
	
	
	//@RequestBody : GET/POST(X) JSON 기반일 경우
	//@ResonseBody : 미디어 타입, 파라미터 타입. 단, 인자값에는 미선언 
	//ajax 통신 cors 기반
	
	@CrossOrigin(origins="*", allowedHeaders="*")
	@GetMapping("/ajaxok.do")
	public ResponseEntity<Map<String, String>> ajaxok(@RequestBody Map<String, List<Object>> data) {
		Map<String, String> response=new HashMap<>();
		
		return ResponseEntity.ok(response);
	}
}
