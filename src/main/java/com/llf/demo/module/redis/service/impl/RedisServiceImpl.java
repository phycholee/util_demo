package com.llf.demo.module.redis.service.impl;

import com.llf.demo.module.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/3 19:03
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean set(String key, String value) {
        stringRedisTemplate.boundValueOps(key).set(value);
        return true;
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }

    @Override
    public boolean mset(String key, String field, String value) {
        redisTemplate.boundHashOps(key).put(field, value);
        return true;
    }

    @Override
    public boolean msetAll(String key, Map<String, Object> map) {
        redisTemplate.boundHashOps(key).putAll(map);
        return true;
    }

    @Override
    public Object mget(String key, String field) {
        return redisTemplate.boundHashOps(key).get(field);
    }

    @Override
    public Map<String, Object> mgetAll(String key) {
        return redisTemplate.boundHashOps(key).entries();
    }

    @Override
    public void lpush(String key, String value) {
        redisTemplate.boundListOps(key).leftPush(value);
    }

    @Override
    public String rPop(String key) {
        Object pop = redisTemplate.boundListOps(key).rightPop();
        return pop != null ? pop.toString() : null;
    }
}
