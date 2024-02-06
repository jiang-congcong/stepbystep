package cn.ccj.step.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class IdentityInterceptor implements HandlerInterceptor {
    private static final String TOKEN = "token";
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String token = request.getHeader(TOKEN);
        if (StringUtils.isBlank(token)) {
            return false;
        }

        return JwtTool.verifyToken(token);
    }
}
