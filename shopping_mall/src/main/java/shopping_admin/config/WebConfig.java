package shopping_admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shopping_admin.interceptor.admin_login_interceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private admin_login_interceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 모든 경로에 대해 인터셉터 적용
                .excludePathPatterns("/", "/login", "/add_master.do", "/add_masterok.do", "/idcheck"); // 로그인 및 회원가입 페이지는 제외
    }
}
