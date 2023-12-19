package com.llf.demo.java.leetcode;

/**
 * 接雨水
 * @author Oliver.li
 * @date 2023/11/29 18:10
 */
public class Trap {

    public static int trap(int[] height) {
        if (height.length <= 2){
            return 0;
        }
        int result = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (height[left] < height[right]){
                result += leftMax - height[left];
                left++;
            } else {
                result += rightMax - height[right];
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{4,2,0,3,2,5}));
    }
}
