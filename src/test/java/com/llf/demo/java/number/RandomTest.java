package com.llf.demo.java.number;

import java.util.Random;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/11/20 16:42
 */
public class RandomTest {


    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(10000);

        System.out.println(i);

    }
}
