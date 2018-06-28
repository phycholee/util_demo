package com.llf.demo.module.redis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/3 19:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void set() {
        redisService.set("llf", "rage");
    }

    @Test
    public void get() {
        System.out.println(redisService.get("llf"));
    }

    @Test
    public void mset(){
        redisService.mset("map", "aa", "AA");

        System.out.println(redisService.mget("map", "aa"));
    }

    @Test
    public void testLock2(){
        RedisLock redisLock = new RedisLock("LOCK_TEST");

        boolean lock = false;
        try {
            lock = redisLock.tryLock();

            System.out.println("get lock result : " + lock);

            if (lock) {
                System.out.println("doing something");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisLock.unlock();
            System.out.println("unlock");
        }


    }

    @Test
    public void testLock(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 100L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

        for (int i = 0; i < 5; i++){
            executor.execute(new LockThread(i));
        }
    }

    class LockThread implements Runnable{

        private int i;

        public LockThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            RedisLock redisLock = new RedisLock("LOCK_TEST");

            boolean lock = false;
            try {
                lock = redisLock.lock();

                System.out.println("thread " + i + " get lock result : " + lock);

                if (lock) {
                    System.out.println("thread " + i + " doing something");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                redisLock.unlock();
                System.out.println("thread " + i + " unlock");
            }


        }
    }
}