package shopping_admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class admin_login_interceptor implements HandlerInterceptor {
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        HttpSession session = request.getSession(false);
	        if (session != null && session.getAttribute("admin") != null) {
	            return true; // 세션에 admin 정보가 있으면 요청을 계속 처리
	        }
	        // 세션에 admin 정보가 없으면 로그인 페이지로 리다이렉트
	        response.sendRedirect(request.getContextPath() + "/");
	        return false;
	    }
}
