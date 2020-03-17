package com.llf.demo.java.string;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/9/18 21:33
 */
public class InternTest {


    public static void main(String[] args) {

//        String foo = "aa";
//        String bar = foo;
//
//        foo = "bb";
//
//        System.out.println(bar);


        test2();

    }

    /**
     * true
     * 491044090
     * 491044090
     */
    public static void test1(){
        char[] chars = new char[]{'g', 'o', 'o', 'd'};
        String s1 = new String(chars);

//        String s1 = new String("good");

        System.out.println(s1.intern() == s1);

        System.out.println(System.identityHashCode(s1.intern()));
        System.out.println(System.identityHashCode(s1));

    }

    /**
     * false
     * 491044090
     * 644117698
     * 491044090
     */
    public static void test2(){
        char[] chars = new char[]{'g', 'o', 'o', 'd'};
        String s1 = new String(chars, 0, 4);

        String s11 = "good";

        System.out.println(s1.intern() == s1);

        System.out.println(System.identityHashCode(s1.intern()));
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s11));
    }


}
