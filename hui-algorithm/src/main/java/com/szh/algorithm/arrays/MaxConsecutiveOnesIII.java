package com.szh.algorithm.arrays;

/**
 * 最大连续1的个数 III
 */
public class MaxConsecutiveOnesIII {


    /**
     * 双指针，窗口内0的个数小于k
     */
    public int longestOnes(int[] nums, int k) {
        // k 就当作是 0 的个数，且零只出现在数组前后
        int i = 0;
        int res = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {
                k--;
            }
            while (k < 0) {
                if (nums[i] == 0) {
                    k++;
                }
                i++;
            }

            res = Math.max(res, j - i + 1);
        }
        return res;

    }
}
