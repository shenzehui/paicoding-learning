package com.szh.algorithm.gredy;

/**
 * 53. 最大子序和
 */
public class MaxSubArray {

    int result = Integer.MIN_VALUE;

    int count = 0;

    public int maxSubArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            result = Math.max(result, count);
            if (count < 0) {
                // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
                count = 0;
            }
        }
        return result;
    }
}
