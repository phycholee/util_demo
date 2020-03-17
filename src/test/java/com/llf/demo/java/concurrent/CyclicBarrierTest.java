package com.llf.demo.java.concurrent;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/9/19 23:40
 */
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier;

    static class CyclicBarrierThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了");
            //等待
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "等完了");
        }
    }

    public static void main(String[] args){
        cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("人到齐了，开会吧...."));

        for(int i = 0 ; i < 5 ; i++){
            new CyclicBarrierThread().start();
        }
    }

}
