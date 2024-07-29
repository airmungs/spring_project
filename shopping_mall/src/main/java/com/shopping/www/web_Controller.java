package com.shopping.www;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class web_Controller extends sha3_pass {
	
	@Resource(name="sha3pass")
	private sha3_pass sh;
	@Resource(name="userselect")
	private user_dao ud;
	//DAO (@ModelAttribute)
	//DAO 없이 사용시 : 자료형 객체 or @RequestParam을 이용해서 사용
	@PostMapping("/search_mid.do")
	public ResponseEntity<Map<String, Object>> idsearch(@RequestParam String mname,@RequestParam String memail) {
		Map<String, Object> response=new HashMap<>();
		if(mname==null || mname.isEmpty() || memail ==null || memail.isEmpty()) {
			response.put("status","error");
			response.put("message","아이디 또는 이메일을 입력하지 않으셨습니다");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}else {
			 try {
		            String userId = ud.search_id(mname, memail);
		            if (userId != null) {
		                response.put("status", "success");
		                response.put("userId", userId);
		                return new ResponseEntity<>(response, HttpStatus.OK);
		            } else {
		                response.put("status", "error");
		                response.put("message", "입력하신 정보에 해당하는 아이디가 존재하지 않습니다.");
		                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		            }
		        } catch (Exception e) {
		            response.put("status", "error");
		            response.put("message", "서버에러");
		            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		        }
		}
	}

	@PostMapping("/modify_mpass.do")
	public String passmodify() {
		
		return null;
	}	
	
	//패스워드 변경 여부를 체크(MD5)
    @GetMapping("/passwd.do")
    public String passwd(String mpass) {
        String result = sh.sha3Making(mpass);
        System.out.println(result);
        return result;
    }
	
	
	
	
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
