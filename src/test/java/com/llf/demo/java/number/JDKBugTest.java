package com.llf.demo.java.number;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/10/29 17:11
 */
public class JDKBugTest {
    public void test() {
        int i = 8;
        while ((i -= 3) > 0);
        System.out.println("i = " + i);
    }

    public static void main(String[] args) {
//        JDKBugTest hello = new JDKBugTest();
//        for (int i = 0; i < 50_000; i++) {
//            hello.test();
//        }

        System.out.println('0' - 1);
    }
}