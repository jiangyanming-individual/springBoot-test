package com.jiang.springboottest.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jiang.springboottest.model.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final long EXPIRE_TIME = 60 * 60 * 24; // 一个小时
    private static final String TOKEN_SECRET = "token123";  //密钥盐

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public static String createToken(User user) {

        String token = null;
        // jwt 三个主要部分： header,payload,sign
        HashMap<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        //生成token
        token = JWT.create()
                .withHeader(map)
                .withClaim("id", user.getId())
                .withClaim("userName", user.getUserName())
                .withClaim("password", user.getPassword())
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())//签发时间；
                .sign(Algorithm.HMAC256(TOKEN_SECRET));

        return token;
    }

    /**
     * 验证签名
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            jwt = jwtVerifier.verify(token);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("token解析错误");
            e.printStackTrace();
        }
        return jwt.getClaims();
    }

}
