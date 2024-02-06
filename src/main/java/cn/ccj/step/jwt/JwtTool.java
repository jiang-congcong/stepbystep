package cn.ccj.step.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTool {
    private static final String SECRET = "cochwoipqbcpqihwqw127ecbqw7e1wcbqpiw";
    private static final Long expiration = 3600L;

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(String userId) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + expiration * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                // 添加头部
                .withHeader(map)
                //可以将基本信息放到claims中
                .withClaim("userId", userId)
                //超时设置,设置过期的日期
                .withExpiresAt(expireDate)
                //签发时间
                .withIssuedAt(new Date())
                //SECRET加密
                .sign(Algorithm.HMAC256(SECRET));
        System.out.println(token);
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static boolean verifyToken(String token) {
        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
            if (jwt.getExpiresAt().before(new Date())) {
                System.out.println("token过期");
                return false;
            }
        } catch (Exception e) {
            //解码异常则抛出异常
            System.out.println("token解析异常:" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取签名中的用户id
     * @param token token
     * @return userId
     */
    public static String getUserId(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        Object userId = body.get("userId");

        return userId == null ? "" : userId.toString();
    }
}
