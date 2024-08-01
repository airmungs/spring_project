package shopping_admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	//카테고리 리스트 페이지
	@GetMapping("/cate_list.do")
    public String cate_list() {
        return "cate_list";
    }
	
	//관리자 로그인 승인
	@GetMapping("/update_admin_status.do")
	public ResponseEntity<Map<String, Object>> updateAdminStatus(
	        @RequestParam("adid") String adid,
	        @RequestParam("status") String status) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        boolean result = adminService.updateAdminStatus(adid, status);
	        if (result) {
	            response.put("success", true);
	        } else {
	            response.put("success", false);
	            response.put("message", "상태 업데이트 실패");
	        }
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "서버 오류 발생: " + e.getMessage());
	    }
	    return ResponseEntity.ok(response);
	}
	
	//admin로그인 index 페이지
    @GetMapping("/")
    public String admin_login() {
        return "index";
    }
    //admin 등록 승인 페이지
    @GetMapping("/admin_list.do")
    public String admin_list(Model m) {
    	m.addAttribute("adminList", adminService.adminList());
    	return "admin_list";
    }
    //admin등록 신청 페이지
    @GetMapping("/add_master.do")
    public String add_master() {
    	return "add_master";
    }
    //admin 로그아웃
    @GetMapping("/admin_logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/"; // 로그인 페이지로 리다이렉트
    }
    //admin 로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> adminLoginok (@RequestBody Map<String, String> loginData, HttpServletRequest request){
    	Map<String,Object> response=new HashMap<>();
    	String adid = loginData.get("adid");
        String adpass = loginData.get("adpass");
        try {
            shopping_admin_dto admin = adminService.adminLogin(adid, adpass);
            if (admin != null && admin.getLogin().equals("Y")) {
            	request.getSession().setAttribute("admin", admin);
                response.put("success", true);
                response.put("admin", admin);
            }else if(admin != null && admin.getLogin().equals("N")) {
            	response.put("success", false);
                response.put("message", "로그인 실패: 승인되지 않은 관리자 입니다");
            }else {
                response.put("success", false);
                response.put("message", "로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "서버 오류 발생: " + e.getMessage());
        }
    	return ResponseEntity.ok(response);
    }
  //admin등록 insert
    @PostMapping("/add_masterok.do")
    public ResponseEntity<Map<String,Object>> adminJoin(@RequestBody shopping_admin_dto adminDTO){
    	Map<String,Object> response=new HashMap<>();
    	try {
	    	boolean result=adminService.registerAdmin(adminDTO);
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
