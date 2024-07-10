package com.jiang.springboottest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class SecureController {

    /**
     * 查询 用户信息，登录后携带JWT才能访问 是空的？？？
     */
    @RequestMapping("/secure/getUserInfo")
    public String login(HttpServletRequest request) {

        String token = (String) request.getAttribute("token");
        System.out.println("获取的token为：" + token);

        Integer id = (Integer) request.getAttribute("id");
        String userName = request.getAttribute("userName").toString();
        String password = request.getAttribute("password").toString();
        return "当前用户信息id=" + id + ",userName=" + userName + ",password=" + password;
    }

}