package com.llf.demo.java.leetcode;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * [1,2,3,0,0]
 * [2,4]
 * [1,2,2,3,4]
 *
 *
 * @author Oliver.li
 * @date 2023/8/31 15:13
 */
public class MergeTwoArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n <= 0){
            return;
        }

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (k >= 0 && j >= 0){
            if (i >= 0){
                int a = nums1[i];
                int b = nums2[j];
                if (a >= b){
                    nums1[k--] = a;
                    i--;
                } else {
                    nums1[k--] = b;
                    j--;
                }
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        int nums1Index = m - 1;
        int nums2Index = n - 1;
        for(int i = k - 1; i >= 0; i--){
            if(nums1Index < 0){
                nums1[i] = nums2[nums2Index--];
            } else if(nums2Index < 0){
                break;
            } else if(nums1[nums1Index] >= nums2[nums2Index]){
                nums1[i] = nums1[nums1Index--];
            } else if(nums2[nums2Index] >= nums1[nums1Index]){
                nums1[i] = nums2[nums2Index--];
            }
        }
    }

    public static void main(String[] args) {
        int[] num1 = {4,5,6,0,0,0};
        int[] num2 = {1,2,3};
        merge(num1, 3, num2, 3);
        System.out.println(Arrays.toString(num1));
    }
}
