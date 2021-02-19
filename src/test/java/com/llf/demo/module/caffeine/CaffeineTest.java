package com.llf.demo.module.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/10/10 11:47
 */
public class CaffeineTest {

    private final LoadingCache<String, Object> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(100)
            .weakKeys()
            .build(this::getValue);

    @Test
    public void test1(){

        System.out.println(cache.get("as"));
    }

    private Integer getValue(String key){
        int h;
        int i = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        if (i < 1000){
            return null;
        }
        return i;
    }

}
