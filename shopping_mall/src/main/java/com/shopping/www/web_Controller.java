package com.shopping.www;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class web_Controller {
	
	
	//@RequestBody : GET/POST(X) JSON 기반일 경우
	//@ResonseBody : 미디어 타입, 파라미터 타입. 단, 인자값에는 미선언 
	//ajax 통신 cors 기반
	
	//HttpSession : interface 를 활용하여, 세션을 빠르게 구현하는 방식
	@PostMapping("/loginok.do")
	public String loginok(@RequestParam(value="",required = false)String mid,HttpSession hs) {
		if(mid!=null) {
		hs.setAttribute("mid", mid);
		hs.setMaxInactiveInterval(1800);
		}
		return "/index3";
	}
	
	/*
	@PostMapping("/loginok.do")
	public String loginok(String mid,HttpServletRequest req) {
		HttpSession hs=req.getSession();
		hs.setAttribute("mid", mid);
		hs.setMaxInactiveInterval(1800);
		System.out.println(mid);
		return "/index3";
	}
	*/
	
	
	
	@CrossOrigin(origins="*", allowedHeaders="*")
	@PostMapping("/ajaxok4.do")
	public ResponseEntity<Map<String, Object>> ajaxok2(@RequestBody Map<String, List<List<String>>> data) {
		List<List<String>> dataload=data.get("data");
		System.out.println(dataload);
		System.out.println(dataload.get(0));
		System.out.println(dataload.get(1));
		Map<String, Object> response=new HashMap<>();
		response.put("success", true);
		return ResponseEntity.ok(response);
	}
	
	
	
	
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/ajax123.do")
    public ResponseEntity<Map<String, Object>> handleAjaxRequest(@RequestBody Map<String, List<String>> data) {
        List<String> dataload = data.get("data");
        System.out.println(dataload);

        Map<String, Object> response = new HashMap<>();
        for (String dataIndex : dataload) {
        	System.out.println(dataIndex);
            if (dataIndex.length() < 3) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", dataIndex + "의 글자수가 제한에 초과되었습니다");
                return ResponseEntity.ok(response);  // 제한에 초과된 경우 즉시 응답 반환
            }
        }
        response.put("success", true);
        return ResponseEntity.ok(response);
    }
    
    
	
	@CrossOrigin(origins="*", allowedHeaders="*")
	@PostMapping("/ajaxok2.do")
	public ResponseEntity<Map<String, Object>> ajaxok(@RequestBody Map<String, List<String>> data) {
		List<String> dataload=data.get("data");
		System.out.println(dataload);
		Map<String, Object> response=new HashMap<>();
		for(String dataIndex : dataload) {
			if(dataIndex.length()<3) {
				response.put("success",true);
			}else{
				response.put("success", false);
				response.put("message",dataIndex+"의 글자수가 제한에 초과되었습니다");
			}
		}
		response.put("success", true);
		return ResponseEntity.ok(response);
	}
	
	/*
	@CrossOrigin(origins="*", allowedHeaders="*")
	@PostMapping("/ajaxok.do")
	public ResponseEntity<Map<String, String>> ajaxok(@RequestBody Map<String, List<Object>> data) {
		Map<String, String> response=new HashMap<>();
		
		return ResponseEntity.ok(response);
	}
	*/
}
