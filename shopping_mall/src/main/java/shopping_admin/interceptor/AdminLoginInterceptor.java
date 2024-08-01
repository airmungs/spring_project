package shopping_admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    HttpSession session = request.getSession(false);
	    if (session != null && session.getAttribute("admin") != null) {
	        return true;
	    }
	    // 차단된 요청에 대해 URL에 error 파라미터를 추가하여 리다이렉트
	    String loginPageUrl = request.getContextPath() + "/?error=access_denied";
	    response.sendRedirect(loginPageUrl);
	    return false;
	}
}
