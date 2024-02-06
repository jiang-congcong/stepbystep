package cn.ccj.step.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 解决跨域
 */

@Component
public class CorsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有域名跨域访问
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允许的请求方法
        response.setHeader("Access-Control-Max-Age", "3600"); // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Allow-Headers", "token,Origin, X-Requested-With, Content-Type, Accept"); // 允许的请求头
        return true;
    }
}
