package com.llf.demo.java.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/5/6 11:31
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(0);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("lalala");

    }

}
