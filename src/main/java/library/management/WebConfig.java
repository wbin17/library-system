package library.management;

import library.management.web.interceptor.AdminCheckInterceptor;
import library.management.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add","/members/checkDuplicate","/login","/logout","/admin/add-asf4a6fv1a5f4a6",
                        "/admin/accessDenied", "/css/**", "/*.ico", "/error");
        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/add-asf4a6fv1a5f4a6", "/admin/accessDenied");

    }
}
