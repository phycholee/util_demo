package com.llf.demo.common.annotation;

import java.lang.annotation.*;

/**
 * @author: Oliver.li
 * @Description: 分布式限流注解
 * @date: 2018/8/17 16:03
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RateLimit {

    /**
     * 限流key
     * @return
     */
    String key();

    /**
     * 限流秒
     * @return
     */
    int second();

    /**
     * 限流数
     * @return
     */
    int count();

}
