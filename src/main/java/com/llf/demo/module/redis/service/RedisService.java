package com.llf.demo.module.redis.service;

import java.util.Map;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/3 18:53
 */
public interface RedisService {

    boolean set(String key, String value);

    String get(String key);

    boolean mset(String key, String field, String value);

    boolean msetAll(String key, Map<String, Object> map);

    Object mget(String key, String field);

    Map<String, Object> mgetAll(String key);
}
