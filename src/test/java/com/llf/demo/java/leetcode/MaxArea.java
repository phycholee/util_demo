package com.llf.demo.java.leetcode;

/**
 *
 *
 * @author Oliver.li
 * @date 2023/11/2 16:42
 */
public class MaxArea {

    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[i], height[j]);
                int w = j - i;
                max = Math.max(max, h * w);
            }
        }
        return max;
    }

    public static int maxArea2(int[] height) {
        int max = 0, left = 0, right = height.length - 1;
        while (left < right){
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            max = Math.max(max, h * w);
            if (height[left] > height[right]){
                right --;
            } else {
                left++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxArea2(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
