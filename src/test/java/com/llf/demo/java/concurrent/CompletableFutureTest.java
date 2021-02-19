package com.llf.demo.java.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/9/8 14:35
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(CompletableFutureTest::getCode);

        CompletableFuture<Double> future2 = future1.thenApplyAsync((code) -> getDouble());

        future2.thenAccept((result)->{
            String threadName = Thread.currentThread().getName();
            System.out.println("result=" + result + ", threadName=" + threadName);
            countDownLatch.countDown();
        });

        future2.exceptionally((e)->{
            String threadName = Thread.currentThread().getName();
            System.out.println("exception=" + e.getMessage() + ", threadName=" + threadName);
            countDownLatch.countDown();
            return null;
        });

        countDownLatch.await();
        String threadName = Thread.currentThread().getName();
        System.out.println("mission complete! threadName=" + threadName);
    }

    private static String getCode(){
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println("getCode sleeping! threadName=" + threadName);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "llf";
    }

    private static double getDouble(){
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println("getDouble sleeping! threadName=" + threadName);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double random = Math.random();
        if (random < 0.5){
            throw new RuntimeException("getDouble error");
        }
        return random;
    }
}
