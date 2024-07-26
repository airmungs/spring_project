package com.shopping.www;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class web_Controller {
	
	@GetMapping("/ajaxok.do")
	public String ajaxok() {
		
		return null;
	}
}
