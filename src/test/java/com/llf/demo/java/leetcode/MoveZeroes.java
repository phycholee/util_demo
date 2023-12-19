package com.llf.demo.java.leetcode;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * @author Oliver.li
 * @date 2023/11/1 17:49
 */
public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1){
            return;
        }

        int slow = 0;
        for (int i = 1; i < nums.length; i++) {
            int left = nums[slow];
            if (left != 0){
                slow++;
                continue;
            }
            int right = nums[i];
            if (right != 0){
                nums[slow++] = right;
                nums[i] = 0;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        moveZeroes(new int[]{2,1,0,0,3,12});
    }
}
