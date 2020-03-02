package com.llf.demo.module.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.llf.demo.module.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/4/18 16:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ThreadTest {

    private static final String LIST_KEY = "LIST_KEY";

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("redis-pool-%d").build();

    public static Integer PUSH_COUNT = 0;

    @Autowired
    private RedisService redisService;

    @Test
    public void producer(){
        for (int i = 0; i < 20000; i++){
            String msg = "This is message " + i;
            redisService.lpush(LIST_KEY, msg);
        }
    }


    /**
     * costTime: 2413
     */
    @Test
    public void consumer1() throws InterruptedException {

        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);

        for (int i = 0; i < 20000; i++){
            String msg = redisService.rPop(LIST_KEY);
            Thread.sleep(100);
            System.out.println(msg);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("costTime: " + (endTime - startTime));
    }


    @Test
    public void consumer2() throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());


        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);

        for (int i = 0; i < 20000; i++){

            if (PUSH_COUNT ++ % 500 == 0){
                Thread.sleep(1000);
                PUSH_COUNT = 1;
            }

            String msg = redisService.rPop(LIST_KEY);

            executor.execute(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println(msg);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        long endTime = System.currentTimeMillis();
        System.out.println("costTime: " + (endTime - startTime));
    }



}
