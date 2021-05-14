package com.llf.demo.java.leetcode;

import java.util.LinkedList;

/**
 * @author: Oliver.li
 * @Description: 最长回文串
 * @date: 2021/5/6 14:55
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        String result = "";

        char[] chars = s.toCharArray();
        if (chars.length == 1){
            return s;
        }

        int len = 0;

        LinkedList<Character> list = new LinkedList<>();

        for (int i = 0; i < chars.length; i++){
            char c = chars[i];

            if (list.contains(c)){

            }

            list.add(c);
        }

        return result;
    }

}
