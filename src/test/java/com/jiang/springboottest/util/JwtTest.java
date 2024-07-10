package com.jiang.springboottest.util;


import com.auth0.jwt.interfaces.Claim;
import com.jiang.springboottest.model.User;
import com.jiang.springboottest.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class JwtTest {


    User user1 = new User(1,"张三","123456");
    User user2 = new User(2,"李四","123123");

    @Test
    public void test(){

//        String token = JwtUtils.createToken(user1);
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImlkIjoxLCJ1c2VyTmFtZSI6IuW8oOS4iSIsImV4cCI6MTcyMDU4MTAzNSwiaWF0IjoxNzIwNTgwOTQ4fQ.0cqCPQSLc96E2IrvDEnOtbCHzyYHvRwFwAvSlPxNRSw";
        System.out.println(token);
        Map<String, Claim> stringClaimMap = JwtUtils.verifyToken(token);
        System.out.println(stringClaimMap.get("id").asInt());
        System.out.println(stringClaimMap.get("userName").asString());
        System.out.println(stringClaimMap.get("password").asString());
    }
}
