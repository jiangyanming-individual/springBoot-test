package com.jiang.springboottest.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HellowController {

    @GetMapping("/login")
    public String userLogin(){
        return "欢迎登录";
    }
}
