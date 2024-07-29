package shopping_admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shopping_admin.dao.shopping_admin_dao;
import shopping_admin.model.shopping_admin_dto;

@Controller
public class shopping_admin_controller {
	
	@Resource(name="shoppingDao")
	private shopping_admin_dao sad;
	
    @GetMapping("/")
    public String admin_login() {
        return "index"; // /WEB-INF/views/index.jsp 를 렌더링
    }
    
    
    @GetMapping("/admin_list.do")
    public String admin_list() {
    	
    	return "admin_list";
    }
    
    @GetMapping("/add_master.do")
    public String add_master() {
    	
    	return "add_master";
    }
    
    @PostMapping("/add_masterok.do")
    public ResponseEntity<Map<String,Object>> admin_join(@RequestBody shopping_admin_dto admin_dto){
    	Map<String,Object> response=new HashMap<>();
    	int result=sad.shoppingInsert(admin_dto);
    	if(result>0) {
    	response.put("success", true);
    	}else {
    	response.put("success", false);	
    	response.put("message", "등록 실패");  	
    	}
    	
    	return ResponseEntity.ok(response);
    }
}
