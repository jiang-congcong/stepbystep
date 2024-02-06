package cn.ccj.step.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final IdentityInterceptor identityInterceptor;
    private final CorsInterceptor corsInterceptor;

    public WebMvcConfig(
            IdentityInterceptor identityInterceptor,
            CorsInterceptor corsInterceptor) {
        this.identityInterceptor = identityInterceptor;
        this.corsInterceptor = corsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(identityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/register", "/user/login");

        registry.addInterceptor(corsInterceptor);
    }
}
