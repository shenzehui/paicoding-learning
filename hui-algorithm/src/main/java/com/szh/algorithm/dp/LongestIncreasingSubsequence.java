package com.szh.algorithm.dp;

/**
 * 300. 最长递增子序列
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int result = 1;

        // dp数组含义：以nums[i] 结尾的最长递增子序列的长度是 dp[i]
        int[] dp = new int[nums.length];

        // 初始化
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        // 遍历 + 递推公式  dp[i] = Math.max(dp[i], dp[j] + 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {  // 这里也可以倒序遍历
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    result = Math.max(result, dp[i]);
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int result = longestIncreasingSubsequence.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
    }


}