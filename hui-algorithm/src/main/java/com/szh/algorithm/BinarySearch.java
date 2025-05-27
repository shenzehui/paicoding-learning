package com.szh.algorithm;

public class BinarySearch {
    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {   // 判断 <= or < 看是否在区间内合法
            int middle = (left + right) / 2;
            if (nums[middle] < target) {
                left = middle + 1; // nums[middle] 已经判断过了，所以这里不需要再判断
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                return middle; // 找到了
            }
        }
        return -1; // 没找到
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int search = search(nums, 5);
        System.out.println(search);
    }
}
