package shopping_client.controller;

import java.util.HashMap;
import java.util.Map;

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

import shopping_client.dto.shopping_clients_dto;
import shopping_client.service.shopping_client_service;

@Controller
public class shopping_client_controller {
	
	@Autowired
	private shopping_client_service clientService;
	
	
	//약관동의
	@GetMapping("/sailmall/agree.do")
	public String agree() {
		return "/client/agree";
	}
	//로그인
	@GetMapping("/sailmall/login.do")
	public String login() {
		return "/client/login";
	}
	//회원가입
	@GetMapping("/sailmall/join.do")
	public String join() {
		return "/client/join";
	}
	//회원가입 완료
	@PostMapping("/sailmall/joinOk")
	public ResponseEntity<Map<String, Object>> joinOk(@RequestBody shopping_clients_dto clientsDTO){
		Map<String, Object> response = new HashMap<>();
		try {
			boolean result=clientService.registerClient(clientsDTO);
			if(result) {
				response.put("success", true);
			}else {
				response.put("success", false);	
				response.put("message", "잘못된 정보 입력으로 인한 가입 실패");  	
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("message", "서버 오류 발생");
		}
		
		return ResponseEntity.ok(response);
	}
	//아이디 중복확인
	@GetMapping("/sailmall/idcheck")
    public ResponseEntity<String> check_id(@RequestParam("userId") String userId) {
        try {
            boolean isAvailable = clientService.isIdAvailable(userId);
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
