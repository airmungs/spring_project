package shopping_admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import shopping_admin.model.shopping_admin_dto;
import shopping_admin.service.shopping_admin_service;

@Controller
public class shopping_admin_controller {
	
	@Autowired
	private shopping_admin_service adminService;
	
	//admin로그인 페이지
    @GetMapping("/")
    public String admin_login() {
        return "index";
    }
    //admin 등록 승인 페이지
    @GetMapping("/admin_list.do")
    public String admin_list() {
    	return "admin_list";
    }
    //admin등록 신청 페이지
    @GetMapping("/add_master.do")
    public String add_master() {
    	return "add_master";
    }
  //admin등록 insert
    @PostMapping("/add_masterok.do")
    public ResponseEntity<Map<String,Object>> admin_join(@RequestBody shopping_admin_dto admin_dto){
    	Map<String,Object> response=new HashMap<>();
    	try {
	    	boolean result=adminService.registerAdmin(admin_dto);
	    	if(result) {
	    	response.put("success", true);
	    	}else {
	    	response.put("success", false);	
	    	response.put("message", "등록 실패");  	
	    	}
    	}catch (Exception e) {
            response.put("success", false);
            response.put("message", "서버 오류 발생");

        }
    	
    	return ResponseEntity.ok(response);
    }
  //admin등록신청 id check
    @GetMapping("/idcheck")
    public ResponseEntity<String> checkId(@RequestParam("adid") String adid) {
        try {
            boolean isAvailable = adminService.isIdAvailable(adid);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.set("Content-Type", "text/plain; charset=UTF-8");

            if (isAvailable) {
                return new ResponseEntity<>("사용 가능한 아이디입니다.", headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("이미 사용 중인 아이디입니다.", headers, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HttpHeaders errorHeaders = new HttpHeaders();
            errorHeaders.setContentType(MediaType.TEXT_PLAIN);
            errorHeaders.set("Content-Type", "text/plain; charset=UTF-8");
            return new ResponseEntity<>("서버 오류 발생", errorHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
