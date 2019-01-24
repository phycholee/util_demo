package com.llf.demo.module.redis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/12/3 11:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedissonLockTest {

    @Test
    public void getLock() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 100L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

        for (int i = 0; i < 10; i++){
            executor.execute(new RedissonLockTest.LockThread(i));
        }
    }

    class LockThread implements Runnable{

        private int i;

        public LockThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
//            RedissonLock redissonLock = new RedissonLock("REDISSON_LOCK_TEST");
//
//            try {
//                redissonLock.lock();
//
//                System.out.println("thread " + i + " doing something");
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                redissonLock.unlock();
//                System.out.println("thread " + i + " unlock");
//            }


        }
    }
}