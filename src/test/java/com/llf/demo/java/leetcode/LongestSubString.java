package com.llf.demo.java.leetcode;

import java.util.LinkedList;

/**
 * @author: Oliver.li
 * @Description: 无重复字符的最长子串
 * @date: 2020/1/13 18:04
 */
public class LongestSubString {


    public int lengthOfLongestSubstring(String s) {

        char[] chars = s.toCharArray();
        if (chars.length == 1){
            return 1;
        }

        int len = 0;

        LinkedList<Character> list = new LinkedList<>();
        for (char c : chars) {
            if (list.contains(c)){
                len = Math.max(len, list.size());
                while (list.pop() != c){}
            }

            list.add(c);
        }

        len = Math.max(len, list.size());
        return len;
    }

    public static void main(String[] args) {
        int len = new LongestSubString().lengthOfLongestSubstring("pwwkew");
        System.out.println(len);
    }

}
