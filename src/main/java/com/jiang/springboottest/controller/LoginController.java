package com.jiang.springboottest.controller;


import com.jiang.springboottest.model.User;
import com.jiang.springboottest.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {
    /**
     * 模拟用户登录：
     */
    static Map<Integer, User> userMap = new HashMap<>();

    static {
        //模拟数据库
        User user1 = new User(1, "张三", "123456");
        userMap.put(1, user1);
        User user2 = new User(2, "李四", "123123");
        userMap.put(2, user2);
    }

    @RequestMapping("/user")
    public String userLogin(User user) {
        if (user == null) {
            return "请求参数为空";
        }
        for (User dbUser : userMap.values()) {
            if (user.getId().equals(dbUser.getId()) && user.getUserName().equals(dbUser.getUserName()) && user.getPassword().equals(dbUser.getPassword())) {
                log.info("生成token");
                String token = JwtUtils.createToken(dbUser);
                System.out.println(token);
                return token; //返回token
            }
        }
        return null;
    }
}
