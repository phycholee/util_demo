package com.llf.demo.java.bit;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/7/25 10:21
 */
public class BitTest {

    public static void main(String[] args) {

        //10011
        int value = 0;

        int powerNum = 1 << 16;

        int result = value | powerNum;

        System.out.println("result = " + result);

        int mark = result & 1 << 16;

        System.out.println("mark = " + mark);

    }


}
