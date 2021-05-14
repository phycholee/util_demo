package com.llf.demo.cache;

/**
 * @author: Oliver.li
 * @Description: 本地缓存
 * @date: 2021/5/6 18:17
 */
public @interface LocalCache {

    /**
     * 获取缓存的key，支持spring的spEL表达式
     * @return
     */
    String key();

    /**
     * 过期时间，默认60秒
     * @return
     */
    int expireSeconds() default 60;

    /**
     * 缓存最大数量
     * @return
     */
    int maximumSize() default 1000;
}
