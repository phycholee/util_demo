package com.llf.demo.java.hash;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/5/29 17:48
 */
public class HashTest {

    public static void main(String[] args) {

        for (int i = 1; i <= 31; i++){

            String day = 2000000483390017L + "" + i;

            int n = 31;
            int hash = hash(day, n);
            System.out.println(String.format("  %s  %s  ", day, hash));
        }

    }

    private static int hash(Object key, int length){
        int suitSize = length;
        suitSize = tableSizeFor(suitSize);
        int h;
        int hash =  (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        int index =  (suitSize -1) & hash;

        if (index >= length){
            index = index - length;
        }
        return index;
    }

    private static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int max = 1 << 30;
        return (n < 0) ? 1 : (n >= max) ? max : n + 1;
    }

}
