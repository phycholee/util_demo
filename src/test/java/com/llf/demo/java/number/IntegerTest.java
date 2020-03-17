package com.llf.demo.java.number;

import java.lang.reflect.Field;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/9/4 15:48
 */
public class IntegerTest {

    public static void main(String[] args) {

        Integer a = 1;
        Integer b = 2;

        System.out.printf("a=%d; b=%d", a, b);

        swap(a, b);

        System.out.printf("\na=%d; b=%d", a, b);

    }

    private static void swap(Integer a, Integer b) {
        int temp = a;

        System.out.printf("\ntemp=%d", temp);

        try {
            Field value = Integer.class.getDeclaredField("value");
            value.setAccessible(true);
            value.set(a, b);
            value.set(b, temp);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
