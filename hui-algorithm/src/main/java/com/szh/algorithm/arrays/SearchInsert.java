package com.szh.algorithm.arrays;

/**
 * 搜索插入位置
 */
public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target);
    }


    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        SearchInsert searchInsert = new SearchInsert();
        System.out.println(searchInsert.searchInsert(new int[]{1, 3, 4, 6}, 5));
    }
}
