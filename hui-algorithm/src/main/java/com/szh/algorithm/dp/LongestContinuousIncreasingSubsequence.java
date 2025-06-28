package com.szh.algorithm.dp;

/**
 * 674. 最长连续递增序列
 */
public class LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {

        int result = 1;

        // dp数组含义：以nums[i]结尾的最长连续递增子序列的长度是dp[i]
        int[] dp = new int[nums.length];

        // 初始化
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        // 遍历 + 递推公式 dp[i] = dp[i-1] + 1
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                result = Math.max(result, dp[i]);
            }
        }

        return result;
    }
}
