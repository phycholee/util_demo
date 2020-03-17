package com.llf.demo.java.string;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/9/23 21:43
 */
public class Base {

    static void test(){
        System.out.println("aa");
    }

    public static void main(String[] args) {
        int[] aa = new int[25];

        System.out.println(aa[2]);
    }
}

class Child{

    void test(){
        System.out.println("bb");
        Base.test();
    }

    public static void main(String[] args) {
        new Child().test();

        int i = 0xFFFFFFF1;
        int j = ~i;
        System.out.println(j);
    }

}
