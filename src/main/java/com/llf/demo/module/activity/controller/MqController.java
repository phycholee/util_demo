package com.llf.demo.module.activity.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.llf.demo.module.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/4/18 17:43
 */
@RestController
@RequestMapping("/api/mq")
public class MqController {


    private static final String LIST_KEY = "LIST_KEY";

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("redis-pool-%d").build();

    public static Integer PUSH_COUNT = 0;

    @Autowired
    private RedisService redisService;


    @GetMapping("producer")
    public String producer(){
        for (int i = 0; i < 20000; i++){
            String msg = "This is message " + i;
            redisService.lpush(LIST_KEY, msg);
        }

        return "ok";
    }


    /**
     * costTime: 2413
     */
    @GetMapping("consumer1")
    public String consumer1() throws InterruptedException {

        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);

        for (int i = 0; i < 20000; i++){
            String msg = redisService.rPop(LIST_KEY);
            Thread.sleep(100);
            System.out.println(msg);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("costTime: " + (endTime - startTime));

        return "ok";
    }


    @GetMapping("consumer2")
    public String consumer2() throws InterruptedException {

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

        return "ok";
    }


    @GetMapping("consumer3")
    public String consumer3(Integer num) {

        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);

        for (int i = 0; i < num; i++){
            int count = 20000 / num;
            new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    String msg = redisService.rPop(LIST_KEY);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(msg);
                }
            }).start();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("costTime: " + (endTime - startTime));

        return "ok";
    }

}
