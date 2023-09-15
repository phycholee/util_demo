package com.llf.demo.java.leetcode;

import java.util.Arrays;

/**
 * 删除有序数组中的重复项2
 *
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3。不需要考虑数组中超出新长度后面的元素。
 *
 * @author Oliver.li
 * @date 2023/9/7 11:28
 */
public class RemoveDuplicates2 {

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2){
            return n;
        }

        int j = 2;
        for (int i = 2; i < n; i++) {
            int num = nums[i];
            int pre = nums[j-2];
            if (num != pre){
                nums[j++] = num;
            }
        }
        return j;
    }

    public static int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n <= 2){
            return n;
        }

        int j = 1;
        int sameCount = 1;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            int pre = nums[i-1];
            if (num == pre){
                sameCount++;
            } else {
                sameCount = 1;
            }
            if (num != pre || sameCount <= 2){
                nums[j++] = num;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int i = removeDuplicates(nums);
        System.out.println("i=" + i);
        System.out.println("nums=" + Arrays.toString(nums));
    }
}
