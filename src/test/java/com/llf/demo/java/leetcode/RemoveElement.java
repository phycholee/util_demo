package com.llf.demo.java.leetcode;

import java.util.Arrays;

/**
 * 移除元素
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 *
 * @author Oliver.li
 * @date 2023/9/5 16:05
 */
public class RemoveElement {

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length <= 0){
            return 0;
        }

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num != val){
                nums[j++] = num;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int i = removeElement(nums, 2);
        System.out.println("i=" + i);
        System.out.println("nums=" + Arrays.toString(nums));
    }
}
