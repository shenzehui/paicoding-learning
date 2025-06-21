package com.szh.algorithm.arrays;

/**
 * 按奇偶排序数组 II
 */
public class SortArrayByParityII {
    public int[] sortArrayByParityII(int[] nums) {
        int[] result = new int[nums.length];
        int evenIndex = 0; // 偶数索引
        int oddIndex = 1; // 奇数索引

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                result[evenIndex] = nums[i];
                evenIndex += 2;
            } else {
                result[oddIndex] = nums[i];
                oddIndex += 2;
            }
        }
        return result;
    }

}
