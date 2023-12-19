package com.llf.demo.java.leetcode;

import java.util.Arrays;

/**
 * 169. 多数元素
 *
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 * @author Oliver.li
 * @date 2023/9/22 16:08
 */
public class MajorityElement {

    public static int majorityElement(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int half = nums.length / 2;
        Arrays.sort(nums);
        int l = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            int next = nums[i + 1];
            if (num == next){
                l++;
            } else {
                l = 1;
                continue;
            }
            if (l > half){
                return num;
            }
        }
        return 0;
    }

    public static int majorityElement2(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
            System.out.printf("count=%s,candidate=%s,num=%s%n", count, candidate, num);
        }

        return candidate;
    }

    public static void sort(int[] nums){
        aux = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    public static void sort(int[] nums, int left, int right){
        if (left >= right){
            return;
        }

        int mid = (left + right) / 2;
        sort(nums, left, mid);
        sort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private static int[] aux;

    public static void merge(int[] nums, int left, int mid, int right){
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            aux[k] = nums[k];
        }

        for (int k = left; k <= right; k++) {
            if (i > mid){
                nums[k] = aux[j++];
            } else if (j > right){
                nums[k] = aux[i++];
            } else if (aux[i] > aux[j]){
                nums[k] = aux[j++];
            } else {
                nums[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,1,1,2,2};
        System.out.println(majorityElement2(nums));
    }
}
