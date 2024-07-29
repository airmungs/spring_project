package shopping_admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class shopping_admin_controller {
    @GetMapping("/")
    public String home() {
        return "index"; // /WEB-INF/views/index.jsp 를 렌더링
    }
}
