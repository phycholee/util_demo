package com.llf.demo.service;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/3 18:53
 */
public interface RedisService {

    boolean set(String key, String value);

    String get(String key);

}
