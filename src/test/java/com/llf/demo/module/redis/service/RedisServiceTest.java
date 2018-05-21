package com.llf.demo.module.redis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/3 19:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void set() {
        redisService.set("llf", "rage");
    }

    @Test
    public void get() {
        System.out.println(redisService.get("llf"));
    }
}