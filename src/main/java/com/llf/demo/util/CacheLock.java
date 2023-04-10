package com.llf.demo.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2021/12/17 18:08
 */
public class CacheLock {

    private final ReentrantLock lock = new ReentrantLock();

    public void test(){
        try {
            boolean tryLock = lock.tryLock();
            if (tryLock){
                //set cache
                System.out.println(Thread.currentThread().getName() + " I've got the key");
                Thread.sleep(1000);
            } else {
                System.out.println(Thread.currentThread().getName() + " I'm going to sleep");
                this.wait(2000);
                System.out.println(Thread.currentThread().getName() + " I'm awake");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " unlock");
            lock.unlock();
            this.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CacheLock cacheLock = new CacheLock();
        for (int i = 0; i < 3; i++){
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " begin...");
                cacheLock.test();
                System.out.println(Thread.currentThread().getName() + " end...");
            });
        }

        Thread.sleep(2000);
        executor.shutdown();
    }

}
