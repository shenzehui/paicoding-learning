package com.szh.algorithm.arrays;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = nums.length - 1;

        int flag = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                flag = 1;
                left = mid;
                right = mid;
                break;
            }
        }

        if (flag == 0) {
            return new int[]{-1, -1};
        }

        while (left > 0 && nums[left - 1] == target) {
            left--;
        }

        while (right < nums.length - 1 && nums[right + 1] == target) {
            right++;
        }

        return new int[]{left, right};
    }

    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        System.out.println(Arrays.toString(searchRange.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
}
