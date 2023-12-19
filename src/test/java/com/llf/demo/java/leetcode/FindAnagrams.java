package com.llf.demo.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 *
 * @author Oliver.li
 * @date 2023/12/4 11:24
 */
public class FindAnagrams {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()){
            return result;
        }

        char[] pChars = p.toCharArray();
        Arrays.sort(pChars);

        int n = p.length();
        int size = s.length() - n + 1;
        for (int i = 0; i < size; i++) {
            int right = i + n - 1;
            char[] temp = new char[n];
            int k = 0;
            for (int j = i; j <= right; j++) {
                temp[k++] = s.charAt(j);
            }
            Arrays.sort(temp);
            if (Arrays.equals(pChars, temp)){
                result.add(i);
            }
        }

        return result;
    }

    public static List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sl = s.length(), pl = p.length();
        if (sl < pl){
            return result;
        }

        //构建26个字母的计数
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pl; i++) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)){
            result.add(0);
        }

        for (int i = 0; i < sl - pl; i++) {
            --sCount[s.charAt(i) - 'a'];    //把左边划过的词删掉
            ++sCount[s.charAt(i + pl) - 'a'];   //把右边滑进来的词加进来

            if (Arrays.equals(sCount, pCount)){
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams2("cbaebabacd", "abc"));
    }

}
