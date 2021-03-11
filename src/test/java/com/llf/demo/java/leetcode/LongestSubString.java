package com.llf.demo.java.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    public int lengthOfLongestSubstring2(String s){

        char[] chars = s.toCharArray();

        int max = 0;
        int left = 0;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++){
            char c = chars[i];

            if (map.containsKey(c)){
                left = Math.max(left, map.get(c) + 1);
            }

            map.put(c, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int len = new LongestSubString().lengthOfLongestSubstring2("pwwkew");
        System.out.println(len);
    }

}
