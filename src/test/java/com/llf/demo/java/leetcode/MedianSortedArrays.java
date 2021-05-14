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

    public static double findMedianSortedArray(int[] nums){
        int mid = nums.length / 2;
        boolean isOdd = (nums.length % 2) != 0;

        if (isOdd){
            return nums[mid];
        } else {
            return (double) (nums[mid] + nums[mid - 1]) / 2;
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        boolean isNums1Empty = nums1.length == 0;
        boolean isNums2Empty = nums2.length == 0;

        if (isNums1Empty && isNums2Empty){
            return 0;
        } else if (isNums1Empty){
            return findMedianSortedArray(nums2);
        } else if (isNums2Empty){
            return findMedianSortedArray(nums1);
        }

        if (nums1.length > nums2.length){
            return findMedianSortedArrays2(nums2, nums1);
        }

        int midA = nums1.length / 2;
        int midB = nums2.length / 2;

        while (true){
            int i = nums1[midA];
            int j = nums1[Math.max((midA - 1), 0)];

            int k = nums2[midB];
            int l = nums2[Math.max((midB - 1), 0)];

            if (midA < nums1.length - 1 && l > i){
                midA++;
            } else if (midB > 2 && j > k){
                midB--;
            } else {
                break;
            }
        }

        int totalLength = nums1.length + nums2.length;
        boolean isOdd = (totalLength % 2) != 0;

        int midA1 = Math.max((midA - 1), 0);
        int midB1 = Math.max((midB - 1), 0);

        if (isOdd){
            int j = nums1[midA1];
            int l = nums2[midB1];
            if (midA1 == midA){
                j = 0;
            }
            if (midB1 == midB){
                l = 0;
            }

            return Math.max(j, l);
        } else {
            int i = nums1[midA];
            int j = nums1[Math.max((midA - 1), 0)];

            int k = nums2[midB];
            int l = nums2[Math.max((midB - 1), 0)];
            if (midA1 == midA){
                j = 0;
            }
            if (midB1 == midB){
                l = 0;
            }

            return (double) (Math.max(j, l) + Math.min(i, k)) / 2;
        }
    }

    public static double findMedianSortedArrays3(int[] nums1, int[] nums2){

        int low = 0;
        int high = nums1.length;
        int k = (nums1.length + nums2.length + 1) / 2;
        int nums1Mid = 0;
        int nums2Mid = 0;

        while (low <= high){
            nums1Mid = low + (high - low) / 2;
            nums2Mid = k - nums1Mid;

            if (nums1Mid > 0 && nums1[nums1Mid - 1] > nums2[nums2Mid]){
                high = nums1Mid - 1;
            } else if (nums1Mid != nums1.length && nums1[nums1Mid] < nums2[nums2Mid - 1]){
                low = nums1Mid + 1;
            } else {
                break;
            }
        }

        int midLeft, midRight;
        if (nums1Mid == 0){
            midLeft = nums2[nums2Mid - 1];
        } else if (nums2Mid == 0){
            midLeft = nums1[nums1Mid - 1];
        } else {
            midLeft = Math.max(nums1[nums1Mid - 1], nums2[nums2Mid - 1]);
        }
        if (((nums1.length + nums2.length)&1) == 1){
            return midLeft;
        }

        if (nums1Mid == nums1.length){
            midRight = nums2[nums2Mid];
        } else if (nums2Mid == nums2.length){
            midRight = nums1[nums1Mid];
        } else {
            midRight = Math.min(nums1[nums1Mid], nums2[nums2Mid]);
        }

        return (double) (midLeft + midRight) / 2;
    }

    public static void main(String[] args) {

        int[] num1 = {3};
        int[] num2 = {1,2};

        double median = findMedianSortedArrays2(num1, num2);
        System.out.println(median);
    }

}
