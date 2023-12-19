package com.llf.demo.java.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @author Oliver.li
 * @date 2023/10/26 10:51
 */
public class LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxSize = 0;
        for (Integer i : set) {
            if (!set.contains(i-1)){
                int currentNum = i;
                int currentSize = 1;

                while (set.contains(currentNum + 1)){
                    currentNum++;
                    currentSize++;
                }

                maxSize = Math.max(maxSize, currentSize);
            }

        }
        return maxSize;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2,101}));
    }
}
