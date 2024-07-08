package com.jiang.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootTestApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Test
    void contextLoads() {
        // 添加
        redisTemplate.opsForValue().set("userName", "沉默王二");
        // 查询
        System.out.println(redisTemplate.opsForValue().get("userName"));
        // 删除
        redisTemplate.delete("userName");
        // 更新
        redisTemplate.opsForValue().set("userName", "沉默王二的狗腿子");
        // 查询
        System.out.println(redisTemplate.opsForValue().get("userName"));

//        // 添加
//        stringRedisTemplate.opsForValue().set("name", "沉默王二");
//        // 查询
//        System.out.println(stringRedisTemplate.opsForValue().get("name"));
//        // 删除
//        stringRedisTemplate.delete("name");
//        // 更新
//        stringRedisTemplate.opsForValue().set("name", "沉默王二的狗腿子");
//        // 查询
//        System.out.println(stringRedisTemplate.opsForValue().get("name"));

    }

}
