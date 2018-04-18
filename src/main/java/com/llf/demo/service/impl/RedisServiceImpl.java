package com.llf.demo.service.impl;

import com.llf.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/3 19:03
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean set(String key, String value) {
        stringRedisTemplate.boundValueOps(key).set(value);
        return true;
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }
}
