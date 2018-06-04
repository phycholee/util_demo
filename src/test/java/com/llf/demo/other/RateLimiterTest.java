package com.llf.demo.other;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/6/4 17:44
 */
public class RateLimiterTest {

    @Test
    public void testRateLimit(){
        RateLimiter rateLimiter = RateLimiter.create(10);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 120, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 100; i++){
            System.out.println("等待时间：" + rateLimiter.acquire());
            int a = i;
            executor.execute(() -> System.out.println(a));
        }
    }

    @Test
    public void testRateLimit2(){
        RateLimiter rateLimiter = RateLimiter.create(10);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 120, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 100; i++){

            int a = i;
            executor.execute(() -> {
                if (!rateLimiter.tryAcquire(1000L, TimeUnit.MILLISECONDS)){
                    System.out.println("抢购失败");
                    return;
                }

                System.out.print("抢购成功：");

                System.out.println(a);
            });
        }
    }

}
