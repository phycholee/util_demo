package com.llf.demo.java.leetcode;

import java.util.*;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * @author Oliver.li
 * @date 2023/11/14 17:51
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            //和前一个值一样，可以跳过
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int k = n - 1;
            int target = -nums[i];
            for (int j = i + 1; j < n; j++) {
                //和前一个值一样，可以跳过
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                while (j < k && nums[j] + nums[k] > target){
                    --k;
                }
                if (j == k){
                    break;
                }

                if (nums[j] + nums[k] == target){
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[k]);
                    list.add(l);
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
