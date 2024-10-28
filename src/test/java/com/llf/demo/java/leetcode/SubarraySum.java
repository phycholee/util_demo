package com.llf.demo.java.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * @author Oliver.li
 * @date 2023/12/19 11:11
 */
public class SubarraySum {

    /*

    0,1
    1,1
    3,1
    6,1

     */

    public static int subarraySum(int[] nums, int k) {
        int pre = 0, count = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        System.out.println(map);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{3,4,7,2,-3,1,4,2,1}, 7));
    }
}
