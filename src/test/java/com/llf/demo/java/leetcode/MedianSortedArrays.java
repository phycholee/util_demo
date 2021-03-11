package com.llf.demo.java.leetcode;

/**
 * @author: Oliver.li
 * @Description: 寻找两个正序数组的中位数
 * @date: 2021/3/2 19:49
 */
public class MedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] merge = new int[nums1.length + nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            merge[i] = nums1[i];
        }

        int nextIndex = nums1.length;
        for (int i = 0; i < nums2.length; i++) {
            int index = i + nextIndex;
            merge[index] = nums2[i];
        }


        int mergeLength = merge.length;
        int[] temp = new int[mergeLength];
        sort(merge, 0, mergeLength - 1, temp);

        int mid = mergeLength / 2;
        boolean isOdd = (mergeLength % 2) != 0;

        if (isOdd){
            return merge[mid];
        } else {
            return (double) (merge[mid] + merge[mid - 1]) / 2;
        }
    }

    private static void sort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        while (i <= mid){
            temp[t++] = arr[i++];
        }
        while (j <= right){
            temp[t++] = arr[j++];
        }
        t = 0;

        while (left <= right){
            arr[left++] = temp[t++];
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        int tempLen = Math.max(nums1.length, nums2.length);

        int[] temp = new int[tempLen];
        sort(nums1, 0, nums1.length - 1, temp);
        sort(nums2, 0, nums2.length - 1, temp);

        int totalLen = nums1.length + nums2.length;

        int mid = totalLen / 2;
        boolean isOdd = (totalLen % 2) != 0;

        if (isOdd){
            if (mid < nums1.length){
                return nums1[mid];
            } else {
                return nums2[mid - nums1.length];
            }

        } else {
            int a, b;
            if (mid < nums1.length){
                a =  nums1[mid];
            } else {
                a =  nums2[mid - nums1.length];
            }

            if (mid - 1 < nums1.length){
                b =  nums1[mid - 1];
            } else {
                b =  nums2[mid - 1 - nums1.length];
            }

            return (double) (a + b) / 2;
        }
    }

    public static void main(String[] args) {

        int[] num1 = {1, 4};
        int[] num2 = {2, 3};

        double median = findMedianSortedArrays2(num1, num2);
        System.out.println(median);
    }

}
